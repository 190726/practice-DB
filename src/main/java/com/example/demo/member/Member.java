package com.example.demo.member;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@Setter(AccessLevel.PRIVATE)
@Entity
@Table(name = "Members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private Integer age;
    private RoleType roleType;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String description;
}