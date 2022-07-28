package com.webradar.stackoverflow.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.webradar.stackoverflow.entities.Answer;
import com.webradar.stackoverflow.entities.User;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

	@Query("SELECT a FROM Answer a WHERE a.author = :id")
	List<Answer> findByUserId(@Param("id") User user);
}
