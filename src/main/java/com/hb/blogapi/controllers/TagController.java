package com.hb.blogapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hb.blogapi.dto.TagDTO;
import com.hb.blogapi.service.TagService;

@RestController
@RequestMapping(value = "api/tag")
public class TagController {

	@Autowired
	private TagService tagService;

	// Create
	@PostMapping
	public TagDTO create(@RequestBody TagDTO tag) {
		TagDTO createdTag = tagService.save(tag);
		return createdTag;
	}

	// Read
	@GetMapping
	public List<TagDTO> getTags() {
		return tagService.getTags();
	}

	@GetMapping("{id}")
	public ResponseEntity<TagDTO> getTag(@PathVariable(name = "id") Integer id) {
		TagDTO tag = tagService.getTag(id);
		if (tag == null) {
			return new ResponseEntity<TagDTO>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<TagDTO>(tag, HttpStatus.OK);
		}
	}

	// Update
	@PutMapping
	public TagDTO replaceTag(@RequestBody TagDTO tag) {
		return tagService.save(tag);
	}

	@PatchMapping
	public TagDTO partialReplaceTag(@RequestBody TagDTO tag) {
		TagDTO existingTag = tagService.getTag(tag.getId());
		if (tag.getName() != null && !tag.getName().equals(existingTag.getName())) {
			existingTag.setName(tag.getName());
		}
		return tagService.save(existingTag);
	}

	// Delete
	@DeleteMapping("{id}")
	public void delete(@PathVariable(name = "id") Integer id) {
		tagService.delete(id);
	}

}
