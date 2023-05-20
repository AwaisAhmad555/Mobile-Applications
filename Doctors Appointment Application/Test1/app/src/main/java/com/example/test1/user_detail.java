package com.example.test1;

public class user_detail {

    public String UserName;
    public String Email;
    public String Phone;
    public String BloodGroup;

    public user_detail(){


    }


   // public user_detail(String userName, String email, String bloodGroup, String phone) {
       // UserName = userName;
     //   Email = email;
      //  BloodGroup = bloodGroup;
      //  Phone = phone;
    //}

    public String getUserName() {
        return UserName;
    }

    public String getEmail() {
        return Email;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }




}
