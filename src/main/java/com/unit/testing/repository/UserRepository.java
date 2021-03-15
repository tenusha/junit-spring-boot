package com.unit.testing.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.unit.testing.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	public User findByName(String name);

}
