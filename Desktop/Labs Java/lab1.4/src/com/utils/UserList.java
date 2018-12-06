package com.utils;

import com.users.User;

public class UserList {
    private User[] users;

    public UserList() {
        users = new User[0];
    }

    public void addUser(User user) {
        User[] newUsers = new User[users.length + 1];
        System.arraycopy(users, 0, newUsers, 0, users.length);
        newUsers[users.length] = user;
        this.users = newUsers;
    }

    public User[] getUsers() {
        return users;
    }
}
