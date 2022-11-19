package com.bridgelabz.employeepayrollappnew.service;

import com.bridgelabz.employeepayrollappnew.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollappnew.exception.EmployeePayrollException;
import com.bridgelabz.employeepayrollappnew.model.EmployeePayrollData;
import com.bridgelabz.employeepayrollappnew.repository.IEmployeePayrollRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmployeePayrollService implements IEmployeePayrollService, UserDetailsService {

    //@Autowired IEmployeePayrollService in service class
    @Autowired
    private IEmployeePayrollRepository employeePayrollRepository;

    @Autowired
    private ModelMapper modelMapper;

    private List<EmployeePayrollData> employeePayrollList = new ArrayList<>();

    @Override
    public List<EmployeePayrollData> getEmployeePayrollData() {

        return employeePayrollRepository.findAll();
    }

    // Throwing Exception when employee id not in Employee Payroll App while retrieving from method
    @Override
    public EmployeePayrollData getEmployeePayrollDataById(int empId) {
        return employeePayrollRepository.findById(empId).orElseThrow(()->new EmployeePayrollException
                ("Employee with employee id"+empId+" does not exits.."));

    }


    @Override
    public List<EmployeePayrollData> getEmployeePayrollDataByDepartment(String department) {
        return employeePayrollRepository.findEmployeesByDepartment(department);
    }


    // Not Created with Id its Auto Created by DB
    @Override
    public EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO empPayrollDTO) {
        EmployeePayrollData empData ;
        empData= new EmployeePayrollData(empPayrollDTO);
        log.debug("Emp Data: "+empData.toString());
        return employeePayrollRepository.save(empData);
    }

    @Override
    public EmployeePayrollData updateEmployeePayrollData(int empId,EmployeePayrollDTO empPayrollDTO) {
        EmployeePayrollData employeePayrollData = this.getEmployeePayrollDataById(empId);
        employeePayrollData.updateEmployeePayrollData(empPayrollDTO);
        return employeePayrollRepository.save(employeePayrollData);
    }



    @Override
    public void deleteEmployeePayrollData(int empId) {
        EmployeePayrollData employeePayrollData=this.getEmployeePayrollDataById(empId);
        employeePayrollRepository.delete(employeePayrollData);
    }

    //Override loadUserByUsername(name) from UserDetailsService header passes it will go in DB and fetch the user Object which provided
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        // fetch user name and city from DB by employeePayrollRepo

        EmployeePayrollData addressBookData = employeePayrollRepository.findByName(name);

        if (addressBookData == null){
            throw new UsernameNotFoundException("Bad credentials");
        }else {
            // map the name and city from DB to Spring Framework security for checking the user object valid or not.
            return new org.springframework.security.core.userdetails.User(addressBookData.getName(), addressBookData.getGender(), new ArrayList<>());
        }
    }
}
