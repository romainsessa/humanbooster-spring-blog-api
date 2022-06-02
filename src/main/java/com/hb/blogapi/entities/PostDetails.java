package com.hb.blogapi.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "details")
public class PostDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "details_id")
	private Integer id;

	@Column(nullable = false)
	private String created_by;

	@Column(nullable = false)
	private Date create_on;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "post_id")
	private Post post;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getCreate_on() {
		return create_on;
	}

	public void setCreate_on(Date create_on) {
		this.create_on = create_on;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public PostDetails() {
	}

	public PostDetails(Integer id, String created_by, Date create_on) {
		super();
		this.id = id;
		this.created_by = created_by;
		this.create_on = create_on;
	}

	public void addPost(Post post) {
		this.post = post;
		post.setDetails(this);
	}

	@Override
	public String toString() {
		return "PostDetails [id=" + id + ", created_by=" + created_by + ", create_on=" + create_on + "]";
	}

}
