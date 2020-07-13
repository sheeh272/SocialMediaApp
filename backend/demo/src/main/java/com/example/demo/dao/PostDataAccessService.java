package com.example.demo.dao;

import com.example.demo.dao.RowMappers.PostRowMapper;
import com.example.demo.dao.RowMappers.UserRowMapper;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("Post_Repo")
public class PostDataAccessService implements PostDao{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPost(UUID id, Post post){
        String sql = "INSERT INTO posts(id,author_id,contents) VALUES(?,?,?)";
        try {
            jdbcTemplate.update(sql, id, post.getAuthor().getId(), post.getContents());
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }

    @Override
    public  List<Post> getAllPosts(){
        String sql = "SELECT users.id, displayName, birthday, loginName, posts.id AS postId, author_id, contents FROM posts LEFT OUTER JOIN users ON posts.author_id = users.id";
        return jdbcTemplate.query(sql,new PostRowMapper());
    }

    @Override
    public List<Post> selectUserPosts(UUID user_id){
        String sql = "SELECT users.id, displayName, birthday, loginName, posts.id AS postId, author_id, contents FROM posts LEFT OUTER JOIN users ON posts.author_id = users.id WHERE posts.author_id = ?";
        System.out.println(user_id);
        return jdbcTemplate.query(sql,new Object[]{user_id},new PostRowMapper());
    }

    @Override
    public Optional<Post> selectPost(UUID id){
        String sql = "SELECT users.id, displayName, birthday, loginName, posts.id AS postId, author_id, contents FROM posts LEFT OUTER JOIN users ON posts.author_id = users.id WHERE posts.id = ?";
        return Optional.of(jdbcTemplate.queryForObject(sql,new Object[]{id}, new PostRowMapper()));
    }

    @Override
    public int deletePost(UUID id){
        Optional<Post> post = selectPost(id);
        String sql = "DELETE from posts WHERE id = ?";
        if(post.isPresent()){
            try {
                jdbcTemplate.update(sql, id);
                return 1;
            }
            catch (Exception e){
                return 0;
            }
        }
        else{
            return 0;
        }
    }

    @Override
    public int updatePost(UUID id,Post post){
        System.out.println(post.getContents());
        String sql = "UPDATE posts SET id = ?, author_id = ?, contents = ?  WHERE id = ?";
        try {
            jdbcTemplate.update(sql, id, post.getAuthor().getId(), post.getContents(),id);
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }
}
