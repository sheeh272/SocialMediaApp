package com.example.demo.api;

import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/post")
@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public  void addPost(@RequestBody Post post){
        postService.addPost(post);
    }

//    @GetMapping
//    public List<Post> getAllPosts(){
//        return postService.getAllPosts();
//    }

//    @GetMapping(value="user_id")
//    public List<Post> getUserPosts(@RequestParam("user_id") UUID user_id){
//        return postService.getUserPosts(user_id);
//    }

    @GetMapping
    public List<Post> getUserPosts(@RequestParam(required = false) UUID user_id){
        if(user_id == null) {
            return postService.getAllPosts();
        }
        else{
            return postService.getUserPosts(user_id);
        }
    }


    @GetMapping(path="{id}")
    public Post getPost(@PathVariable("id") UUID id){
        return postService.getPost(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public int deletePost(@PathVariable("id") UUID id){
        return postService.deletePost(id);
    }

    @PutMapping(path = "{id}")
    public void updatePost(@PathVariable("id") UUID id, @RequestBody Post post){
        postService.updatePost(id,post);
    }
}
