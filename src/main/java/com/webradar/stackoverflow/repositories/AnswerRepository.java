package com.webradar.stackoverflow.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.webradar.stackoverflow.entities.Answer;
import com.webradar.stackoverflow.entities.Question;
import com.webradar.stackoverflow.entities.User;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

	@Query("SELECT a FROM Answer a WHERE a.author = :id")
	List<Answer> findByUserId(@Param("id") User user);

	@Query("SELECT a FROM Answer a WHERE a.question = :id")
	List<Answer> findByQuestionId(@Param("id") Question question);
	
	Answer getById(Long id);
}
