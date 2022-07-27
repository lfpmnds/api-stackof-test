package com.webradar.stackoverflow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webradar.stackoverflow.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
