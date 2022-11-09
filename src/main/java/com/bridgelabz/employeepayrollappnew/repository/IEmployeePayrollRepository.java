package com.bridgelabz.employeepayrollappnew.repository;

import com.bridgelabz.employeepayrollappnew.model.EmployeePayrollData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeePayrollRepository extends JpaRepository<EmployeePayrollData,Integer> {
}
