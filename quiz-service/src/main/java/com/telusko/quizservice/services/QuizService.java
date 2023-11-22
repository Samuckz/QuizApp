package com.telusko.quizservice.services;


import com.telusko.quizservice.domain.dtos.QuizDto;
import com.telusko.quizservice.domain.dtos.QuizSubmitDto;
import com.telusko.quizservice.domain.models.QuizModel;
import com.telusko.quizservice.domain.repository.QuizRepository;
import com.telusko.quizservice.feign.QuizInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity createQuiz(QuizDto quizdto) {
        List<Integer> questions = quizInterface.getQuestionsForQuiz(quizdto.category(), quizdto.numQuestions()).getBody();
        QuizModel quiz = new QuizModel();
        quiz.setTitle(quizdto.title());
        quiz.setQuestionIds(questions);
        quizRepository.save(quiz);

        return new ResponseEntity(quiz, HttpStatus.CREATED);
    }

    public ResponseEntity getQuizQuestions(Integer quizId) {
        var quiz = quizRepository.findById(quizId);
        List<Integer> questionsFromQuiz = quiz.get().getQuestionIds();
        return quizInterface.getQuestionsFromId(questionsFromQuiz);
    }

    public ResponseEntity<Integer> calculateResult(Integer quizId, List<QuizSubmitDto> dtoList) {
        return quizInterface.getScore(dtoList);
    }
}
