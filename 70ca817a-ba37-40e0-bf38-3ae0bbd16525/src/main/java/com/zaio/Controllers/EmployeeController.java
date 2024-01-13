package com.zaio.Controllers;

import com.zaio.DTO.EmployeeSalaryDTO;
import com.zaio.Entity.Employee;
import com.zaio.MessageHandling.Response;
import com.zaio.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    public ResponseEntity<Response> createEmployee(@RequestBody Employee employee) {
        try {
            // Validate the incoming employee data
            if (employee.getName() == null || employee.getName().isEmpty() ||
                    employee.getDepartment() == null ||
                    employee.getDesignation() == null || employee.getDesignation().isEmpty()) {
                return ResponseEntity.badRequest().body(new Response("Invalid employee data. Please provide all required fields."));
            }

            // Save the employee in the database
            Employee createdEmployee = employeeService.save(employee);

            // Return the created employee in the response with HTTP status 201
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("employee with id "+employee.getId() + " has been created"));
        } catch (Exception e) {
            // Handle any unexpected errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("An unexpected error occurred while creating the employee."));
        }
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.findAllEmployee();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Object> updateEmployeeById(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        Optional<Employee> existingEmployeeOptional = employeeService.findById(id);

        Employee existingEmployee = existingEmployeeOptional.get();

        // Update the employee fields
        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setDepartment(updatedEmployee.getDepartment());
        existingEmployee.setDesignation(updatedEmployee.getDesignation());
        existingEmployee.setSalary(updatedEmployee.getSalary());

        // Save the updated employee in the database
        Employee updatedEmployeeObj = employeeService.save(existingEmployee);

        return ResponseEntity.status(HttpStatus.OK).body(updatedEmployeeObj);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Object> deleteEmployeeById(@PathVariable Long id) {
        Optional<Employee> existingEmployeeOptional = employeeService.findById(id);
        Employee existingEmployee = existingEmployeeOptional.get();
        employeeService.delete(existingEmployee);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("Data has been Created"));
    }

    @GetMapping("/employees/salary")
    public ResponseEntity<Object> getAllEmployeeSalaries() {
        List<EmployeeSalaryDTO> employeeSalaryDTOs = employeeService.findAllEmployeeSalaries();
        if (employeeSalaryDTOs.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("no employees found"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(employeeSalaryDTOs);
    }

}

