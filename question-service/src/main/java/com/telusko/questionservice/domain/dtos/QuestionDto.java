package com.telusko.questionservice.domain.dtos;

public record QuestionDto(String questionTitle, String option1, String option2, String option3, String option4, String rightAnswer, String difficultyLevel, String category) {
}
