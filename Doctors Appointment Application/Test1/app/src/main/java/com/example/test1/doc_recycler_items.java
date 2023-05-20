package com.example.test1;

public class doc_recycler_items {

    private String itm_name;
    private int logo;

    public String doctor_email;
    public String employee_number;

    public String employee_city;


    public doc_recycler_items(String itm_name, int logo, String doctor_email, String employee_number, String employee_city) {
        this.itm_name = itm_name;
        this.logo = logo;
        this.doctor_email = doctor_email;
        this.employee_number = employee_number;
        this.employee_city = employee_city;
    }


    public String getItm_name() {
        return itm_name;
    }

    public int getLogo() {
        return logo;
    }

    public void setItm_name(String itm_name) {
        this.itm_name = itm_name;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getDoctor_email() {
        return doctor_email;
    }

    public void setDoctor_email(String doctor_email) {
        this.doctor_email = doctor_email;
    }

    public String getEmployee_number() {
        return employee_number;
    }

    public void setEmployee_number(String employee_number) {
        this.employee_number = employee_number;
    }

    public String getEmployee_city() {
        return employee_city;
    }

    public void setEmployee_city(String employee_city) {
        this.employee_city = employee_city;
    }


}
