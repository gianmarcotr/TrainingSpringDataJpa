package com.troiano.springmvc.controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.print.Doc;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.troiano.springmvc.model.*;
import com.troiano.springmvc.service.DocumentService;
import com.troiano.springmvc.service.MaritalStatusService;
import com.troiano.springmvc.service.SkillService;
import javafx.print.Printer;
import org.aspectj.weaver.ast.Not;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.troiano.springmvc.service.UserService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/")
public class FileController {

    @Autowired
    UserService userService;

    @Autowired
    MaritalStatusService maritalStatusService;

    @Autowired
    SkillService skillService;

    @Autowired
    DocumentService documentService;

    @Autowired
    MessageSource messageSource;



    //MANAGE FILE GET
    @RequestMapping(value = {"/uploadFile-{idU}"}, method = RequestMethod.GET)
    public String uploadFile(@PathVariable int idU, ModelMap model){

        User user = userService.findById(idU);
        FileBucket fileBucket = new FileBucket();
        if(user.getUserDocuments() != null)
            model.addAttribute("docs", true);
        model.addAttribute("fileBucket", fileBucket);
        model.addAttribute("user", user);

        List<Notification> notifications = new ArrayList<>();
        Notification not = new Notification("Manage User File");
        notifications.add(not);

        model.addAttribute("notice", true);
        model.addAttribute("notification", notifications);
        return "document";
    }

    //UPLOAD FILE POST
    @RequestMapping(value = {"/uploadFile-{idU}"}, method = RequestMethod.POST)
    public String uploadFile(@Valid FileBucket fileBucket, BindingResult bindingResult, ModelMap model, @PathVariable int idU, RedirectAttributes redirectAttributes) throws IOException{

        User user = userService.findById(idU);

        if(fileBucket.getFile().getOriginalFilename().equals("")){
            bindingResult.reject("fileBucket");
            model.addAttribute("invalidFB", "is-invalid");
        }

        if(bindingResult.hasErrors()){
            List<FieldError> fieldError = bindingResult.getFieldErrors();
            for (FieldError fe: fieldError){
                if(fe.getField().equals("description"))
                    model.addAttribute("invalidDesc", "is-invalid");

            }
            List<Notification> notifications = new ArrayList<>();
            Notification not = new Notification("Error: please control the form:", "danger");
            notifications.add(not);

            model.addAttribute("notice", true);
            model.addAttribute("notification", notifications);
            model.addAttribute("user", user);
            return "document";
        }

        if(user.getUserDocuments() != null)
            documentService.deleteById(user.getUserDocuments().getIdUD());

        saveDocument(fileBucket, user);

        List<Notification> notifications = new ArrayList<>();
        Notification not = new Notification("File uploaded with success", "success");
        notifications.add(not);

        redirectAttributes.addFlashAttribute("notice", true);
        redirectAttributes.addFlashAttribute("notification", notifications);
        model.addAttribute("docs", true);

        return "redirect:/user-"+user.getId();
    }


    //DELETE FILE
    @RequestMapping(value = {"/deleteFile-{idU}"}, method = RequestMethod.GET)
    public String deleteFile(@PathVariable int idU, RedirectAttributes redirectAttributes){
        User user = userService.findById(idU);

        documentService.deleteById(user.getUserDocuments().getIdUD());

        List<Notification> notifications = new ArrayList<>();
        Notification not = new Notification("File deleted with success", "success");
        notifications.add(not);
        redirectAttributes.addFlashAttribute("notification", notifications);
        redirectAttributes.addFlashAttribute("notice", true);

        return "redirect:/user-"+user.getId();
    }

    //DOWNLOAD FILE
    @RequestMapping(value = { "/downloadFile-{idU}-{idD}" }, method = RequestMethod.GET)
    public String downloadDocument(@PathVariable int idU, @PathVariable int idD, HttpServletResponse response) throws IOException {
        Document document = documentService.findById(idD);
        response.setContentType(document.getType());
        response.setContentLength(document.getContent().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + document.getName() +"\"");

        FileCopyUtils.copy(document.getContent(), response.getOutputStream());

        return "redirect:/user-"+idU;
    }


    //SAVE FILE
    private void saveDocument(FileBucket fileBucket, User user) throws IOException{
        Document document = new Document();

        MultipartFile multipartFile = fileBucket.getFile();

        document.setName(multipartFile.getOriginalFilename());
        document.setDescr(fileBucket.getDescription());
        document.setType(multipartFile.getContentType());
        document.setContent(multipartFile.getBytes());
        document.setUser(user);
        documentService.saveDocument(document);
    }



    @ModelAttribute("maritalStatus")
    public List<MaritalStatus> getMaritalStatus(){
        List<MaritalStatus> maritalStatus = maritalStatusService.findAllMaritalStatus();
        return maritalStatus;
    }

    @ModelAttribute("listSkills")
    public List<Skill> getSkills(){
        List<Skill> skills = skillService.findAllSkills();
        return skills;
    }
}