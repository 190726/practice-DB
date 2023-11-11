package com.example.demo.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

public record EmpResponse(String empId, String empName, String deptName) {
}
