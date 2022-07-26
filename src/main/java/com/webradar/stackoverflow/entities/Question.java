package com.webradar.stackoverflow.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_question")
public class Question implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@NotBlank(message = "A pergunta não pode estar vazia")
	private String body;
	
	@ManyToOne
	@JoinColumn(name = "author_id")
	private User author;
	
	@JsonIgnore
	@OneToMany(mappedBy = "question")
	List<Answer> answers = new ArrayList<>();
		
	public Question() {
	}
	
	public Question(Long id) {
		super();
		this.id = id;
	}

	public Question(Long id, String body, User userAuthor) {
		super();
		this.id = id;		
		this.body = body;
		this.author = userAuthor;
	}	

	public Question(Long id, String body) {
		super();
		this.id = id;		
		this.body = body;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}	
	
	public List<Answer> getAnswers() {
		return answers;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		return Objects.equals(id, other.id);
	}
}
