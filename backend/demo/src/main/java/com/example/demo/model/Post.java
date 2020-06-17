package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Post {
   private final UUID id;
   public final User author;
   public String contents;

    public Post(@JsonProperty("id") UUID id, @JsonProperty("author") User author, @JsonProperty("contents") String contents) {
        this.id = id;
        this.author = author;
        this.contents = contents;
    }

    public User getAuthor() {
        return author;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String postText) {
        this.contents = postText;
    }

    public UUID getId() {
        return id;
    }
}
