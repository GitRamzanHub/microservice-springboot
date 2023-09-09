package com.ramzan.departmentservice.repository;

import com.ramzan.departmentservice.model.Department;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentRepository {

    private List<Department> departments = new ArrayList<Department>();


    // adding department to the list
    public Department addDepartment(Department department){
        departments.add(department);
        return department;
    }


    // find department from given id
    public Department findById(Long id){
        return departments.stream()
                .filter(department -> department.getId().equals(id))
                .findFirst().orElseThrow();
    }

    // return list of all departments
    public List<Department> findAll(){
        return departments;
    }



}
