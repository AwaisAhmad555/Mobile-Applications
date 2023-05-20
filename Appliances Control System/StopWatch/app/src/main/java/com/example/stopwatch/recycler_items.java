package com.example.stopwatch;

public class recycler_items {



    public int img;

    public String itm_name;


    public recycler_items(int img, String itm_name) {
        this.img = img;
        this.itm_name = itm_name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getItm_name() {
        return itm_name;
    }

    public void setItm_name(String itm_name) {
        this.itm_name = itm_name;
    }

}
