package com.bridgelabz.employeepayrollappnew.service;

import com.bridgelabz.employeepayrollappnew.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollappnew.exception.EmployeePayrollException;
import com.bridgelabz.employeepayrollappnew.model.EmployeePayrollData;
import com.bridgelabz.employeepayrollappnew.repository.IEmployeePayrollRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmployeePayrollService implements IEmployeePayrollService{

    //@Autowired IEmployeePayrollService in service class
    @Autowired
    private IEmployeePayrollRepository employeePayrollRepository;
    private List<EmployeePayrollData> employeePayrollList = new ArrayList<>();

    @Override
    public List<EmployeePayrollData> getEmployeePayrollData() {
        return employeePayrollList;
    }

    // Throwing Exception when employee id not in Employee Payroll App while retrieving from method
    @Override
    public EmployeePayrollData getEmployeePayrollDataById(int empId) {
        return employeePayrollList.stream().filter(empData->empData.getEmployeeId() == empId )
                .findFirst().orElseThrow(()->new EmployeePayrollException("Employee Not Found..!!"));

    }

    // Not Created with Id its Auto Created by DB
    @Override
    public EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO empPayrollDTO) {
        EmployeePayrollData empData = null;
        empData= new EmployeePayrollData(empPayrollDTO);
        log.debug("Emp Data: "+empData.toString());
        employeePayrollList.add(empData);
        return employeePayrollRepository.save(empData);
    }

    @Override
    public EmployeePayrollData updateEmployeePayrollData(int empId,EmployeePayrollDTO empPayrollDTO) {
        EmployeePayrollData empData = this.getEmployeePayrollDataById(empId);
        empData.setName(empPayrollDTO.name);
        empData.setSalary(empPayrollDTO.salary);
        employeePayrollList.set(empId-1, empData);
        return empData;
    }

    @Override
    public void deleteEmployeePayrollData(int empId) {
        employeePayrollList.remove(empId-1);

    }
}
