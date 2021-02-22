package com.example.quiz.resource;

import com.example.quiz.dto.QuizCategoryListResponse;
import com.example.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizResource {

    @Autowired
    private QuizService quizService;

    @GetMapping("/coding/exercise/quiz")
    public ResponseEntity<QuizCategoryListResponse> getQuiz() {
        return ResponseEntity.ok(quizService.getQuizzes());
    }
}
