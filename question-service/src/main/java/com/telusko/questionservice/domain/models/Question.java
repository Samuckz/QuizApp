package com.telusko.questionservice.domain.models;


import com.telusko.questionservice.domain.dtos.QuestionDto;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "question")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String rightAnswer;
    private String difficultylevel;
    private String category;


    public Question(QuestionDto dto) {
        this.questionTitle = dto.questionTitle();
        this.option1 = dto.option1();
        this.option2 = dto.option2();
        this.option3 = dto.option3();
        this.option4 = dto.option4();
        this.rightAnswer = dto.rightAnswer();
        this.difficultylevel = dto.difficultyLevel();
        this.category = dto.category();
    }

    public String toString(Question question){
        return "Question: " + question.questionTitle +
                "\n a) " + question.option1 +
                "\n b) " + question.option2 +
                "\n c) " + question.option3 +
                "\n d) " + question.option4;
    }
}
