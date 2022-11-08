package com.bridgelabz.employeepayrollappnew;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication

// Use lombak Lib for Logging @Slf4j
@Slf4j
public class EmployeePayrollAppNewApplication {

    public static void main(String[] args) {

//       ConfigurableApplicationContext passing to log.info for Employee Payroll App Started in DEV Environment

       ConfigurableApplicationContext context =  SpringApplication.run(EmployeePayrollAppNewApplication.class, args);
        log.info("Employee Payroll App Started in {} Environment",
                context.getEnvironment().getProperty("environment"));
    }

}
