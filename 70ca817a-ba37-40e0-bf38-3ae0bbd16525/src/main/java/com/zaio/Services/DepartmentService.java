package com.zaio.Services;

import com.zaio.Entity.Department;
import com.zaio.Repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    public Department getDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentName(departmentName);
    }

    public Department getById(Long id) {
        return departmentRepository.getById(id);
    }

    public Optional<Department> getOptionalById(Long id) {
        return departmentRepository.findById(id);
    }
}
