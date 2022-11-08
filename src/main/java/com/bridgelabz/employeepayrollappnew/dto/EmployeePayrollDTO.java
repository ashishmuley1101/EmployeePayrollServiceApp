package com.bridgelabz.employeepayrollappnew.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

//@ToString from lombok lib provide toString() at source time

public @ToString class EmployeePayrollDTO {

    // Validation from user input for name by regex
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message = "Employee name Invalid")
    public String name;

    // Validation from user input for salary must be greater then 500
    @Min(value = 500,message = "Minimum wages should greater than 500")
    public long salary;

    public String gender;

    //@Json convert the string date into date format
   @JsonFormat(pattern = "dd MMM yyyy")
    public LocalDate startDate;
    public String note;
    public String profilePic;
    public List<String> departments;
}
