package com.example.test1;

public class appointment_items {




    private String doc_name;
    private int logo;
    private String doc_type;
    private String status;
    private String time;
    private String date;


    public appointment_items(){


    }

    public appointment_items(String doc_name, int logo, String doc_type, String status, String time, String date) {
        this.doc_name = doc_name;
        this.logo = logo;
        this.doc_type = doc_type;
        this.status = status;
        this.time = time;
        this.date = date;
    }

    public String getDoc_name() {
        return doc_name;
    }

    public int getLogo() {
        return logo;
    }

    public String getDoc_type() {
        return doc_type;
    }

    public String getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public void setDoc_name(String doc_name) {
        this.doc_name = doc_name;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public void setDoc_type(String doc_type) {
        this.doc_type = doc_type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }



}
