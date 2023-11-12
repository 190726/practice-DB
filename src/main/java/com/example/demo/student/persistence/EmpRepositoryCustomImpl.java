package com.example.demo.student.persistence;

import com.example.demo.student.Emp;
import com.example.demo.student.QDept;
import com.example.demo.student.QEmp;
import com.example.demo.student.QScore;
import com.example.demo.student.web.EmpResponse;
import com.example.demo.student.web.EmpScoreResponse;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmpRepositoryCustomImpl implements EmpRepositoryCustom {

    @Autowired
    JPAQueryFactory queryFactory;
    @Override
    public List<EmpResponse> findByDeptCode(String deptCode) {
        QEmp emp = QEmp.emp;
        QDept dept = QDept.dept;

        JPAQuery<Emp> result = queryFactory.selectFrom(emp)
                .join(dept)
                .on(emp.dept.deptCode.eq(dept.deptCode))
                .where(emp.dept.deptCode.eq(deptCode));

        return result
                .fetchJoin()
                .fetch()
                .stream()
                .map(emp1 ->
                        new EmpResponse(emp1.getEmpNo(),
                                        emp1.getName(),
                                        emp1.getDept().getName()))
                .toList();
    }

    @Override
    public List<EmpResponse> findByAgeAndRegion(Integer age, String region) {

        QEmp emp = QEmp.emp;
        QDept dept = QDept.dept;

        JPAQuery<Tuple> query = queryFactory.select(
                        emp.empNo,
                        emp.age,
                        emp.name,
                        emp.sexType,
                        emp.address,
                        dept.name
                ).from(emp).join(dept).on(emp.dept.eq(dept))
                .where(emp.age.gt(age),
                        emp.address.contains(region)
                )
                .orderBy(emp.age.asc(), emp.name.desc());

        return query.fetchJoin().fetch()
                .stream().map(tuple ->
                {
                        return new EmpResponse(
                        tuple.get(emp.empNo),
                        tuple.get(emp.age),
                        tuple.get(emp.name),
                        tuple.get(3, Emp.SexType.class).getText(),
                        tuple.get(emp.address),
                        tuple.get(dept.name));
                }).toList();
    }

    @Override
    public List<EmpScoreResponse> findByScore(Integer num) {
        QEmp emp = QEmp.emp;
        QScore score = QScore.score1;

        JPAQuery<Tuple> query = queryFactory.select(
                        emp.empNo,
                        emp.name,
                        score.score
                ).from(emp).join(score).on(emp.empNo.eq(score.empNo))
                .where(score.score.gt(num))
                .orderBy(emp.name.desc());

        return query.fetchJoin().fetch()
                .stream().map(tuple ->
                {
                    return new EmpScoreResponse(
                            tuple.get(emp.empNo),
                            tuple.get(emp.name),
                            tuple.get(2, Double.class));
                }).toList();
    }
}