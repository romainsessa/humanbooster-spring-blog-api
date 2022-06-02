package com.hb.blogapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hb.blogapi.entities.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag, Integer> {

}
