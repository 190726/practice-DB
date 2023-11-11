package com.example.demo.student;

import com.github.javafaker.Faker;
import nonapi.io.github.classgraph.json.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

//@Component
public class DataLoad implements CommandLineRunner {

    @Autowired DeptRepository deptRepository;
    @Autowired EmpRepository empRepository;

    @Override
    public void run(String... args) throws Exception {
        Dept dept0 = Dept.builder().deptCode("A001").name("인사팀").build();
        Dept dept1 = Dept.builder().deptCode("A002").name("총무팀").build();
        Dept dept2 = Dept.builder().deptCode("A003").name("영업팀").build();
        Dept dept3 = Dept.builder().deptCode("A004").name("회계팀").build();

        deptRepository.save(dept0);
        deptRepository.save(dept1);
        deptRepository.save(dept2);
        deptRepository.save(dept3);

        Faker faker = new Faker(new Locale("ko"));

        IntStream.rangeClosed(1, 500)
                .forEach((i) -> {
                    System.out.println(i);

                    empRepository.save(
                            Emp.builder().empNo(String.valueOf(1000 + i))
                                    .name(faker.name().name())
                                    .dept(dept0)
                                    .build()
                    );

                    empRepository.save(
                            Emp.builder().empNo(String.valueOf(2000 + i))
                                    .name(faker.name().name())
                                    .dept(dept1)
                                    .build()
                    );

                    empRepository.save(
                            Emp.builder().empNo(String.valueOf(3000 + i))
                                    .name(faker.name().name())
                                    .dept(dept2)
                                    .build()
                    );

                    empRepository.save(
                            Emp.builder().empNo(String.valueOf(4000 + i))
                                    .name(faker.name().name())
                                    .dept(dept3)
                                    .build()
                    );

                });

        Emp emp1 = Emp.builder()
                        .empNo("0001")
                        .name("이윤아")
                        .dept(dept1).
                build();

        Emp emp2 = Emp.builder()
                .empNo("0002")
                .name("김재은")
                .dept(dept2).
                build();

        empRepository.saveAll(List.of(emp1, emp2));

    }
}
