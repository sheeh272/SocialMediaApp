package com.example.demo.dao.RowMappers;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class PostRowMapper implements RowMapper<Post> {

    @Override
    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserRowMapper userMapper = new UserRowMapper();
        User user = userMapper.mapRow(rs,rowNum);

        UUID post_id = UUID.fromString(rs.getString("postId"));
        String content = rs.getString("contents");
        Post post = new Post(post_id,user,content);

        return post;
    }
}
