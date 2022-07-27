package com.webradar.stackoverflow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webradar.stackoverflow.entities.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
