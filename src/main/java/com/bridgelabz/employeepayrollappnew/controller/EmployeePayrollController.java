package com.bridgelabz.employeepayrollappnew.controller;


import com.bridgelabz.employeepayrollappnew.dto.EmployeePayrollDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {

    @RequestMapping(value = {"", "/", "/get"})
    public ResponseEntity<String> getEmployeePayrollData() {
        return new ResponseEntity<String>("Get call success", HttpStatus.OK);
    }

    // Getting Employee data using id @GetMapping

    @GetMapping("/get/{empId}")
    public ResponseEntity<String> getEmployeePayrollData(@PathVariable("empId") int empId) {
        return new ResponseEntity<String>("Get call success for id: "+empId, HttpStatus.OK);
    }


    //Creating employee data using @PostMapping

    @PostMapping(path = "/create")
    public ResponseEntity<String> addEmployeePayrollData(@RequestBody EmployeePayrollDTO empPayrollDTO) {
        return new ResponseEntity<String>("Created employee payroll data for: "+empPayrollDTO, HttpStatus.OK);
    }

    //Updating employee data using @PutMapping

    @PutMapping(path = "/update")
    public ResponseEntity<String> updateEmployeePayrollData(@PathVariable("empId") int empId,
                                                            @RequestBody EmployeePayrollDTO empPayrollDTO) {
        return new ResponseEntity<String>("Updated employee payroll data for: "+empPayrollDTO, HttpStatus.OK);
    }

    //Deleting employee data using @DeleteMapping

    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<String> deleteEmployeePayrollData(@PathVariable("empId") int empId) {
        return new ResponseEntity<String>("Delete call success for id: "+empId, HttpStatus.OK);
    }

}
