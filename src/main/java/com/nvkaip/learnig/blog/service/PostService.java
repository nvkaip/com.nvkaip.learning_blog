package com.nvkaip.learnig.blog.service;

import com.nvkaip.learnig.blog.model.Post;
import com.nvkaip.learnig.blog.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostService {
    Flux<Post> findAll();
    Flux<Post> findAllByUser (Mono<User> userMono);
    Mono<Post> save (Post post);
    Mono<Post> findById(Long postId);
    Mono<Void> delete(Post post);
}
