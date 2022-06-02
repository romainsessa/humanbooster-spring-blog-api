package com.hb.blogapi.dto;

public class PostCommentDTO {

	private Integer id;
	private String review;
	private Integer postId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}
	
	public Integer getPostId() {
		return postId;
	}
	
	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public PostCommentDTO() {
	}

	public PostCommentDTO(Integer id, String review, Integer postId) {
		super();
		this.id = id;
		this.review = review;
		this.postId = postId;
	}

	@Override
	public String toString() {
		return "PostCommentDTO [id=" + id + ", review=" + review + "]";
	}

}
