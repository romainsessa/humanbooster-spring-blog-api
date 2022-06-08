package com.hb.blogapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.blogapi.dto.TagDTO;
import com.hb.blogapi.dto.transformers.TransformerFactory;
import com.hb.blogapi.entities.Post;
import com.hb.blogapi.entities.Tag;
import com.hb.blogapi.exceptions.NotFoundException;
import com.hb.blogapi.repositories.TagRepository;

@Service
@Transactional
public class TagService implements ITagService {

	@Autowired
	private TagRepository tagRepository;

	@Override
	public List<TagDTO> getTagDTOs() {
		Iterable<Tag> tags = tagRepository.findAll();
		List<TagDTO> tagDTOs = new ArrayList<>();
		for (Tag tag : tags) {
			TagDTO tagDTO = TransformerFactory.getTagTransformer().transform(tag);
			tagDTOs.add(tagDTO);
		}
		return tagDTOs;
	}

	public TagDTO getTagDTO(Integer id) throws NotFoundException {
		Optional<Tag> result = tagRepository.findById(id);
		if(result.isEmpty()) {
			throw new NotFoundException();
		}
		return TransformerFactory.getTagTransformer().transform(result.get());
	}

	@Override
	public TagDTO save(TagDTO tag) {
		Tag entityTag = TransformerFactory.getTagTransformer().transform(tag);
		entityTag = tagRepository.save(entityTag);
		return TransformerFactory.getTagTransformer().transform(entityTag);
	}

	@Override
	public void delete(Integer id) throws NotFoundException {
		Optional<Tag> result = tagRepository.findById(id);
		if(result.isEmpty()) {
			throw new NotFoundException();
		}
		Tag t = result.get();
		List<Post> posts = new ArrayList<>(t.getPosts());		
		for (Post post : posts) {
			t.removePost(post);
		}
		tagRepository.deleteById(id);
	}

}
