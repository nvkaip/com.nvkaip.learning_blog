package com.nvkaip.learnig.blog.controller;

import com.nvkaip.learnig.blog.model.Post;
import com.nvkaip.learnig.blog.model.User;
import com.nvkaip.learnig.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public Flux<Post> getAllPosts() {
        return postService.findAll();
    }

    @GetMapping("/user/posts")
    public Flux<Post> getAllUserPosts(@AuthenticationPrincipal Mono<User> userMono){
        return postService.findAllByUser(userMono);
    }

    @PostMapping("/posts")
    public Mono<Post> createPosts(@Valid @RequestBody Post post) {
        return postService.save(post);
    }

    @GetMapping("/posts/{id}")
    public Mono<ResponseEntity<Post>> getPostById(@PathVariable(value = "id") Long postId) {
        return postService.findById(postId)
                .map(savedPost -> ResponseEntity.ok(savedPost))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/posts/{id}")
    public Mono<ResponseEntity<Post>> updatePost(@PathVariable(value = "id") Long postId,
                                                   @Valid @RequestBody Post post) {
        return postService.findById(postId)
                .flatMap(existingPost -> {
                    existingPost.setText(post.getText());
                    return postService.save(existingPost);
                })
                .map(updatedPost -> new ResponseEntity<>(updatedPost, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/posts/{id}")
    public Mono<ResponseEntity<Void>> deletePost(@PathVariable(value = "id") Long postId) {

        return postService.findById(postId)
                .flatMap(existingPost ->
                        postService.delete(existingPost)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/stream/posts", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Post> streamAllPosts() {
        return postService.findAll();
    }
}
