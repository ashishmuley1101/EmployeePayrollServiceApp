package com.bridgelabz.employeepayrollappnew.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class EmployeePayrollDTO {

    // Validation from user input for name by regex
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message = "Employee name Invalid")
    public String name;

    // Validation from user input for salary must be greater then 500
    @Min(value = 500,message = "Minimum wages should bemore that 500")
    public long salary;

    public EmployeePayrollDTO(String name,long salary){
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "EmployeePayrollDTO : " +
                "name='" + name + '\'' +
                ", salary=" + salary ;
    }
}
