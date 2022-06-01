package com.hb.blogapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hb.blogapi.entities.InternalUser;

@Repository
public interface InternalUserRepository extends CrudRepository<InternalUser, Integer> {

	public InternalUser findByUsername(String username);

}
