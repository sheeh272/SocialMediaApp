package com.example.demo.service;
import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("SQL_DB") UserDao userDao) {
        this.userDao = userDao;
    }

    public int addUser(User user){
        return userDao.insertUser(user);
    }

    public List<User> getAll_Users(){
        return userDao.selectAll_Users();
    }

    public Optional<User> getUser(UUID id) {return  userDao.selectUser(id); }

    public int deleteUser(UUID id) {return userDao.deleteUser(id);}

    public int updateUser(UUID id, User user) {return userDao.updateUser(id,user);}

    public  Optional<User> validateUser(String username, String passcode){
        return userDao.validateUser(username,passcode);
    }
}
