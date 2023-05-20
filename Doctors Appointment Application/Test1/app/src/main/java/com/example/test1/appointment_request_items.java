package com.example.test1;

public class appointment_request_items {

    public String user_username;
    public String user_email;
    public String appointment_date;
    public String appointment_time;
    public String appointment_status;
    public String patient_name;
    public String employee_number;
    public String doctor_email;

    public String employee_city;
    public String Hospital;


    public String getEmployee_city() {
        return employee_city;
    }

    public void setEmployee_city(String employee_city) {
        this.employee_city = employee_city;
    }

    public String getHospital() {
        return Hospital;
    }

    public void setHospital(String hospital) {
        Hospital = hospital;
    }


    public String getEmployee_number() {
        return employee_number;
    }

    public void setEmployee_number(String employee_number) {
        this.employee_number = employee_number;
    }

    public String getDoctor_email() {
        return doctor_email;
    }

    public void setDoctor_email(String doctor_email) {
        this.doctor_email = doctor_email;
    }


    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }


    public String getAppointment_status() {
        return appointment_status;
    }

    public void setAppointment_status(String appointment_status) {
        this.appointment_status = appointment_status;
    }


    public String getUser_username() {
        return user_username;
    }

    public void setUser_username(String user_username) {
        this.user_username = user_username;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(String appointment_date) {
        this.appointment_date = appointment_date;
    }

    public String getAppointment_time() {
        return appointment_time;
    }

    public void setAppointment_time(String appointment_time) {
        this.appointment_time = appointment_time;
    }


    public appointment_request_items(String doctor_email,String employee_number,String patient_name,String user_username, String user_email, String appointment_date, String appointment_time, String appointment_status, String Hospital, String employee_city) {
        this.doctor_email = doctor_email;
        this.employee_number = employee_number;
        this.patient_name = patient_name;
        this.user_username = user_username;
        this.user_email = user_email;
        this.appointment_date = appointment_date;
        this.appointment_time = appointment_time;
        this.appointment_status = appointment_status;
        this.Hospital = Hospital;
        this.employee_city = employee_city;
    }







}
