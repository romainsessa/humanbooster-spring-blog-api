package com.hb.blogapi.dto.transformers;

import org.springframework.stereotype.Component;

import com.hb.blogapi.dto.PostCommentDTO;
import com.hb.blogapi.dto.PostDTO;
import com.hb.blogapi.entities.Post;
import com.hb.blogapi.entities.PostComment;


@Component
public class PostCommentTransformer {
	
	public PostCommentDTO transform(PostComment comment) {
		PostCommentDTO commentDTO = new PostCommentDTO();
		commentDTO.setId(comment.getId());
		commentDTO.setReview(comment.getReview());
		commentDTO.setPostId(comment.getPost().getId());
		return commentDTO;
	}

	public PostComment transform(PostCommentDTO comment, PostDTO post) {
		PostComment entityComment = new PostComment();
		entityComment.setId(comment.getId());
		entityComment.setReview(comment.getReview());

		Post entityPost = TransformerFactory.getPostTransformer().transform(post, false);
		entityComment.setPost(entityPost);

		return entityComment;
	}

}
