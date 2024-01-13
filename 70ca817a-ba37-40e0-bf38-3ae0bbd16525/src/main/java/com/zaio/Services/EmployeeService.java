package com.zaio.Services;

import com.zaio.DTO.EmployeeSalaryDTO;
import com.zaio.Entity.Department;
import com.zaio.Entity.Employee;
import com.zaio.Repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final DepartmentService departmentService;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentService departmentService) {
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
    }

    public Employee save(Employee employee) {
        Department employeeDepartment = employee.getDepartment();
        Optional<Department> department = Optional.of(new Department());
        if (employeeDepartment != null && employeeDepartment.getId() != null) {
            // Department is already persisted, attach it to the current session
            department = departmentService.getOptionalById(employeeDepartment.getId());
            if (department.isEmpty()){
                // Department is new, save it first
                employeeDepartment = departmentService.save(employeeDepartment);
            }
            else {
                employeeDepartment = department.get();
            }
        }
        else {
            return null;
        }
        // Set the persisted or attached department to the employee
        employee.setDepartment(employeeDepartment);
        return employeeRepository.save(employee);
    }

    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()){
            return employee;
        }
        return null;
    }

    public void delete(Employee existingEmployee) {
        employeeRepository.delete(existingEmployee);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public List<EmployeeSalaryDTO> findAllEmployeeSalaries() {
        List<Employee> employees = this.findAll();

        List<EmployeeSalaryDTO> employeeSalaryDTOs = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeSalaryDTO employeeSalaryDTO = new EmployeeSalaryDTO(
                    employee.getId(),
                    employee.getSalary(),
                    employee.getDepartment().getDepartmentName()
            );
            employeeSalaryDTOs.add(employeeSalaryDTO);
        }
        return employeeSalaryDTOs;
    }
}
