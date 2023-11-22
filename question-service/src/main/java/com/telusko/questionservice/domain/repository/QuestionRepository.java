package com.telusko.questionservice.domain.repository;


import com.telusko.questionservice.domain.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);

    @Query("SELECT q.id FROM Question q WHERE q.category = :category ORDER BY RANDOM() LIMIT :numQ")
    List<Integer> findRandomQuestionsByCategory(@Param("category") String category, @Param("numQ") int numQ);

}
