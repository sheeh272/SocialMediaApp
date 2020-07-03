package com.example.demo.dao;

import com.example.demo.dao.RowMappers.UserRowMapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("defultValidation")

public class JWTDataAccessService implements JWTDao{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JWTDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean validateUser(String username, String passcode){
        String sql = "SELECT id, displayName, birthday, loginName FROM users WHERE loginName = ? AND passcode = ?";
        List<User> test = jdbcTemplate.query(sql,new Object[]{username,passcode},new UserRowMapper());
        if(test.size() == 1) {
            return true;
        }
        else {
            return false;
        }
    }

    public UUID getUserId(String userName){
        String sql = "SELECT id, displayName, birthday, loginName FROM users WHERE loginName = ?";
        User user = jdbcTemplate.queryForObject(sql,new Object[]{userName},new UserRowMapper());
        return user.getId();
    }

}
