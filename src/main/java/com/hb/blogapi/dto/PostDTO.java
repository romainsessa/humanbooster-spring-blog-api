package com.hb.blogapi.dto;

import java.util.ArrayList;
import java.util.List;

public class PostDTO {

	private Integer id;
	private String title;
	private PostDetailsDTO details;
	private List<PostCommentDTO> comments = new ArrayList<>();
	private List<TagDTO> tags = new ArrayList<>();

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

	public PostDetailsDTO getDetails() {
		return details;
	}

	public void setDetails(PostDetailsDTO details) {
		this.details = details;
	}

	public List<PostCommentDTO> getComments() {
		return comments;
	}

	public void setComments(List<PostCommentDTO> comments) {
		this.comments = comments;
	}

	public List<TagDTO> getTags() {
		return tags;
	}

	public void setTags(List<TagDTO> tags) {
		this.tags = tags;
	}

	public PostDTO() {
	}

	public PostDTO(Integer id, String title, PostDetailsDTO details, List<PostCommentDTO> comments, List<TagDTO> tags) {
		super();
		this.id = id;
		this.title = title;
		this.details = details;
		this.comments = comments;
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "PostDTO [id=" + id + ", title=" + title + ", details=" + details + ", comments=" + comments + ", tags="
				+ tags + "]";
	}
}
