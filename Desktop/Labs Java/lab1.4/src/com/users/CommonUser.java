package com.users;

public class CommonUser extends User {

    public CommonUser(String username, String password) {
        setUsername(username);
        setPassword(password);
        setAccessLevel(0);
    }

    @Override
    public String toString() {
        return "Username: " + getUsername() + "; AccountId: " + getAccountId();
    }
}
