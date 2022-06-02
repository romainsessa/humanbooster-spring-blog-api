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
		if (id == 4) { // Just to simulate an error use case
			return null;
		} else {
			return new TagDTO(id, "tag" + id);
		}
	}

	public TagDTO save(TagDTO tag) {
		// Id generation simulation
		tag.setId(1);
		return tag;
	}

	public void delete(Integer id) {
		System.out.println("delete" + id);
	}

}
