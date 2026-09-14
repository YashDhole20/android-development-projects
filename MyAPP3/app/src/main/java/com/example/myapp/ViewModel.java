package com.example.myapp;

public class ViewModel {

    private String title_nm;
    private String desc;
    private String loc;
    private static boolean isSelected;

    public ViewModel(String title_nm, String desc, String loc) {
        this.title_nm = title_nm;
        this.desc = desc;
        this.loc = loc;
    }

    public String getTitle_nm() {
        return title_nm;
    }

    public void setTitle_nm(String title_nm) {
        this.title_nm = title_nm;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public static boolean isIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(boolean isSelected) {
        ViewModel.isSelected = isSelected;
    }
}



