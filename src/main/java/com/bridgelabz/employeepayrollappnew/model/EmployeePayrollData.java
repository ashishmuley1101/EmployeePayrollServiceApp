package com.bridgelabz.employeepayrollappnew.model;

import com.bridgelabz.employeepayrollappnew.dto.EmployeePayrollDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

//Employee Payroll Data use like Bean
// @Data give all methods getter and setter to class
public @Data class EmployeePayrollData {
    private int employeeId;
    private String name;
    private long salary;

    private String gender;
    private LocalDate startDate;
    private String note;
    private String profilePic;
    private List<String> departments;

    public EmployeePayrollData() {}

    public EmployeePayrollData(int empId, EmployeePayrollDTO employeePayrollDTO)
    {
        this.employeeId = empId;
        this.name = employeePayrollDTO.name;
        this.salary = employeePayrollDTO.salary;
        this.gender=employeePayrollDTO.gender;
        this.note=employeePayrollDTO.note;
        this.startDate= employeePayrollDTO.startDate;
        this.profilePic=employeePayrollDTO.profilePic;
        this.departments=employeePayrollDTO.departments;
    }



}
