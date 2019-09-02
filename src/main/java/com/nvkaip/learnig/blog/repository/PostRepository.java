package com.nvkaip.learnig.blog.repository;

import com.nvkaip.learnig.blog.model.Post;
import com.nvkaip.learnig.blog.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostRepository extends ReactiveMongoRepository<Post, Long> {
    Flux<Post> findAllByUser (Mono<User> userMono);
}
