package com.hb.blogapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hb.blogapi.dto.PostDTO;
import com.hb.blogapi.exceptions.NotFoundException;
import com.hb.blogapi.service.IPostService;

@RestController
@RequestMapping("api/post")
public class PostController {

	@Autowired
	private IPostService postService;

	@GetMapping("/{id}")
	public ResponseEntity<PostDTO> getPost(@PathVariable(name = "id") Integer id) {
		ResponseEntity<PostDTO> response = null;
		try {
			PostDTO post = postService.getPostDTO(id);
			response = new ResponseEntity<PostDTO>(post, HttpStatus.OK);
		} catch (NotFoundException e) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@GetMapping
	public List<PostDTO> getPosts() {
		return postService.getPostDTOs();
	}

	@PostMapping
	public ResponseEntity<PostDTO> create(@RequestBody PostDTO post) {
		PostDTO result = postService.save(post);
		ResponseEntity<PostDTO> response = new ResponseEntity<PostDTO>(result, HttpStatus.OK);
		return response;
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
		ResponseEntity<Void> response = null;
		try {
			postService.delete(id);
			response = new ResponseEntity<>(HttpStatus.OK);
		} catch (NotFoundException e) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@GetMapping("{postId}/map/{tagId}")
	public ResponseEntity<Void> mapPostTag(@PathVariable(name = "postId") Integer postId,
			@PathVariable(name = "tagId") Integer tagId) {
		try {
			postService.mapPostTag(postId, tagId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("{postId}/unmap/{tagId}")
	public ResponseEntity<Void> unmapPostTag(@PathVariable(name = "postId") Integer postId,
			@PathVariable(name = "tagId") Integer tagId) {
		try {
			postService.unmapPostTag(postId, tagId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

}
