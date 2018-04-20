package com.troiano.springmvc.model;

public class Notification {

    private String msg;
    private String type;
    private int timer;

    public Notification(){
        this.msg = "";
        this.type = "info";
        this.timer = 3000;
    }
    public Notification(String msg){
        this.msg = msg;
        this.type = "info";
        this.timer = 3000;
    }

    public Notification(String msg, String type){
        this.msg = msg;
        this.type = type;
        this.timer = 3000;
    }


    public Notification(String msg, String type, int timer) {
        this.msg = msg;
        this.type = type;
        this.timer = timer;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
}

