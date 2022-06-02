package com.hb.blogapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hb.blogapi.entities.PostComment;

@Repository
public interface CommentRepository extends CrudRepository<PostComment, Integer> {

}
