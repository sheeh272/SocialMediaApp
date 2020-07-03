package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;
import java.util.UUID;
import java.util.Date;

public class User {
    private String displayName;
    private final UUID id;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    private String loginName;
    private String passcode;

    private List<UUID> friendsList;

    public User(@JsonProperty("id") UUID id, @JsonProperty("displayName") String displayName,
                @JsonProperty("birthday") Date birthday, @JsonProperty("loginName") String loginName,
                @JsonProperty("passcode") String passcode) {
        this.displayName = displayName;
        this.id = id;
        this.birthday = birthday;
        this.loginName = loginName;
        this.passcode = passcode;
    }

    public User(UUID id, String displayName, Date birthday){
        this.displayName = displayName;
        this.id = id;
        this.birthday = birthday;
    }

    public User(UUID id, String displayName, Date birthday, String loginName){
        this.displayName = displayName;
        this.id = id;
        this.birthday = birthday;
        this.loginName = loginName;
    }

    public UUID getId() {
        return id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public List<UUID> getFriendsList() {
        return friendsList;
    }

    public void addFriend(UUID newFriend){
        friendsList.add(newFriend);
    }
}
