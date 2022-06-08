package com.hb.blogapi.service;

import java.util.List;

import com.hb.blogapi.dto.PostCommentDTO;
import com.hb.blogapi.exceptions.NotFoundException;

public interface ICommentService {

	public PostCommentDTO getCommentDTO(Integer id) throws NotFoundException;	

	public List<PostCommentDTO> getCommentDTOs();

	public PostCommentDTO save(PostCommentDTO comment) throws NotFoundException;

	public void delete(Integer id) throws NotFoundException;

}
