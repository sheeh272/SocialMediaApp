package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.UUID;
import java.util.Date;

public class User {
    private String name;
    private final UUID id;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    public User(@JsonProperty("id") UUID id, @JsonProperty("name") String name,
                @JsonProperty("birthday") Date birthday) {
        this.name = name;
        this.id = id;
        this.birthday = birthday;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
