package com.example.demo.student.persistence;

import com.example.demo.student.Dept;
import com.example.demo.student.Emp;
import com.example.demo.student.web.EmpResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Iterator;
import java.util.List;

public interface EmpRepository extends JpaRepository<Emp, Long> , EmpRepositoryCustom{

    @Query("select e from Emp e join fetch e.dept d where d.deptCode=:dept and age >:age")
    Iterable<Emp> findAllByDeptAndAgeGreaterThan(
            @Param("dept") String dept,
            @Param("age") int age);

}
