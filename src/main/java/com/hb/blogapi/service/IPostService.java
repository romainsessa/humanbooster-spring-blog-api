package com.hb.blogapi.service;

import java.util.List;
import com.hb.blogapi.dto.PostDTO;

public interface IPostService {

	public PostDTO getPostDTO(Integer id);

	public List<PostDTO> getPostDTOs();

	public PostDTO save(PostDTO post);

	public void delete(Integer id);

	public void mapPostTag(Integer postId, Integer tagId);

	public void unmapPostTag(Integer postId, Integer tagId);
}
