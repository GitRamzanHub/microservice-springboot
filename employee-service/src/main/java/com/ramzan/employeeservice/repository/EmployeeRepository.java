package com.ramzan.employeeservice.repository;

import com.ramzan.employeeservice.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {

    private List<Employee> employees = new ArrayList<>();


    // add employee
    public Employee add(Employee employee){
        employees.add(employee);
        return employee;
    }

    // find employee by id
    public Employee findById(Long id){
        return employees.stream()
                .filter(a -> a.id().equals(id))
                .findFirst().orElseThrow();
    }

    // find all the employee
    public List<Employee> findAll(){
        return employees;
    }

    // find all employee based on department id
    public List<Employee> findByDepartment(Long departmentId){
        return employees.stream()
                .filter(a -> a.departmentId().equals(departmentId))
                .toList();
    }

}
