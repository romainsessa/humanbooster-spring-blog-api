package com.hb.blogapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hb.blogapi.entities.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {

}
