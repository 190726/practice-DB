package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepository extends JpaRepository<Dept, Long> {
    Dept findByDeptCode(String deptCode);
}
