package com.example.demo.service;

import com.example.demo.dao.PostDao;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {
    private final PostDao postDao;

    @Autowired
    public PostService(@Qualifier("Post_Repo") PostDao postDao) {
        this.postDao = postDao;
    }

    public int addPost(Post post){
        return postDao.insertPost(post);
    }

    public List<Post> getAllPosts() {return  postDao.getAllPosts();}

    public List<Post> getUserPosts(UUID user_id){
        return  postDao.selectUserPosts(user_id);
    }

    public Optional<Post> getPost(UUID id){
        return postDao.selectPost(id);
    }

    public int deletePost(UUID id){
        return postDao.deletePost(id);
    }

    public int updatePost(UUID id,Post post){
        return postDao.updatePost(id,post);
    }
}
