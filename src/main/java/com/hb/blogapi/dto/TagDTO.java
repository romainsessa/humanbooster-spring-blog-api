package com.hb.blogapi.dto;

import java.util.ArrayList;
import java.util.List;

public class TagDTO {

	private Integer id;
	private String name;
	private List<PostDTO> posts = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PostDTO> getPosts() {
		return posts;
	}

	public void setPosts(List<PostDTO> posts) {
		this.posts = posts;
	}

	public TagDTO() {
	}

	public TagDTO(Integer id, String name, List<PostDTO> posts) {
		super();
		this.id = id;
		this.name = name;
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "TagDTO [id=" + id + ", name=" + name + ", posts=" + posts + "]";
	}

}
