package com.troiano.springmvc.model;


import org.hibernate.validator.constraints.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.lang.annotation.Documented;

/*
    Spring provides org.springfreamework.web.multipart.MultipartFile which is
    a representation of an uploaded file received in a multipart request.
    It provides handy methods like getName(), getContentType(), getBytes(),
    getInputStream() etc.. which make life bit easier while retrieving
    information about file being uploaded
 */
public class FileBucket {


    MultipartFile file;

    @NotEmpty
    String description;


    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file){
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
