package com.example.demo.dao;

import com.example.demo.SocialMediaProjectApplication;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SocialMediaProjectApplication.class)
public class PostDataAccessServiceTest {

    @Autowired
    private PostDataAccessService postDataAccessService;

    @Autowired
    private UserDataAccessService userDataAccessService;

    void inituser(User user){
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date birthday = new Date();

        try {
            birthday = simpleDateFormat.parse("2018-12-05");
        }
        catch(Exception e){
            System.out.println(e);
        }
        user.setBirthday(birthday);
        user.setDisplayName("testUser");
        user.setLoginName("testUser");
        user.setPasscode("test");
    }

    @Test
    public void testAddAndDelete(){
        UUID id = UUID.randomUUID();
        User user = new User(id);
        inituser(user);
        assertEquals(1,userDataAccessService.insertUser(id,user));
        Post post = new Post(UUID.randomUUID(),user,"test",new Date());
        assertEquals(1,postDataAccessService.insertPost(post.getId(),post));
        assertEquals(1,postDataAccessService.deletePost(post.getId()));
        assertEquals(1,userDataAccessService.deleteUser(user.getId()));
    }

    @Test
    public void testSelectUserPosts(){
        UUID id = UUID.randomUUID();
        User user = new User(id);
        inituser(user);
        assertEquals(1,userDataAccessService.insertUser(id,user));
        Post post = new Post(UUID.randomUUID(),user,"test",new Date());

        assertEquals(0,postDataAccessService.selectUserPosts(id).size());
        assertEquals(1,postDataAccessService.insertPost(post.getId(),post));
        assertEquals(1,postDataAccessService.selectUserPosts(id).size());
        assertEquals(1,postDataAccessService.deletePost(post.getId()));
        assertEquals(1,userDataAccessService.deleteUser(user.getId()));
    }

    @Test
    public void testSelectAllPosts(){
        UUID id = UUID.randomUUID();
        User user = new User(id);
        inituser(user);
        assertEquals(1,userDataAccessService.insertUser(id,user));
        Post post = new Post(UUID.randomUUID(),user,"test",new Date());

        int numPosts = postDataAccessService.getAllPosts().size();
        assertEquals(1,postDataAccessService.insertPost(post.getId(),post));
        assertEquals(numPosts+1,postDataAccessService.getAllPosts().size());
        assertEquals(1,postDataAccessService.deletePost(post.getId()));
        assertEquals(1,userDataAccessService.deleteUser(user.getId()));
    }

    @Test
    public void testSelectPost(){
        UUID id = UUID.randomUUID();
        User user = new User(id);
        inituser(user);
        assertEquals(1,userDataAccessService.insertUser(id,user));
        Post post = new Post(UUID.randomUUID(),user,"test",new Date());
        assertEquals(1,postDataAccessService.insertPost(post.getId(),post));
        assertEquals("test",postDataAccessService.selectPost(post.getId()).get().contents);
        assertEquals(1,postDataAccessService.deletePost(post.getId()));
        assertEquals(1,userDataAccessService.deleteUser(user.getId()));
    }

    @Test
    public void testUpdate(){
        UUID id = UUID.randomUUID();
        User user = new User(id);
        inituser(user);
        assertEquals(1,userDataAccessService.insertUser(id,user));
        Post post = new Post(UUID.randomUUID(),user,"test",new Date());
        assertEquals(1,postDataAccessService.insertPost(post.getId(),post));

        Post post2 = new Post(post.getId(),user,"testUpdate",new Date());
        assertEquals(1,postDataAccessService.updatePost(post2.getId(),post2));
        assertEquals("testUpdate",postDataAccessService.selectPost(post2.getId()).get().contents);
        assertEquals(1,postDataAccessService.deletePost(post2.getId()));
        assertEquals(1,userDataAccessService.deleteUser(user.getId()));
    }

}