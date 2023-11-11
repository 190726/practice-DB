package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmpController {

    @Autowired EmpRepository repository;

    @Autowired DeptRepository deptRepository;

    @GetMapping("/emp/{deptCode}")
    List<EmpResponse> findBy(@PathVariable String deptCode){
        return repository.findByDeptCode(deptCode);
    }

    @GetMapping("/emp/where")
    List<EmpResponse> findWhere(@RequestParam Integer age, @RequestParam String region){

        System.out.println(age + " / " + region);

        return repository.findByAgeAndRegion(age, region);
    }

    @PostMapping("/emp")
    String create(@RequestBody EmpRequest request){
        Dept dept = deptRepository.findByDeptCode(request.deptcode());

        repository.save(Emp.builder()
                        .empNo(request.empno())
                        .name(request.name())
                        .dept(dept)
                .build());
        return "succes";
    }

}