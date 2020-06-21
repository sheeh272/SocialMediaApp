package com.example.demo.dao.RowMappers;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.jdbc.core.RowMapper;
import com.example.demo.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.Date;
import java.text.SimpleDateFormat;


public class UserRowMapper implements RowMapper<User>{
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        UUID id = UUID.fromString(rs.getString("id"));
        String displayName = rs.getString("displayName");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday;
        try {
            birthday = formatter.parse(rs.getString("birthday"));
        }
        catch (Exception e){
            birthday = null;
        }

        User user = new User(id,displayName,birthday);

        return user;

    }
}
