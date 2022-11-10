package com.bridgelabz.employeepayrollappnew.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;
import lombok.Data;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;


//@ToString from lombok lib provide toString() at source time

public @ToString class EmployeePayrollDTO {

    // @Pattern Validation from user input in regex format
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message = "Employee name Invalid")
    public String name;

    // Validation from user input for salary must be greater then 500
    @Min(value = 500,message = "Minimum wages should greater than 500")
    public long salary;

    @Pattern(regexp = "male|female", message = "Gender needs to be male or female")
    public String gender;

    //@Json convert the string date into date format
    //@NotNull giving the message that not null
    //@NotBlank giving the message that not to be blank
    @JsonFormat( pattern = "dd MMM yyy")
   @NotNull(message = "startDate should not be empty..!")
   @PastOrPresent(message = "startDate should be past or today's date..!")
    public  LocalDate startDate;

   @NotBlank(message = "Note cannot be empty...! ")
    public String note;
    @NotBlank(message = "profilePic cannot be empty...! ")
    public String profilePic;
    @NotNull(message = "department should not be empty...!")
    public List<String> departments;
}
