package com.example.quiz.client;

import com.example.quiz.dto.QuizResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
public class QuizClient {

    public Mono<QuizResponse> getQuizzes(String url) {

        WebClient client = WebClient.builder()
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", url))
                .build();

        return client.get().uri(url).retrieve().bodyToMono(QuizResponse.class);
    }
}
