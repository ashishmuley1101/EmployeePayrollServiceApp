package com.bridgelabz.employeepayrollappnew.controller;


import com.bridgelabz.employeepayrollappnew.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollappnew.dto.ResponseDTO;
import com.bridgelabz.employeepayrollappnew.model.EmployeePayrollData;
import com.bridgelabz.employeepayrollappnew.service.IEmployeePayrollService;
import com.bridgelabz.employeepayrollappnew.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@RequestMapping("/employeepayrollservice")
@Slf4j
public class EmployeePayrollController {

    @Autowired
    private IEmployeePayrollService employeePayrollService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    // Getting employee data in the form of JSON
    @RequestMapping(value = {"", "/", "/get"})
    public ResponseEntity<ResponseDTO> getEmployeePayrollData() {
        List<EmployeePayrollData> empDataList = null;
        empDataList = employeePayrollService.getEmployeePayrollData();
        ResponseDTO respDTO = new ResponseDTO("Get Call Successful", empDataList);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    // Getting employee data using id @GetMapping
    //@PathVariable for taking the user I/p as employee Id
    @GetMapping("/get/{empId}")
    public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("empId") int empId) {
        EmployeePayrollData empPayrollData = null;
        empPayrollData = employeePayrollService.getEmployeePayrollDataById(empId);
        ResponseDTO respDTO = new ResponseDTO("Get Call for Id Successful ", empPayrollData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    //getting all data with the @PathVariable department
    @GetMapping("/department/{department}")
    public ResponseEntity<ResponseDTO> getDepartmentById(@PathVariable("department") String department){
        List<EmployeePayrollData> empDataList = null;
        empDataList =employeePayrollService.getEmployeePayrollDataByDepartment(department);
        ResponseDTO respDTO = new ResponseDTO("Get call success", empDataList);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    // Creating the employee data using @PostMapping Method
    //@Valid for validating the @RequestBody request body which given by the user
    //@PathVariable for taking the user I/p as employee Id
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createEmployeePayrollData(@Valid @RequestBody EmployeePayrollDTO empPayrollDTO) {
        log.debug("Employee DTO :"+empPayrollDTO.toString());
        EmployeePayrollData empData = null;
        empData = employeePayrollService.createEmployeePayrollData(empPayrollDTO);
        ResponseDTO respDTO = new ResponseDTO("Created Employee Payroll Data Successfully ", empData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
    //@PathVariable for taking the user I/p as employee Id
    //Updating employee data using @PutMapping
    //@Valid for validating the @RequestBody request body which given by the user
    //RequestMapping for getting the user data in the form of query
    @PutMapping("/update/{empId}")
    public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@PathVariable("empId") int empId, @Valid @RequestBody EmployeePayrollDTO empPayrollDTO) {
        EmployeePayrollData empData ;
        empData = employeePayrollService.updateEmployeePayrollData(empId,empPayrollDTO);
        ResponseDTO respDTO = new ResponseDTO("Updated Employee Payroll Data Successfully", empData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    //Delete employee data using @DeleteMapping Method

    //@PathVariable for taking the user I/p as employee Id
    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable("empId") int empId) {
        employeePayrollService.deleteEmployeePayrollData(empId);
        ResponseDTO respDTO = new ResponseDTO("Deleted Successfully", "Deleted id: " + empId);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody EmployeePayrollDTO employeePayrollDTO) throws Exception {
        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(employeePayrollDTO.getName(), employeePayrollDTO.getGender())
            );
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("Inavalid username/gender...!!");
        }
        // Generate token for by name using jwtUtil.generateToken().
        return jwtUtil.generateToken(employeePayrollDTO.getName());
    }

}
