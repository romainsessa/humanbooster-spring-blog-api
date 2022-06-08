package com.hb.blogapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.hb.blogapi.dto.PostCommentDTO;
import com.hb.blogapi.dto.PostDTO;
import com.hb.blogapi.dto.transformers.TransformerFactory;
import com.hb.blogapi.entities.PostComment;
import com.hb.blogapi.exceptions.NotFoundException;
import com.hb.blogapi.repositories.CommentRepository;

@Service
@Transactional
public class CommentService implements ICommentService {

	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private PostService postService;

	@Override
	public PostCommentDTO getCommentDTO(Integer id) throws NotFoundException {
		Optional<PostComment> result = commentRepository.findById(id);
		if (result.isEmpty()) {
			throw new NotFoundException();
		}
		return TransformerFactory.getPostCommentTransformer().transform(result.get());
	}

	@Override
	public List<PostCommentDTO> getCommentDTOs() {
		Iterable<PostComment> entities = commentRepository.findAll();
		List<PostCommentDTO> comments = new ArrayList<>();
		for (PostComment entityComment : entities) {
			PostCommentDTO comment = TransformerFactory.getPostCommentTransformer().transform(entityComment);
			comments.add(comment);
		}
		return comments;
	}

	@Override
	public PostCommentDTO save(PostCommentDTO comment) throws NotFoundException {
		PostDTO post = postService.getPostDTO(comment.getPostId());
		PostComment entityComment = TransformerFactory.getPostCommentTransformer().transform(comment, post);
		entityComment = commentRepository.save(entityComment);
		return TransformerFactory.getPostCommentTransformer().transform(entityComment);
	}

	@Override
	public void delete(Integer id) throws NotFoundException {
		try {
			commentRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new NotFoundException();
		}
	}
}
