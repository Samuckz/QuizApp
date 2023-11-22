package com.telusko.questionservice.services;

import com.telusko.questionservice.domain.dtos.QuestionDto;
import com.telusko.questionservice.domain.dtos.QuestionResponseDto;
import com.telusko.questionservice.domain.dtos.QuizSubmitDto;
import com.telusko.questionservice.domain.models.Question;
import com.telusko.questionservice.domain.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity getAllQuestions() {
        try{
            return new ResponseEntity(questionRepository.findAll(), HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity getQuestionsByCategory(String category) {
        try{
            return new ResponseEntity(questionRepository.findByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity(new ArrayList(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity addQuestion(QuestionDto dto) {
        try{
            Question question = new Question(dto);
            var response = questionRepository.save(question);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();

    }

    public ResponseEntity getAllQuestionsForStudents() {
        try{
            var questions = questionRepository.findAll();
            return new ResponseEntity(questions, HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, Integer numQuestions) {

        var questions = questionRepository.findRandomQuestionsByCategory(category, numQuestions);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }


    public ResponseEntity<List<QuestionResponseDto>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionResponseDto> response = questionIds.stream()
                .map(questionId -> questionRepository.findById(questionId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(question -> new QuestionResponseDto(
                        question.getId(),
                        question.getQuestionTitle(),
                        question.getOption1(),
                        question.getOption2(),
                        question.getOption3(),
                        question.getOption4()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Integer> getScore(List<QuizSubmitDto> answers) {
        int right = 0;

        for (QuizSubmitDto response: answers) {
            var question = questionRepository.findById(response.quizId());
            if (response.answer().equals(question.get().getRightAnswer())){
                right++;
            }
        }

        return new ResponseEntity(right, HttpStatus.OK);
    }
}
