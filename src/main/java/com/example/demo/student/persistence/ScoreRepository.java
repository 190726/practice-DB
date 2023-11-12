package com.example.demo.student.persistence;

import com.example.demo.student.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Long> {
}
