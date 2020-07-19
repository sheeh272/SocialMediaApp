package com.example.demo.api;

import com.example.demo.service.UserService;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
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
    public int addUser(@RequestBody User user){
        return userService.addUser(user);
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

    @GetMapping(params = {"userId"})
    public List<UUID> getFriends(@RequestParam UUID userId){
        return userService.getFriends(userId);
    }

    @PostMapping(params = {"newFriend","userId"})
    public int addFriend(@RequestParam UUID newFriend, @RequestParam UUID userId){
        return userService.addFriend(newFriend, userId);
    }

    @PostMapping(params = {"friendToDelete","userId"})
    public int deleteFriend(@RequestParam UUID friendToDelete, @RequestParam UUID userId){
        return userService.deleteFriend(friendToDelete, userId);
    }

    @GetMapping(params = {"name"})
    public List<User>getUserByName(@RequestParam String name){
        return userService.getUserByName(name);
    }

}
