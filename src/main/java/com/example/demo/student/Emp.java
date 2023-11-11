package com.example.demo.student;

import jakarta.persistence.*;
import lombok.*;

@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(name = "EMP_SEQ_GEN",
        sequenceName = "EMP_SEQ",
        initialValue = 1)
@Entity
public class Emp {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMP_SEQ_GEN")
    private Long id;
    private String empNo;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id", referencedColumnName = "dept_code")
    private Dept dept;
}

