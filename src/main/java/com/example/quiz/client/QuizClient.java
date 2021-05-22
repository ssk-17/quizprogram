package com.example.quiz.client;

import com.example.quiz.dto.QuizResponse;
import reactor.core.publisher.Mono;

public interface QuizClient {
    Mono<QuizResponse> getQuizzes(String url);
}
