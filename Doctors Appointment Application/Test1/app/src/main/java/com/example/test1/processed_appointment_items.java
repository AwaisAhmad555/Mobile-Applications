package com.example.test1;

public class processed_appointment_items {


    public int id;
    public String AppointmentStatus;
    public String AppointmentDate;
    public String AppointmentTime;
    public String AppointmentHospital;
    public String request_date;
    public String patientUsername;
    public String doctorUsername;


    public processed_appointment_items(int id, String appointmentStatus, String appointmentDate, String appointmentTime, String appointmentHospital, String request_date, String patientUsername, String doctorUsername) {

        this.id = id;
        this.AppointmentStatus = appointmentStatus;
        this.AppointmentDate = appointmentDate;
        this.AppointmentTime = appointmentTime;
        this.AppointmentHospital = appointmentHospital;
        this.request_date = request_date;
        this.patientUsername = patientUsername;
        this.doctorUsername = doctorUsername;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getAppointmentStatus() {
        return AppointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        AppointmentStatus = appointmentStatus;
    }

    public String getAppointmentDate() {
        return AppointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        AppointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return AppointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        AppointmentTime = appointmentTime;
    }

    public String getAppointmentHospital() {
        return AppointmentHospital;
    }

    public void setAppointmentHospital(String appointmentHospital) {
        AppointmentHospital = appointmentHospital;
    }

    public String getRequest_date() {
        return request_date;
    }

    public void setRequest_date(String request_date) {
        this.request_date = request_date;
    }

    public String getPatientUsername() {
        return patientUsername;
    }

    public void setPatientUsername(String patientUsername) {
        this.patientUsername = patientUsername;
    }

    public String getDoctorUsername() {
        return doctorUsername;
    }

    public void setDoctorUsername(String doctorUsername) {
        this.doctorUsername = doctorUsername;
    }



}
