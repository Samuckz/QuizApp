package com.telusko.quizservice.domain.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "quiz")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @ElementCollection
    @CollectionTable(name = "quiz_question_ids", joinColumns = @JoinColumn(name = "quiz_id"))
    private List<Integer> questionIds;

}

