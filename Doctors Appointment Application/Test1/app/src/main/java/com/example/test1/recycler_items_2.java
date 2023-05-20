package com.example.test1;

public class recycler_items_2 {



    private String doc_Name;
    private String doc_type;
    private int profile_img;
    private String employee_no;
    private String hospital_address;
    private String Patient_name;
    private String Patient_username;
    private String Ph_no;
    private String email;
    private String Patient_email;
    private String Patient_phone;

    public recycler_items_2(String doc_Name, String doc_type, int profile_img, String employee_no, String hospital_address, String email, String patient_name, String patient_username, String ph_no,String patient_email, String patient_phone) {
        this.doc_Name = doc_Name;
        this.doc_type = doc_type;
        this.profile_img = profile_img;
        this.employee_no = employee_no;
        this.hospital_address = hospital_address;
        this.email = email;
        this.Patient_name = patient_name;
        this.Patient_username = patient_username;
        this.Ph_no = ph_no;
        this.Patient_email = patient_email;
        this.Patient_phone = patient_phone;
    }








    public int getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(int profile_img) {
        this.profile_img = profile_img;
    }

    public String getDoc_Name() {
        return doc_Name;
    }

    public void setDoc_Name(String doc_Name) {
        this.doc_Name = doc_Name;
    }

    public String getDoc_type() {
        return doc_type;
    }

    public void setDoc_type(String doc_type) {
        this.doc_type = doc_type;
    }

    public String getEmployee_no() {
        return employee_no;
    }

    public String getHospital_address() {
        return hospital_address;
    }

    public void setEmployee_no(String employee_no) {
        this.employee_no = employee_no;
    }

    public void setHospital_address(String hospital_address) {
        this.hospital_address = hospital_address;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPatient_name() {
        return Patient_name;
    }

    public String getPatient_username() {
        return Patient_username;
    }

    public void setPatient_name(String patient_name) {
        Patient_name = patient_name;
    }

    public void setPatient_username(String patient_username) {
        Patient_username = patient_username;
    }

    public String getPh_no() {
        return Ph_no;
    }

    public void setPh_no(String ph_no) {
        Ph_no = ph_no;
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
