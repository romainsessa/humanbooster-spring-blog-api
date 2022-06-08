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
import com.hb.blogapi.exceptions.NotFoundException;
import com.hb.blogapi.service.TagService;

@RestController
@RequestMapping(value = "api/tag")
public class TagController {

	@Autowired
	private TagService tagService;

	@PostMapping
	public TagDTO create(@RequestBody TagDTO tag) {
		TagDTO createdTag = tagService.save(tag);
		return createdTag;
	}

	@GetMapping
	public List<TagDTO> getTags() {
		return tagService.getTagDTOs();
	}

	@GetMapping("{id}")
	public ResponseEntity<TagDTO> getTag(@PathVariable(name = "id") Integer id) {
		try {
			TagDTO tag = tagService.getTagDTO(id);
			return new ResponseEntity<TagDTO>(tag, HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<TagDTO>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping
	public TagDTO replaceTag(@RequestBody TagDTO tag) {
		return tagService.save(tag);
	}

	@PatchMapping
	public ResponseEntity<TagDTO> partialReplaceTag(@RequestBody TagDTO tag) {
		try {
			TagDTO existingTag = tagService.getTagDTO(tag.getId());
			if (tag.getName() != null && !tag.getName().equals(existingTag.getName())) {
				existingTag.setName(tag.getName());
			}
			TagDTO updatedTag = tagService.save(existingTag);
			return new ResponseEntity<TagDTO>(updatedTag, HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
		try {
			tagService.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

}
