package com.example.demo.users;

import jakarta.persistence.*;
import lombok.*;

@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(name = "USERS_SEQ_GEN",
        sequenceName = "USERS_SEQ",
        initialValue = 1)
@Table(name = "CT_USERS")
@Entity
public class ApplicationUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS")
    private Long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private StateType verified;
    @Enumerated(EnumType.STRING)
    private StateType locked;
    @Column(name="ACC_CRED_EXPIRED")
    @Enumerated(EnumType.STRING)
    private StateType accountCredentialExpired;
}
