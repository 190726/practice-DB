package com.example.demo.student.persistence;

import com.example.demo.student.web.EmpResponse;
import com.example.demo.student.web.EmpScoreResponse;

import java.util.List;

public interface EmpRepositoryCustom {
    List<EmpResponse> findByDeptCode(String deptCode);

    List<EmpResponse> findByAgeAndRegion(Integer age, String region);

    List<EmpScoreResponse> findByScore(Integer score);
}
