package com.example.demo.student.persistence;

import com.example.demo.student.Dept;
import com.example.demo.student.Emp;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.IntStream;

@Profile("dev")
@Component
public class DataLoad implements CommandLineRunner {
    @Autowired DeptRepository deptRepository;

    @Autowired EmpRepository empRepository;

    @Override
    public void run(String... args) throws Exception {

        List<Dept> allDept = deptRepository.findAll();
        if(!allDept.isEmpty()) return;

        Dept dept0 = Dept.builder().deptCode("A001").name("인사팀").build();
        Dept dept1 = Dept.builder().deptCode("A002").name("총무팀").build();
        Dept dept2 = Dept.builder().deptCode("A003").name("영업팀").build();
        Dept dept3 = Dept.builder().deptCode("A004").name("회계팀").build();

        deptRepository.save(dept0);
        deptRepository.save(dept1);
        deptRepository.save(dept2);
        deptRepository.save(dept3);
        Dept[] depts = {dept0, dept1, dept2, dept3};

        IntStream.rangeClosed(1, 500)
                .forEach((i) -> {
                    empRepository.save(createEmp(String.valueOf(1000 + i), depts));
                });
    }

    public Emp createEmp(String empNo, Dept[] depts){

        String[] teamNames = {"인사팀","총무팀","영업팀","회계팀"};
        Faker faker = new Faker(new Locale("ko"));

        return Emp.builder().empNo(empNo)
                .name(faker.name().name())
                .age(new Random().nextInt(40) + 20)
                .address(faker.address().fullAddress())
                .createDateTime(LocalDateTime.now())
                .sexType(Emp.SexType.random())
                .dept(depts[new Random().nextInt(4)])
                .build();
    }
}
