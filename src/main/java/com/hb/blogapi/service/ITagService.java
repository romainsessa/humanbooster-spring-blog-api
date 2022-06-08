package com.hb.blogapi.service;

import java.util.List;
import com.hb.blogapi.dto.TagDTO;
import com.hb.blogapi.exceptions.NotFoundException;

public interface ITagService {

	public List<TagDTO> getTagDTOs();

	public TagDTO getTagDTO(Integer id) throws NotFoundException;

	public void delete(Integer id) throws NotFoundException;

	public TagDTO save(TagDTO tag);

}
