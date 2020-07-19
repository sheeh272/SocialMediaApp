package com.example.demo.dao;

import com.example.demo.SocialMediaProjectApplication;
import com.example.demo.dao.UserDataAccessService;
import com.example.demo.service.UserService;
import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SocialMediaProjectApplication.class)
class UserDataAccessServiceTest {

    @Autowired
    private UserService userService;

    @Autowired UserDataAccessService userDataAccessService;

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
    void inituser2(User user){
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
        user.setDisplayName("testUser2");
        user.setLoginName("testUser2");
        user.setPasscode("test");
    }

    @Test
    void addAndDeleteUser() {
        UUID id = UUID.randomUUID();
        User user = new User(id);
        inituser(user);
        assertEquals(1,userDataAccessService.insertUser(id,user));
        assertEquals(1,userDataAccessService.deleteUser(user.getId()));
    }

    @Test
    void addAndRetrive() {
        int currSize = userDataAccessService.selectAll_Users().size();
        UUID id = UUID.randomUUID();
        User user = new User(id);
        inituser(user);
        assertEquals(1,userDataAccessService.insertUser(id,user));
        assertEquals(currSize+1,userDataAccessService.selectAll_Users().size());
        assertEquals(1,userDataAccessService.deleteUser(user.getId()));
    }

    @Test
    void updateAndSelectUser() {
        UUID id = UUID.randomUUID();
        User user = new User(id);
        inituser(user);
        assertEquals(1,userDataAccessService.insertUser(id,user));
        User user2 = new User(id);
        inituser2(user2);
        assertEquals(1,userDataAccessService.updateUser(id,user2));
        assertEquals("testUser2",userDataAccessService.selectUser(id).get().getDisplayName());
        assertEquals(1,userDataAccessService.deleteUser(user.getId()));
    }

    @Test
    void AddAndValidate(){
        UUID id = UUID.randomUUID();
        User user = new User(id);
        inituser(user);
        assertEquals(1,userDataAccessService.insertUser(id,user));
        assertEquals(true,userDataAccessService.validateUser(user.getLoginName(),user.getPasscode()).isPresent());
        assertEquals(1,userDataAccessService.deleteUser(user.getId()));
    }

    @Test
    void addGetAndDeleteFriend(){
        UUID id = UUID.randomUUID();
        User user = new User(id);
        inituser(user);
        assertEquals(1,userDataAccessService.insertUser(id,user));
        UUID id2 = UUID.randomUUID();
        User user2 = new User(id2);
        inituser2(user2);
        assertEquals(1,userDataAccessService.insertUser(id2,user2));
        assertEquals(1,userDataAccessService.addFriend(id2,id));
        assertEquals(id2,userDataAccessService.getFriends(id).get(0));
        assertEquals(1,userDataAccessService.deleteFriend(id2,id));
        assertEquals(0,userDataAccessService.getFriends(id).size());
        assertEquals(1,userDataAccessService.deleteUser(user.getId()));
        assertEquals(1,userDataAccessService.deleteUser(user2.getId()));
    }
}
