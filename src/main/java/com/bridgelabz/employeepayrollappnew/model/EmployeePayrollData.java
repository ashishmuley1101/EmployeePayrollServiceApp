package com.bridgelabz.employeepayrollappnew.model;

import com.bridgelabz.employeepayrollappnew.dto.EmployeePayrollDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

//Employee Payroll Data use like Bean
// @Data give all methods getter and setter to class

//@Entity for generating in DB for particular class
//@Table for creating table in DB
@Entity
@Table(name = "employee_payrolll")
public @Data class EmployeePayrollData {

    //@Id for Primary Key for table with variable employeeId
    //@Generated as Auto Type
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employeeId;

    //@Column creating column in employee_payroll DB in with same field
    @Column(name = "name")
    private String name;
    @Column(name = "salary")
    private long salary;
    @Column(name = "gender")
    private String gender;

    @Column(name = "start_da")
    private LocalDate startDate;

    @Column(name = "note")
    private String note;

    @Column(name = "profile_pic")
    private String profilePic;

    //@ElementCollection for mapping for one to many relationship in table
    //@CollectionTable for creating table with employee_department with primary key @JoinColumn id
    @ElementCollection
    @CollectionTable(name = "employee_department", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "department")
    private List<String> departments;



    // employeeId not creating by user it Auto created by DB
    public  EmployeePayrollData(){ }
    public EmployeePayrollData( EmployeePayrollDTO employeePayrollDTO)
    {
        this.updateEmployeePayrollData(employeePayrollDTO);
    }
    public void updateEmployeePayrollData(EmployeePayrollDTO employeePayrollDTO){
        this.name = employeePayrollDTO.name;
        this.salary = employeePayrollDTO.salary;
        this.gender = employeePayrollDTO.gender;
        this.note = employeePayrollDTO.note;
        this.startDate = employeePayrollDTO.startDate;
        this.profilePic = employeePayrollDTO.profilePic;
        this.departments = employeePayrollDTO.departments;
    }

}
