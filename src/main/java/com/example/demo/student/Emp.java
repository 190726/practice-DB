package com.example.demo.student;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Random;

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
    private Integer age;
    private String name;
    @Enumerated(EnumType.STRING)
    private SexType sexType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id", referencedColumnName = "dept_code")
    private Dept dept;
    private LocalDateTime createDateTime;
    private String address;

    @RequiredArgsConstructor
    @Getter
    public static enum SexType{
        F("여"), M("남");
        private final String text;
        public static SexType random(){
            return new Random().nextInt(2) == 0
                    ? SexType.F:SexType.M;
        }
    }
}
