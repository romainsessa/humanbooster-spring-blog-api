package com.hb.blogapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hb.blogapi.dto.TagDTO;

@Service
public class TagService {

	public List<TagDTO> getTags() {
		List<TagDTO> tags = new ArrayList<>();
		tags.add(new TagDTO(1, "tag1"));
		tags.add(new TagDTO(2, "tag2"));	
		
		return tags;
	}

	public TagDTO getTag(Integer id) {
		return new TagDTO(id, "tag"+id);
	}

	public TagDTO save(TagDTO tag) {
		// simulation de la génération de l'id
		tag.setId(1); 
		return tag;
	}

	public void delete(Integer id) {
		System.out.println("delete" + id);
	}

}
