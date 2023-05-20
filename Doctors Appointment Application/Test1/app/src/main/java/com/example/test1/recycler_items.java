package com.example.test1;

public class recycler_items {

    private String Itm_name;
    private int Logo;
    private String Patient_Name;
    private String Patient_UserName;

    private String Patient_email;

    private String Patient_phone;

    public recycler_items() {
    }

    public recycler_items(String itm_name, int logo, String patient_Name, String patient_UserName,String patient_email,String patient_phone) {
        this.Itm_name = itm_name;
        this.Logo = logo;
        this.Patient_Name = patient_Name;
        this.Patient_UserName = patient_UserName;
        this.Patient_email = patient_email;
        this.Patient_phone = patient_phone;
    }


    public String getItm_name() {
        return Itm_name;
    }

    public int getLogo() {
        return Logo;
    }

    public void setItm_name(String itm_name) {
        Itm_name = itm_name;
    }

    public void setLogo(int logo) {
        Logo = logo;
    }

    public String getPatient_Name() {
        return Patient_Name;
    }
    public void setPatient_Name(String patient_Name) {
        Patient_Name = patient_Name;
    }


    public String getPatient_UserName() {
        return Patient_UserName;
    }

    public void setPatient_UserName(String patient_UserName) {
        Patient_UserName = patient_UserName;
    }

    public String getPatient_email() {
        return Patient_email;
    }

    public String getPatient_phone() {
        return Patient_phone;
    }

    public void setPatient_email(String patient_email) {
        Patient_email = patient_email;
    }

    public void setPatient_phone(String patient_phone) {
        Patient_phone = patient_phone;
    }


}

