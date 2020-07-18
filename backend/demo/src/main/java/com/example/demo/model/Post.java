package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.UUID;

public class Post {
   private final UUID id;
   public final User author;
   public String contents;

   //private Date datePosted;
    private long datePosted;

    public Post(@JsonProperty("id") UUID id, @JsonProperty("author") User author, @JsonProperty("contents") String contents) {
        this.id = id;
        this.author = author;
        this.contents = contents;
        //datePosted = new Date();
        datePosted = new Date().getTime();
    }

    public Post(UUID id, User author, String contents, Date datePosted) {
        this.id = id;
        this.author = author;
        this.contents = contents;
        this.datePosted = datePosted.getTime();
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

    public long getDatePosted() {
        return datePosted;
    }
}
