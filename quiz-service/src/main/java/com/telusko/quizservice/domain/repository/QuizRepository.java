package com.telusko.quizservice.domain.repository;


import com.telusko.quizservice.domain.models.QuizModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<QuizModel, Integer> {
}
