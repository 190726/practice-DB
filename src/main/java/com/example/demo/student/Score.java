package com.example.demo.student;

import jakarta.persistence.*;
import lombok.*;

@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(name = "SCORE_SEQ_GEN",sequenceName = "SCORE_SEQ")
@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCORE_SEQ")
    private Long id;
    private String empNo;
    private String subject;
    private Double score;
}