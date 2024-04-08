package com.quiz.quizapp;

import com.quiz.quizapp.dao.QuestionDao;
import com.quiz.quizapp.model.Question;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class QuizappApplicationTests {

	@Autowired
	private QuizappApplication quizappApplication;

	@Test
	void contextLoads() {
		assertThat(quizappApplication).isNotNull();
	}

	@Autowired
	QuestionDao questionDao;

	@Test
	public void addQuestionTest() {
		Question question = new Question("Is JAVA aa fully Object Oriented language ?", "Yes", "No" , "Partially" , "Don't Know", "Partially", "Easy", "Java");
		Question savedQuestions = questionDao.saveAndFlush(question);
		assertEquals(question, savedQuestions);
	}

//	@Test
//	public void deleteQuestionTest() {
//		Question question = new Question("Is JAVA a fully Object Oriented language ?", "Yes", "No" , "Partially" , "Don't Know", "Partially", "Easy", "Java");
//		questionDao.saveAndFlush(question);
//
//		questionDao.delete(question);
//
//		Mockito.verify(questionDao, Mockito.times(1)).delete(question);
//	}
}
