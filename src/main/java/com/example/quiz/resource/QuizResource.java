package com.example.quiz.resource;

import com.example.quiz.dto.QuizCategoryListResponse;
import com.example.quiz.service.QuizServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class QuizResource {

    @Autowired
    private QuizServiceImpl quizServiceImpl;

    Logger logger = Logger.getLogger(String.valueOf(QuizResource.class));

    @GetMapping("/coding/exercise/quiz")
    public ResponseEntity<QuizCategoryListResponse> getQuiz() {
        logger.log(Level.INFO, "Received request to get quizzes");
        return ResponseEntity.ok(quizServiceImpl.getQuizzes());
    }
}
