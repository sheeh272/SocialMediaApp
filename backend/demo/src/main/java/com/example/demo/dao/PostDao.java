package com.example.demo.dao;

import com.example.demo.model.Post;
import com.example.demo.model.User;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

public interface PostDao {
    int insertPost(UUID id, Post post);

    default int insertPost(Post post){
        UUID id = UUID.randomUUID();
        return insertPost(id, post);
    }

    List<Post> getAllPosts();

    //id in parameters is user id not post id
    List<Post> selectUserPosts(UUID id);

    Optional<Post> selectPost(UUID id);

    int deletePost(UUID id);

    int updatePost(UUID id,Post post);
}
