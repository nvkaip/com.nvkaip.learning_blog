package com.nvkaip.learnig.blog.repository;

import com.nvkaip.learnig.blog.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User, Long> {
}
