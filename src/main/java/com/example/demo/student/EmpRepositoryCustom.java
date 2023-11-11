package com.example.demo.student;

import java.util.List;

public interface EmpRepositoryCustom {
    List<EmpResponse> findByDeptCode(String deptCode);
}
