package com.troiano.springmvc.controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.print.Doc;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.sun.org.apache.xpath.internal.operations.Mod;
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
public class UserController {

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

    /**
     * This method will list all existing users.
     */
    @RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
       List<User> users = userService.findAllUsers();
       model.addAttribute("users", users);
        return "index";
    }

    //DELETE USER
    @RequestMapping(value = {"/delete-{id}"}, method = RequestMethod.GET)
        public String deleteUer(@PathVariable int id, RedirectAttributes redirectAttributes){
        userService.deleteUser(id);
        List<Notification> notifications = new ArrayList<>();
        Notification not = new Notification("User deleted with success", TypeNotice.success.getTypeNotice());
        notifications.add(not);
        redirectAttributes.addFlashAttribute("notification", notifications);
        redirectAttributes.addFlashAttribute("notice", true);
        return "redirect:/list";
    }

    //USER PROFILE GET
    @RequestMapping(value = {"/user-{id}"}, method = RequestMethod.GET)
    public String user(@PathVariable int id, ModelMap model){
        User user = userService.findById(id);

        if(user.getUserDocuments() != null)
            model.addAttribute("docs", true);
        if(user.getSkills().size() == 0)
            model.addAttribute("noSkill", true);

        model.addAttribute("user", user);
        return "user";
    }

    //ADD USER GET
    @RequestMapping(value = {"/new"}, method = RequestMethod.GET)
    public String newUser(ModelMap modelMap){
        User user = new User();
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("edit", false);

        return "signup";
    }

    //ADD USER POST
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @RequestMapping(value = {"/new"}, method = RequestMethod.POST)
    public String newUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttributes){

        if(user.getMaritalStatus().getIdMS()==0)
            user.setMaritalStatus(null);

        if(bindingResult.hasErrors()){
            //Setto notifiche di errore
            List<Notification> notifications = new ArrayList<>();

            //prima notifica
            Notification not1 = new Notification("Error: please control the form", TypeNotice.danger.getTypeNotice());
            //seconda notifica
            Notification not2 = new Notification("First name, last name and birth date are required", TypeNotice.warning.getTypeNotice(), 10000);

            Notification not3 = new Notification("In the next page you can uplooad a file");
            not3.setTimer(6000);

            notifications.add(not1);
            notifications.add(not3);
            notifications.add(not2);


            modelMap.addAttribute("notice", true);
            modelMap.addAttribute("notification", notifications);

            //Controllo quale campo ha determintato l'errore
            List<FieldError> fieldError = bindingResult.getFieldErrors();
            checkField(fieldError, modelMap);
            return "signup";
        }

        //MaritalStatus ms = maritalStatusService.findById(user.getMaritalStatus().getIdMS());
        //user.setMaritalStatus(ms);

        userService.saveUser(user);
        List<Notification> notifications = new ArrayList<>();


        Notification not = new Notification("User added with success", TypeNotice.success.getTypeNotice(), 5000);
        Notification not2 = new Notification("Click on 'Manage File' to upload a document");
        not2.setTimer(7000);

        notifications.add(not);
        notifications.add(not2);


        redirectAttributes.addFlashAttribute("notification", notifications);
        redirectAttributes.addFlashAttribute("notice", true);

        return "redirect:/user-"+user.getId();

    }

    //EDIT USER GET
    @RequestMapping(value = { "/edit-user-{idU}" }, method = RequestMethod.GET)
    public String editUser(@PathVariable int idU, ModelMap model) {
        User user = userService.findById(idU);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        return "signup";
    }

    //EDIT USER POST
    @RequestMapping(value = {"/edit-user-{idU}"}, method = RequestMethod.POST)
        public String updateUser(@Valid User user, BindingResult result, ModelMap model, @PathVariable int idU, RedirectAttributes redirectAttributes) {

        if(user.getMaritalStatus().getIdMS()==0)
            user.setMaritalStatus(null);

        if (result.hasErrors()){
            //Setto notifica di errore
            List<Notification> notifications = new ArrayList<>();
            //prima notifica
            Notification not1 = new Notification("Error: please control the form", TypeNotice.danger.getTypeNotice(), 10000);
            notifications.add(not1);

            model.addAttribute("notice", true);
            model.addAttribute("notification", notifications);

            //Controllo quale campo ha determintato l'errore
            List<FieldError> fieldError = result.getFieldErrors();

            checkField(fieldError, model);

            return "signup";
        }



        user.setId(idU);

        //MaritalStatus ms = maritalStatusService.findById(user.getMaritalStatus().getIdMS());
        //user.setMaritalStatus(ms);

        userService.updateUser(user);
        List<Notification> notifications = new ArrayList<>();
        Notification not = new Notification("User updated with success", TypeNotice.success.getTypeNotice());
        notifications.add(not);

        redirectAttributes.addFlashAttribute("notification", notifications);
        redirectAttributes.addFlashAttribute("notice", true);


        return "redirect:/user-"+user.getId();
    }


    // SEARCH
    @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
    public String search(@RequestParam("str") String str, ModelMap model){
        List<User> users = userService.search(str);
        if(users.size()==0){
            model.addAttribute("noElement", true);
            List<Notification> notifications = new ArrayList<>();
            Notification not = new Notification("No element found", TypeNotice.warning.getTypeNotice());
            notifications.add(not);
            model.addAttribute("notice", true);
            model.addAttribute("notification", notifications);
        }

        model.addAttribute("reset", true);
        model.addAttribute("users", users);



        return "index";
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

    public void checkField(List<FieldError> fieldError, ModelMap model){

        for(FieldError fe : fieldError){
            if(fe.getField().equals("firstname"))
                model.addAttribute("invalidFN", "is-invalid");
            if(fe.getField().equals("lastname"))
                model.addAttribute("invalidLN", "is-invalid");
            if(fe.getField().equals("birthDate"))
                model.addAttribute("invalidBD", "is-invalid");
        }

    }
}