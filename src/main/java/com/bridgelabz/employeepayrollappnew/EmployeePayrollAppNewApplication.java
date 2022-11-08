package com.bridgelabz.employeepayrollappnew;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

// Use lombak Lib for Logging @Slf4j
@Slf4j
public class EmployeePayrollAppNewApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeePayrollAppNewApplication.class, args);
        log.info("Employee Payroll App Started");
    }

}
