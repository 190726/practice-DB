package com.example.demo.student.persistence;

import com.example.demo.student.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepository extends JpaRepository<Dept, Long> {
    Dept findByDeptCode(String deptCode);
}