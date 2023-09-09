package com.ramzan.departmentservice.controller;

import com.netflix.discovery.converters.Auto;
import com.ramzan.departmentservice.client.EmployeeClient;
import com.ramzan.departmentservice.model.Department;
import com.ramzan.departmentservice.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    // need logger to publish all the log to zipkin
    private static final Logger LOGGER
            = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeClient employeeClient;



    // add department
    @PostMapping
    public Department add(@RequestBody Department department){
        LOGGER.info("Department add: {}", department);
        return departmentRepository.addDepartment(department);
    }

    // list all department
    @GetMapping
    public List<Department> findAll(){
        LOGGER.info("Department find");
        return departmentRepository.findAll();
    }

    // find department by id
    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id){
        LOGGER.info("Department find: id={}",id);
        return departmentRepository.findById(id);
    }

    // Get all department with employees
    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployees(){
        LOGGER.info("Department find");
        List<Department> departments = departmentRepository.findAll();
        departments.forEach(department -> department.setEmployees(
                employeeClient.findByDepartment(department.getId())
        ));
        return departments;
    }


}
