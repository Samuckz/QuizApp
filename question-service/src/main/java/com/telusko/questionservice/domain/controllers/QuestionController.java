package com.telusko.questionservice.domain.controllers;

import com.telusko.questionservice.domain.dtos.QuestionDto;
import com.telusko.questionservice.domain.dtos.QuestionResponseDto;
import com.telusko.questionservice.domain.dtos.QuizSubmitDto;
import com.telusko.questionservice.domain.models.Question;
import com.telusko.questionservice.domain.repository.QuestionRepository;
import com.telusko.questionservice.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionRepository questionRepository;
    @GetMapping("/getAllQuestions")
    public ResponseEntity getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/student/getAllQuestions")
    public ResponseEntity getAllQuestionsForStudents(){
        return questionService.getAllQuestionsForStudents();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity getQuestionByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping
    public ResponseEntity addQuestion(@RequestBody @Validated QuestionDto dto){
        return questionService.addQuestion(dto);
    }

    // generate questions for quiz
    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numQuestions){
        return questionService.getQuestionsForQuiz(category, numQuestions);
    }

    // getQuestions (quizId)
    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionResponseDto>> getQuestionsFromId(@RequestBody List<Integer> questionsIds){
        return questionService.getQuestionsFromId(questionsIds);
    }

    // getScore
    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<QuizSubmitDto> answers){
        return questionService.getScore(answers);
    }

}
