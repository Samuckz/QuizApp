package com.telusko.quizservice.feign;

import com.telusko.quizservice.domain.dtos.QuestionResponseDto;
import com.telusko.quizservice.domain.dtos.QuizSubmitDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("questions/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numQuestions);

    // getQuestions (quizId)
    @PostMapping("questions/getQuestions")
    public ResponseEntity<List<QuestionResponseDto>> getQuestionsFromId(@RequestBody List<Integer> questionsIds);
    // getScore
    @PostMapping("questions/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<QuizSubmitDto> answers);


}
