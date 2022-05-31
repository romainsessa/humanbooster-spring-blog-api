package com.hb.blogapi.dto;

public class TagDTO {

	private Integer id;
	private String name;

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

	public TagDTO() {
	}

	public TagDTO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "TagDTO [id=" + id + ", name=" + name + "]";
	}

}
