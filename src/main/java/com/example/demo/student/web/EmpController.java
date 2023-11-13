package com.example.demo.student.web;

import com.example.demo.student.Dept;
import com.example.demo.student.persistence.DeptRepository;
import com.example.demo.student.Emp;
import com.example.demo.student.persistence.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
public class EmpController {

    @Value("${app.name}")
    private String appName;

    @Autowired
    EmpRepository empRepository;

    @Autowired
    DeptRepository deptRepository;

    @GetMapping("/emp/queryMethod/1")
    Iterable<Emp> findBySexType(@RequestParam String sexType){
        return empRepository.findBySexTypeOrderByCreateDateTimeDesc(Emp.SexType.valueOf(sexType));
    }

    @GetMapping("/emp/{deptCode}")
    List<EmpResponse> findBy(@PathVariable String deptCode){
        return empRepository.findByDeptCode(deptCode);
    }

    @GetMapping("/emp/where")
    List<EmpResponse> findWhere(@RequestParam Integer age, @RequestParam String region){
        return empRepository.findByAgeAndRegion(age, region);
    }

    @GetMapping("/emp/where2")
    Page<Emp> findWhere(@RequestParam String deptCode, @RequestParam String region, Pageable pageable){
        return empRepository.findAllByDeptAndRegionOrderByNameAsc(deptCode, region, pageable);
    }

    @GetMapping("/emp/score")
    List<EmpScoreResponse> findScore(@RequestParam Integer score){
        return empRepository.findByScore(score);
    }

    @GetMapping("/emp/query")
    Iterable<Emp> findQuery(@RequestParam String deptCode, @RequestParam Integer age){
        System.out.println(appName);
        return empRepository.findAllByDeptAndAgeGreaterThan(deptCode, age);
    }

    @PostMapping("/emp")
    Emp create(@RequestBody EmpRequest request){
        Dept dept = deptRepository.findByDeptCode(request.deptcode());

        return empRepository.save(Emp.builder()
                        .empNo(request.empno())
                        .name(request.name())
                        .dept(dept)
                .build());
    }
}