package com.example.quiz.service;

import com.example.quiz.client.QuizClient;
import com.example.quiz.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuizService {
    @Autowired
    private QuizClient quizClient;

    private static final String url1 = "https://opentdb.com/api.php?amount=5&category=11";
    private static final String url2 = "https://opentdb.com/api.php?amount=5&category=12";

    public QuizCategoryListResponse getQuizzes() {
        Mono<QuizResponse> quizResponse1 = quizClient.getQuizzes(url1);
        Mono<QuizResponse> quizResponse2 = quizClient.getQuizzes(url2);

        List<Quiz> results = quizResponse1.block().getResults();
        results.addAll(quizResponse2.block().getResults());

        //Construct category based list of responses
        Map<String, List<QuizCategory>> response = new HashMap<>();
        for (Quiz result : results) {
            if (response.get(result.getCategory()) != null) {
                List<QuizCategory> list = response.get(result.getCategory());
                list.add(getQuizCategoryFromQuiz(result));
                response.put(result.getCategory(), list);
            } else {
                List<QuizCategory> quizCategoryList = new ArrayList<>();
                quizCategoryList.add(getQuizCategoryFromQuiz(result));
                response.put(result.getCategory(), quizCategoryList);
            }
        }
        QuizCategoryListResponse quizCategoryListResponse = new QuizCategoryListResponse();
        List<QuizCategoryResponse> quizCategoryResponseList = new ArrayList<>();
        for (Map.Entry<String, List<QuizCategory>> entry : response.entrySet()) {
            QuizCategoryResponse quizCategoryResponse = new QuizCategoryResponse();
            quizCategoryResponse.setCategory(entry.getKey());
            quizCategoryResponse.setResults(entry.getValue());
            quizCategoryResponseList.add(quizCategoryResponse);
        }
        quizCategoryListResponse.setQuiz(quizCategoryResponseList);
        return quizCategoryListResponse;
    }

    private QuizCategory getQuizCategoryFromQuiz(Quiz quiz) {
        QuizCategory quizCategory = new QuizCategory();

        quizCategory.setType(quiz.getType());
        quizCategory.setDifficulty(quiz.getDifficulty());
        quizCategory.setQuestion(quiz.getQuestion());
        quizCategory.setCorrectAnswer(quiz.getCorrectAnswer());
        quizCategory.setAllAnswers(quiz.getIncorrectAnswers());
        quizCategory.getAllAnswers().add(quiz.getCorrectAnswer());

        return quizCategory;
    }
}
