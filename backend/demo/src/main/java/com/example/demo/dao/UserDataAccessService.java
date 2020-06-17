package com.example.demo.dao;

import com.example.demo.model.User;
import com.example.demo.dao.RowMappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("SQL_DB")
public class UserDataAccessService implements UserDao {
    private static List<User> DB = new ArrayList<>();

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertUser(UUID id, User user){
        String sql = "INSERT INTO users(id,name,birthday) VALUES(?,?,?)";
        try {
            jdbcTemplate.update(sql, id, user.getName(), user.getBirthday());
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }

    @Override
    public List<User> selectAll_Users(){
        String sql = "SELECT id, name, birthday FROM users";
        return jdbcTemplate.query(sql,new UserRowMapper());
    }

    @Override
    public Optional<User> selectUser(UUID id) {
        String sql = "SELECT id, name, birthday FROM users WHERE id = ?";
        return Optional.of(jdbcTemplate.queryForObject(sql,new Object[]{id}, new UserRowMapper()));
    }

    @Override
    public int deleteUser(UUID id) {
        Optional<User> user = selectUser(id);
        String sql = "DELETE from users WHERE id = ?";
        if(user.isPresent()){
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
    public int updateUser(UUID id, User user) {
        String sql = "UPDATE users SET id = ?, name = ?, birthday = ?  WHERE id = ?";
        try {
            jdbcTemplate.update(sql, id, user.getName(), user.getBirthday(),id);
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }
}
