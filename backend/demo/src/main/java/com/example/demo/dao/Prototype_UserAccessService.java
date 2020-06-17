package com.example.demo.dao;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

@Repository("UserDao")

public class Prototype_UserAccessService implements UserDao{

    private static List<User> DB = new ArrayList<>();

    @Override
    public int insertUser(UUID id, User user){
        DB.add(new User(id, user.getName(), user.getBirthday()));
        return 1;
    }

    @Override
    public List<User> selectAll_Users(){
        return DB;
    }

    @Override
    public Optional<User> selectUser(UUID id) {
        for(int i = 0; i < DB.size(); i++){
            if(DB.get(i).getId().equals(id)){
                return Optional.of(DB.get(i));
            }
        }
        return Optional.of(null);
    }

    @Override
    public int deleteUser(UUID id) {
        Optional<User> user = selectUser(id);
        if(user.isPresent()){
            DB.remove(user.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int updateUser(UUID id, User user) {
        for(int i = 0; i < DB.size(); i++){
            if(DB.get(i).getId().equals(id)){
                DB.set(i, new User(id,user.getName(),user.getBirthday()));
                return 1;
            }
        }
        return 0;
    }
}
