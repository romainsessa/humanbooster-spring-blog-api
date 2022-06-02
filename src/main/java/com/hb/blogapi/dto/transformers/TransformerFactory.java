package com.hb.blogapi.dto.transformers;

import org.springframework.stereotype.Component;

@Component
public class TransformerFactory {

	public static PostTransformer getPostTransformer() {
		return new PostTransformer();
	}

	public static TagTransformer getTagTransformer() {
		return new TagTransformer();
	}

	public static PostCommentTransformer getPostCommentTransformer() {
		return new PostCommentTransformer();
	}

}
