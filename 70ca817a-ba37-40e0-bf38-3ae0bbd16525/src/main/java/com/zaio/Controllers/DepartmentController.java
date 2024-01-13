package com.zaio.Controllers;

import com.zaio.Entity.Department;
import com.zaio.MessageHandling.Response;
import com.zaio.Services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/departments")
    public ResponseEntity<Object> createDepartment(@RequestBody Department department) {
        try {
            // Validate the incoming employee data
            if (department.getId() == null ||
                department.getDepartmentName() == null || department.getDepartmentName().isEmpty()) {
                return ResponseEntity.badRequest().body(new Response("Invalid department  data. Please provide all required fields."));
            }

            // Save the employee in the database
            Department createdDepartment = departmentService.save(department);

            // Return the created employee in the response with HTTP status 201
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDepartment);
        } catch (Exception e) {
            // Handle any unexpected errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while creating the employee.");
        }
    }



}
