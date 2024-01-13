package com.zaio.DTO;

public class EmployeeSalaryDTO {
    private Long id;
    private double salary;
    private String departmentName;

    public EmployeeSalaryDTO() {
    }

    public EmployeeSalaryDTO(Long id, double salary, String departmentName) {
        this.id = id;
        this.salary = salary;
        this.departmentName = departmentName;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}

