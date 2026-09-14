package com.example.project;

public class Student {
    private String studName;
    private String parentName;
    private String address;
    private String mobile;
    private String pickup;
    private String studID;

    public Student() {
    }

    public Student(String studName, String parentName, String address, String mobile, String pickup, String studID) {
        this.studName = studName;
        this.parentName = parentName;
        this.address = address;
        this.mobile = mobile;
        this.pickup = pickup;
        this.studID = studID;
    }

    public String getStudName() {
        return studName;
    }

    public void setStudName(String studName) {
        this.studName = studName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getStudID() {
        return studID;
    }

    public void setStudID(String studID) {
        this.studID = studID;
    }
}


