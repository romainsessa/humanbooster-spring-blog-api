package com.hb.blogapi.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.blogapi.dto.PostCommentDTO;
import com.hb.blogapi.dto.PostDTO;
import com.hb.blogapi.dto.transformers.TransformerFactory;
import com.hb.blogapi.entities.PostComment;
import com.hb.blogapi.repositories.CommentRepository;

@Service
@Transactional
public class CommentService implements ICommentService {

	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private PostService postService;

	@Override
	public PostCommentDTO getCommentDTO(Integer id) {
		PostComment entityComment = commentRepository.findById(id).get();
		return TransformerFactory.getPostCommentTransformer().transform(entityComment);
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
	public PostCommentDTO save(PostCommentDTO comment) {
		PostDTO post = postService.getPostDTO(comment.getPostId());		
		PostComment entityComment = TransformerFactory.getPostCommentTransformer().transform(comment, post);
		entityComment = commentRepository.save(entityComment);
		return TransformerFactory.getPostCommentTransformer().transform(entityComment);
	}

	@Override
	public void delete(Integer id) {
		commentRepository.deleteById(id);
	}
}
