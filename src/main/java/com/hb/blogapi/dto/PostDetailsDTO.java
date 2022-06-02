package com.hb.blogapi.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PostDetailsDTO {

	private Integer id;
	private String createdBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdOn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public PostDetailsDTO() {
	}

	public PostDetailsDTO(Integer id, String createdBy, Date createdOn) {
		super();
		this.id = id;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "PostDetailsDTO [id=" + id + ", createdBy=" + createdBy + ", createdOn=" + createdOn + "]";
	}

}
