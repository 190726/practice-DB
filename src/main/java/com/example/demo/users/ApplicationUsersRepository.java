package com.example.demo.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUsersRepository extends JpaRepository<ApplicationUsers, Long> {
    ApplicationUsers findByUsername(String username);
}