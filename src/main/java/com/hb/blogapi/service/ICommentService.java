package com.hb.blogapi.service;

import java.util.List;

import com.hb.blogapi.dto.PostCommentDTO;

public interface ICommentService {

	public PostCommentDTO getCommentDTO(Integer id);	

	public List<PostCommentDTO> getCommentDTOs();

	public PostCommentDTO save(PostCommentDTO comment);

	public void delete(Integer id);

}
