package com.troiano.springmvc.model;

public enum TypeNotice{
    success("success"),
    warning("warning"),
    danger("danger"),
    info("info");

    String typeNotice;

    private TypeNotice(String typeNotice) {
        this.typeNotice = typeNotice;
    }

    public String getTypeNotice(){
        return typeNotice;
    }
}
