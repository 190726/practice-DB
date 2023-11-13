package com.example.demo.student.persistence;

import com.example.demo.QueryDslConfig;
import com.example.demo.student.Dept;
import com.example.demo.student.Emp;
import com.example.demo.student.web.EmpResponse;
import com.github.javafaker.Faker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;


@ActiveProfiles("test")
@DataJpaTest
@Import(QueryDslConfig.class)
class EmpRepositoryTest {

    @Autowired EmpRepository empRepository;
    @Autowired DeptRepository deptRepository;

    @DisplayName("부서 조건 일치 & 특정 나이 보다 큰 직원 목록 조회")
    @Test
    void findAllByDeptAndAgeGreaterThan(){

        List<Dept> sampleDept= createSampleDept();
        deptRepository.saveAll(sampleDept);
        empRepository.saveAll(createSampleEmp(sampleDept));

        Iterable<Emp> result = empRepository.findAllByDeptAndAgeGreaterThan("A001", 30);

        assertThat(result).hasSize(1);
        assertThat(result).extracting("empNo", "age", "dept.name")
                          .contains(
                                  tuple("0004", 46, "인사팀")
                          );
    }

    @DisplayName("부서명과 지역으로 검색 후, 팀원명순으로 정렬")
    @Test
    void findAllByDeptAndRegionOrderByNameAsc(){
        //given
        List<Dept> sampleDept= createSampleDept();
        deptRepository.saveAll(sampleDept);
        empRepository.saveAll(createSampleEmp(sampleDept));
        //when
        Page<Emp> resultPage = empRepository.findAllByDeptAndRegionOrderByNameAsc("A001", "서울", PageRequest.of(0, 5));
        List<Emp> list = resultPage.stream().toList();
        //then
        assertThat(list).hasSize(2);
        assertThat(list).extracting("name", "age", "dept.name")
                .containsExactly(
                        tuple("강길동",20,"인사팀"),
                        tuple("박길동",46,"인사팀")
                        );
    }

    @Test
    void findByDeptCode(){
        //given
        List<Dept> sampleDept= createSampleDept();
        deptRepository.saveAll(sampleDept);
        empRepository.saveAll(createSampleEmp(sampleDept));
        //when
        List<EmpResponse> response = empRepository.findByDeptCode("A001");
        //then
        assertThat(response).hasSize(2);
        assertThat(response).extracting("empName", "empId", "deptName")
                .containsExactlyInAnyOrder(
                        tuple("강길동","0001","인사팀"),
                        tuple("박길동","0004","인사팀")
                );
    }

    public List<Dept> createSampleDept(){
        return List.of(
             Dept.builder()
                .deptCode("A001")
                .name("인사팀")
                .build(),
            Dept.builder()
                .deptCode("A002")
                .name("회계팀")
                .build());
    }

    public List<Emp> createSampleEmp(List<Dept> depts){
        Dept dept1 = depts.get(0);
        Dept dept2 = depts.get(1);
        return List.of(
            createEmp("0001","강길동",20,"서울시 강북구", Emp.SexType.F, dept1),
            createEmp("0002","홍길동",25,"남양주시 다산동", Emp.SexType.F, dept2),
            createEmp("0003","김길동",35,"경기도 광주", Emp.SexType.M, dept2),
            createEmp("0004","박길동",46,"서울시 강남구", Emp.SexType.F, dept1),
            createEmp("0005","차길동",55,"서울시 도봉구", Emp.SexType.M, dept2)
        );
    }

    public Emp createEmp(String empNo,String empName, int age, String address, Emp.SexType sexType, Dept dept){
        return Emp.builder().empNo(empNo)
                .name(empName)
                .age(age)
                .address(address)
                .createDateTime(LocalDateTime.now())
                .sexType(sexType)
                .dept(dept)
                .build();
    }
}