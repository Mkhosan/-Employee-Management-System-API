package com.zaio.Controllers;

import com.zaio.DTO.AttendanceRequestDTO;
import com.zaio.Entity.Attendance;
import com.zaio.Entity.Employee;
import com.zaio.MessageHandling.Response;
import com.zaio.Services.AttendanceService;
import com.zaio.Services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@RestController
public class AttendanceController {

    // Your other code and dependencies
    private final EmployeeService employeeService;

    private final AttendanceService attendanceService;

    public AttendanceController(EmployeeService employeeService, AttendanceService attendanceService) {
        this.employeeService = employeeService;
        this.attendanceService = attendanceService;
    }

    /* write your function for @PostMapping("/attendance") annotation maps the method createAttendance to handle
    HTTP POST requests with the "/attendance" endpoint
    Retrieve the employee from the database using the provided employeeId.
    If the employee is not found, return a suitable error response with an appropriate status code and error message.
    If the employee is found, create a new Attendance object with the extracted details and the retrieved employee.
    Save the Attendance object in the database.
    Return a response in the JSON format.
    
     */

}

