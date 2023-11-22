package com.telusko.quizservice.domain.controllers;

import com.telusko.quizservice.domain.dtos.QuizDto;
import com.telusko.quizservice.domain.dtos.QuizSubmitDto;
import com.telusko.quizservice.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;
    @PostMapping("/create")
    public ResponseEntity createQuiz(@RequestBody QuizDto quizdto){
        return quizService.createQuiz(quizdto);

    }

    @GetMapping("/get/{quizId}")
    public ResponseEntity getQuiz(@PathVariable Integer quizId){
        return quizService.getQuizQuestions(quizId);
    }

    @PostMapping("/submit/{quizId}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer quizId, @RequestBody List<QuizSubmitDto> dtoList){
        return quizService.calculateResult(quizId, dtoList);
    }
}
