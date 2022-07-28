package com.webradar.stackoverflow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webradar.stackoverflow.entities.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
