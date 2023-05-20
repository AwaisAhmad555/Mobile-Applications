package com.example.stopwatch;

public class switch_recycler_items {


    public String appliance_name;
    public String index;


    public String NameOut;
    public String AddressOut;


    public switch_recycler_items(String appliance_name, String index) {
        this.appliance_name = appliance_name;
        this.index = index;
    }


    public String getAppliance_name() {
        return appliance_name;
    }

    public String getIndex() {
        return index;
    }

    public void setAppliance_name(String appliance_name) {
        this.appliance_name = appliance_name;
    }

    public void setIndex(String index) {
        this.index = index;
    }




    public String getNameOut() {
        return NameOut;
    }

    public void setNameOut(String nameOut) {
        NameOut = nameOut;
    }

    public String getAddressOut() {
        return AddressOut;
    }

    public void setAddressOut(String addressOut) {
        AddressOut = addressOut;
    }


}
