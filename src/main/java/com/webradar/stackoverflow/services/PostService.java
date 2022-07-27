package com.webradar.stackoverflow.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webradar.stackoverflow.entities.Post;
import com.webradar.stackoverflow.repositories.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	
	public List<Post> findAll() {
		return repository.findAll();
	}
}
