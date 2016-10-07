package com.maliao.hellomvc.entity;

/**
 * Created by minjj on 2016/9/16 0016.
 */
public class User {

    private String userName;

    private Integer id;

    private Integer gender;

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", id=" + id +
                ", gender=" + gender +
                '}';
    }
}
