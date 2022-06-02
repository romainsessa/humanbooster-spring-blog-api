package com.hb.blogapi.dto.transformers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hb.blogapi.dto.PostCommentDTO;
import com.hb.blogapi.dto.PostDTO;
import com.hb.blogapi.dto.PostDetailsDTO;
import com.hb.blogapi.dto.TagDTO;
import com.hb.blogapi.entities.Post;
import com.hb.blogapi.entities.PostComment;
import com.hb.blogapi.entities.PostDetails;
import com.hb.blogapi.entities.Tag;

@Component
public class PostTransformer {

	public PostDTO transform(Post post) {
		return transform(post, true);
	}

	public PostDTO transform(Post post, boolean withTag) {
		PostDTO pDTO = new PostDTO();
		pDTO.setId(post.getId());
		pDTO.setTitle(post.getTitle());

		PostDetailsDTO dDTO = new PostDetailsDTO();
		dDTO.setId(post.getDetails().getId());
		dDTO.setCreatedBy(post.getDetails().getCreated_by());
		dDTO.setCreatedOn(post.getDetails().getCreate_on());

		pDTO.setDetails(dDTO);

		List<PostCommentDTO> commentDTOs = new ArrayList<>();
		for (PostComment comment : post.getComments()) {
			PostCommentDTO commentDTO = TransformerFactory.getPostCommentTransformer().transform(comment);
			commentDTOs.add(commentDTO);
		}
		pDTO.setComments(commentDTOs);

		if (withTag) {
			List<TagDTO> tagDTOs = new ArrayList<>();
			for (Tag tag : post.getTags()) {
				TagDTO tagDTO = TransformerFactory.getTagTransformer().transform(tag, false);
				tagDTOs.add(tagDTO);
			}
			pDTO.setTags(tagDTOs);
		}
		return pDTO;
	}

	public Post transform(PostDTO post) {
		return transform(post, true);
	}

	public Post transform(PostDTO post, boolean withComment) {
		Post entityPost = new Post();
		entityPost.setId(post.getId());
		entityPost.setTitle(post.getTitle());

		PostDetails detailsEntity = new PostDetails();
		detailsEntity.setId(post.getDetails().getId());
		detailsEntity.setCreated_by(post.getDetails().getCreatedBy());
		detailsEntity.setCreate_on(post.getDetails().getCreatedOn());

		entityPost.setDetails(detailsEntity);

		if (withComment) {
			for (PostCommentDTO comment : post.getComments()) {
				PostComment entityComment = new PostComment();
				entityComment.setId(comment.getId());
				entityComment.setReview(comment.getReview());
				entityPost.addComment(entityComment);
			}
		}

		return entityPost;
	}

}
