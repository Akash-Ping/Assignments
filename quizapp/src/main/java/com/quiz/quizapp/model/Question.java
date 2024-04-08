package com.quiz.quizapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //use Auto Increment in the mysql
    private Integer id;

    @Column
    private String question_title;

    @Column
    private String option1;

    @Column
    private String option2;

    @Column
    private String option3;

    @Column
    private String option4;

    @Column
    private String right_answer;

    @Column
    private String difficulty_level;

    @Column
    private String category;




    public Question(String question_title, String option1, String option2, String option3, String option4, String right_answer, String difficulty_level, String category) {

        this.question_title = question_title;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.right_answer = right_answer;
        this.difficulty_level = difficulty_level;
        this.category = category;
    }
}