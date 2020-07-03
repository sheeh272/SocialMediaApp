package com.example.demo.dao;

import com.example.demo.model.User;
import com.example.demo.dao.RowMappers.UserRowMapper;

import java.sql.Array;
import java.sql.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;


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
        String sql = "INSERT INTO users(id,displayName,birthday,loginName,passcode) VALUES(?,?,?,?,?)";
        //if loginName is not unique it will throw an exception
        try {
            jdbcTemplate.update(sql, id, user.getDisplayName(),
                    user.getBirthday(),user.getLoginName(),user.getPasscode());
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }

    @Override
    public List<User> selectAll_Users(){
        String sql = "SELECT id, displayName, birthday, loginName FROM users";
        return jdbcTemplate.query(sql,new UserRowMapper());
    }

    @Override
    public Optional<User> selectUser(UUID id) {
        String sql = "SELECT id, displayName, birthday, loginName FROM users WHERE id = ?";
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
        String sql = "UPDATE users SET id = ?, displayName = ?, birthday = ?  WHERE id = ?";
        try {
            jdbcTemplate.update(sql, id, user.getDisplayName(), user.getBirthday(),id);
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }

    @Override

    public Optional<User> validateUser(String username, String passcode){
        String sql = "SELECT id, displayName, birthday, loginName FROM users WHERE loginName = ? AND passcode = ?";
        List<User> test = jdbcTemplate.query(sql,new Object[]{username,passcode},new UserRowMapper());
        if(test.size() == 1) {
            return Optional.of(test.get(0));
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public List<UUID> getFriends(UUID userId){
        String sql = "SELECT friendsList FROM users WHERE id = ?";
        List<UUID> friendsList = new ArrayList<>();
        //no need to make row mapper for single use
        Array array = jdbcTemplate.queryForObject(sql, new Object[]{userId}, (rs, rowNum) ->
                rs.getArray("friendsList")
        );
        try {
            Object[] objects = (Object[]) array.getArray();
            for (int i = 0; i < objects.length; i++) {
                friendsList.add((UUID)objects[i]);
            }
            return friendsList;
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public int addFriend(UUID newFriend, UUID userId){
        String sql = "UPDATE users SET friendsList = array_append(friendsList, ?) WHERE id = ?";
        try {
            jdbcTemplate.update(sql,newFriend,userId);
            return 1;
        }
        catch (Exception e){
            return 0;
        }
    }

    @Override
    public List<User> getUserByName(String name){
        String sql = "SELECT id, displayName, birthday, loginName FROM users WHERE displayName = ? OR loginName = ?";
        return jdbcTemplate.query(sql ,new Object[]{name,name}, new UserRowMapper());
    }
}
