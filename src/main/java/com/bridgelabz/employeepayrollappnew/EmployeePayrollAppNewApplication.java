package com.bridgelabz.employeepayrollappnew;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

// Use lombak Lib for Logging @Slf4j
@Slf4j
public class EmployeePayrollAppNewApplication {

    @Bean
    public ModelMapper modelMapper(){
        return  new ModelMapper();
    }

    public static void main(String[] args) {

//       ConfigurableApplicationContext passing to log.info for Employee Payroll App Started in DEV Environment

       ConfigurableApplicationContext context =  SpringApplication.run(EmployeePayrollAppNewApplication.class, args);
        log.info("Employee Payroll App Started in {} Environment",
                context.getEnvironment().getProperty("environment"));
        log.info("Employee Payroll DB user is {} ",
                context.getEnvironment().getProperty("spring.datasource.username"));
    }

}
