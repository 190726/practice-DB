package com.example.demo.student.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.hibernate.type.SetType;

public record EmpResponse(String empId,
                          Integer age,
                          String empName,
                          String sex,
                          String address,
                          String deptName
) {
    public EmpResponse(String empId, String empName, String deptName){
        this(empId, 0, empName, null, "", deptName);
    }
}
