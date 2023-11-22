package com.telusko.quizservice.domain.dtos;

public record QuestionResponseDto(Integer quizId, String questionTitle, String option1, String option2, String option3, String option4) {
}
