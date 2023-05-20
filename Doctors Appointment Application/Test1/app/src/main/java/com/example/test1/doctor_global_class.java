package com.example.test1;

public class doctor_global_class {

    public static doctor_global_class instance;

    public String DoctorEmail;
    public String doctorUsername;
    public String EmployeeNumber;
    public String EmployeeCity;
    public int doctor_id;



    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }



    public String getDoctorEmail() {
        return DoctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        DoctorEmail = doctorEmail;
    }

    public String getDoctorUsername() {
        return doctorUsername;
    }

    public void setDoctorUsername(String doctorUsername) {
        this.doctorUsername = doctorUsername;
    }

    public String getEmployeeNumber() {
        return EmployeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        EmployeeNumber = employeeNumber;
    }

    public String getEmployeeCity() {
        return EmployeeCity;
    }

    public void setEmployeeCity(String employeeCity) {
        EmployeeCity = employeeCity;
    }




    //constructor

    public doctor_global_class() {

    }


    public static synchronized doctor_global_class getInstance(){

        if(instance == null){

            instance = new doctor_global_class();

        }

        return instance;
    }

}
