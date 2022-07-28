package com.webradar.stackoverflow.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.webradar.stackoverflow.entities.Question;
import com.webradar.stackoverflow.entities.User;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

	@Query("SELECT q FROM Question q WHERE q.author = :id")
	List<Question> findByUserId(@Param("id") User user);
}
