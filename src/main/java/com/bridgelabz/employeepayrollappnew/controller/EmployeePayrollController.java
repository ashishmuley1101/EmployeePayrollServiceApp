package com.bridgelabz.employeepayrollappnew.controller;


import com.bridgelabz.employeepayrollappnew.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollappnew.dto.ResponseDTO;
import com.bridgelabz.employeepayrollappnew.model.EmployeePayrollData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {

    // Getting employee data in the form of JSON
    @RequestMapping(value = {"", "/", "/get"})
    public ResponseEntity<ResponseDTO> getEmployeePayrollData() {
        EmployeePayrollData employeePayrollData = null;
        employeePayrollData = new EmployeePayrollData(1, new EmployeePayrollDTO("Tom", 25000));
        ResponseDTO respDTO = new ResponseDTO("Get Call Success", employeePayrollData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    // Getting employee data using id @GetMapping
    @GetMapping("/get/{empId}")
    public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("empId") int empId) {
        EmployeePayrollData employeePayrollData = null;
        employeePayrollData = new EmployeePayrollData(empId, new EmployeePayrollDTO("Jerry", 30000));
        ResponseDTO respDTO = new ResponseDTO("Get Call Success", employeePayrollData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    // Creating the employee data using @PostMapping Method
    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDTO> addEmployeePayrollData(@RequestBody EmployeePayrollDTO empPayrollDTO) {
        EmployeePayrollData employeePayrollData = null;
        employeePayrollData = new EmployeePayrollData(1, empPayrollDTO);
        ResponseDTO respDTO = new ResponseDTO("Created Employee Data Successfully!", empPayrollDTO);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    //Updating employee data using @PutMapping
    @PutMapping(path = "/update/{empId}")
    public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@PathVariable("empId") int empId,
                                                                 @RequestBody EmployeePayrollDTO empPayrollDTO) {
        EmployeePayrollData employeePayrollData = null;
        employeePayrollData = new EmployeePayrollData(empId, empPayrollDTO);
        ResponseDTO respDTO = new ResponseDTO("Updated Employee payroll Data Successfully! ", empPayrollDTO);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    //Delete employee data using @DeleteMapping Method
    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable("empId") int empId) {
        ResponseDTO respDTO = new ResponseDTO("Deleted Employee Payroll Data Successfully..! ", "Deleted Id :"+empId);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }


}
