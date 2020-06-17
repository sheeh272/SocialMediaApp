package com.example.demo.api;

import com.example.demo.service.UserService;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/user")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public  void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @GetMapping
    public List<User> getAll_Users(){
        return userService.getAll_Users();
    }

    @GetMapping(path="{id}")
    public User getUser(@PathVariable("id") UUID id){
        return userService.getUser(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public int deleteUser(@PathVariable("id") UUID id){
        return userService.deleteUser(id);
    }

    @PutMapping(path = "{id}")
    public void updateUser(@PathVariable("id") UUID id, @RequestBody User user){
        userService.updateUser(id,user);
    }
}
