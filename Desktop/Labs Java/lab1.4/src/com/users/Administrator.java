package com.users;

public class Administrator extends User {
    public Administrator(String username, String password) {
        setUsername(username);
        setPassword(password);
        setAccessLevel(1);
    }
}
