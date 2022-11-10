package com.bridgelabz.employeepayrollappnew.repository;

import com.bridgelabz.employeepayrollappnew.model.EmployeePayrollData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IEmployeePayrollRepository extends JpaRepository<EmployeePayrollData,Integer> {


    //@Query for custom query annotation to retrieve the data
    @Query(value = "select * from employee_payrolll, employee_department where employee_id = id and department = :department",nativeQuery = true)
    List<EmployeePayrollData> findEmployeesByDepartment(String department);

}
