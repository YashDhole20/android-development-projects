package com.apti.smartaptitude;



public class Company {
    private String name;
    private String currentJobRole;
    private int totalVacancies;
    private double salary;
    private String jobDescription;

    // Constructor
    public Company(String name, String currentJobRole, int totalVacancies, double salary, String jobDescription) {
        this.name = name;
        this.currentJobRole = currentJobRole;
        this.totalVacancies = totalVacancies;
        this.salary = salary;
        this.jobDescription = jobDescription;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getCurrentJobRole() {
        return currentJobRole;
    }

    public int getTotalVacancies() {
        return totalVacancies;
    }

    public double getSalary() {
        return salary;
    }

    public String getJobDescription() {
        return jobDescription;
    }
}
