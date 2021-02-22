package com.example.quiz.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Quiz {

    private String category;
    private String type;
    private String difficulty;
    private String question;
    private String correctAnswer;
    private List<String> incorrectAnswers;

}
