package com.webradar.stackoverflow.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_post")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String body;
	
	@ManyToOne
	@JoinColumn(name = "author_id")
	private User author;
	
	public Post() {
	}

	public Post(Long id, String title, String body, User userAuthor) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.author = userAuthor;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}

	public User getUserAuthor() {
		return author;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, author);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(id, other.id) && Objects.equals(author, other.author);
	}	
}
