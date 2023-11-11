package com.example.demo.student;

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
}
