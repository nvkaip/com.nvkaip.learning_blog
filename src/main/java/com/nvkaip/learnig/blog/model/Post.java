package com.nvkaip.learnig.blog.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Document(collection = "posts")
public class Post {

    @Id
    private Long id;

    @NotBlank
    private String text;

    @NotNull
    private Date createdAt = new Date();

    @NotBlank
    private User user;

    public Post() {
    }

    public Post(@NotBlank String text) {
        this.text = text;
    }

    public Post(Long id, @NotBlank String text, @NotBlank User user) {
        this.id = id;
        this.text = text;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) &&
                Objects.equals(text, post.text) &&
                Objects.equals(createdAt, post.createdAt) &&
                Objects.equals(user, post.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, createdAt, user);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", createdAt=" + createdAt +
                ", user=" + user +
                '}';
    }
}
