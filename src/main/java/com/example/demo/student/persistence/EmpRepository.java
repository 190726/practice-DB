package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepository extends JpaRepository<Emp, Long> , EmpRepositoryCustom{
}
