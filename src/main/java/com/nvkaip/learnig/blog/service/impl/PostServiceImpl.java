package com.nvkaip.learnig.blog.service.impl;

import com.nvkaip.learnig.blog.model.Post;
import com.nvkaip.learnig.blog.model.User;
import com.nvkaip.learnig.blog.repository.PostRepository;
import com.nvkaip.learnig.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Flux<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Flux<Post> findAllByUser(Mono<User> userMono) {
        return postRepository.findAllByUser(userMono);
    }

    @Override
    public Mono<Post> save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Mono<Post> findById(Long postId) {
        return postRepository.findById(postId);
    }

    @Override
    public Mono<Void> delete(Post post) {
        return postRepository.delete(post);
    }
}
