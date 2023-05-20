package com.example.test1;

public class patient_global_class {

    public static patient_global_class instance;


    public int patientID;
    public String patientName;
    public String UserName;
    public String UserEmail;
    public String UserPhone;
    public String UserBloodGroup;


    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }


    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public String getUserBloodGroup() {
        return UserBloodGroup;
    }

    public void setUserBloodGroup(String userBloodGroup) {
        UserBloodGroup = userBloodGroup;
    }




    public patient_global_class() {
    }


    public static synchronized patient_global_class getInstance(){

        if(instance == null){

            instance = new patient_global_class();

        }

        return instance;
    }

}
