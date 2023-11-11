package com.example.demo.student;

import jakarta.persistence.*;
import lombok.*;


@Data
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "DEPT_SEQ_GEN", sequenceName = "DEPT_SEQ")
@Entity
public class Dept {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEPT_SEQ")
    private Long id;
    @Column(name = "dept_code", unique = true)
    private String deptCode;
    private String name;
}
