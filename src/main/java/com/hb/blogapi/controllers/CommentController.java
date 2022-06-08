package com.hb.blogapi.controllers;

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

import com.hb.blogapi.dto.PostCommentDTO;
import com.hb.blogapi.exceptions.NotFoundException;
import com.hb.blogapi.service.ICommentService;

@RestController
@RequestMapping("api/comment")
public class CommentController {

	@Autowired
	private ICommentService commentService;

	@GetMapping("{id}")
	public ResponseEntity<PostCommentDTO> getComment(@PathVariable(name = "id") Integer id) {
		try {
			PostCommentDTO comment = commentService.getCommentDTO(id);
			return new ResponseEntity<PostCommentDTO>(comment, HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<PostCommentDTO>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<PostCommentDTO> create(@RequestBody PostCommentDTO comment) {
		try {
			comment = commentService.save(comment);
			return new ResponseEntity<PostCommentDTO>(comment, HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<PostCommentDTO>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
		try {
			commentService.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

}
