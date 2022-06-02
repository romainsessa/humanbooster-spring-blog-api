package com.hb.blogapi.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Integer id;

	@Column(nullable = false)
	private String title;

	@OneToOne(mappedBy = "post", cascade = CascadeType.ALL)
	private PostDetails details;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PostComment> comments = new ArrayList<PostComment>();

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<Tag> tags = new ArrayList<Tag>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public PostDetails getDetails() {
		return details;
	}

	public void setDetails(PostDetails details) {
		this.details = details;
		details.setPost(this);
	}

	public List<PostComment> getComments() {
		return comments;
	}

	public void setComments(List<PostComment> comments) {
		this.comments = comments;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Post() {
		// TODO Auto-generated constructor stub
	}

	public Post(Integer id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

	public void removeDetails(PostDetails details) {
		this.details = null;
		details.setPost(null);
	}

	public void addComment(PostComment comment) {
		this.comments.add(comment);
		comment.setPost(this);
	}

	public void removeComment(PostComment comment) {
		this.comments.remove(comment);
		comment.setPost(null);
	}

	public void addTag(Tag tag) {
		this.tags.add(tag);
		tag.getPosts().add(this);
	}

	public void removeTag(Tag tag) {
		this.tags.remove(tag);
		tag.getPosts().remove(this);
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", details=" + details + ", comments=" + comments + ", tags="
				+ tags + "]";
	}

}
