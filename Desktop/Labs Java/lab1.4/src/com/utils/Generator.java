package com.utils;

import com.account.Account;
import com.account.Card;
import com.order.Order;
import com.users.Administrator;
import com.users.CommonUser;
import com.users.User;

import java.util.Random;

public class Generator {
    private static String[] names = new String[]{
            "PC",
            "MacBook",
            "IPhone",
            "Car",
            "House",
            "Phone",
            "Boat"
    };
    private static String[] usernames = new String[] {
        "Nik",
        "Lena",
        "Vrungel",
        "Artboy",
        "Neverever",
        "Mentilda",
        "Kolya",
        "Whatever",
        "Sasuke",
        "Horse"
    };
    private static String[] passwords = new String[] {
            "123",
            "6787654",
            "1243",
            "cdvfbg",
            "098765",
            "123467",
            "1",
            "2",
            "3",
            "4"
    };
    private static Account[] accounts = new Account[0];
    private static Random random = new Random();

    public static Order[] generateOrders() {
        Order[] orders = new Order[10];
        for (int i = 0; i < orders.length; i++) {
            Order order = new Order(getName(), random.nextInt(2000));
            order.setId(String.valueOf(System.currentTimeMillis() + i));
            orders[i] = order;
        }
        return orders;
    }

    public static User[] getInitUsers() {
        User[] users = new User[10];
        for (int i = 0; i < users.length; i++) {
            User user;
            if (i == 0) {
                user = new Administrator("Nast", "123");
                Account account = new Account();
                account.setAccountId(String.valueOf(System.currentTimeMillis() + i));
                Card card = new Card();
                card.setCardNum(String.valueOf(System.currentTimeMillis() + i + i));
                card.setBalance(1000);
                account.setHolderUsername(user.getUsername());
                account.addCard(card);
                user.setAccountId(account.getAccountId());
                addAccount(account);
            } else {
                user = new CommonUser(usernames[random.nextInt(usernames.length)], passwords[random.nextInt(passwords.length)]);
                Account account = new Account();
                account.setAccountId(String.valueOf(System.currentTimeMillis() + i));
                Card card = new Card();
                card.setCardNum(String.valueOf(System.currentTimeMillis() + i + i));
                card.setBalance(random.nextInt(5000));
                account.setHolderUsername(user.getUsername());
                account.addCard(card);
                user.setAccountId(account.getAccountId());
                addAccount(account);
            }
            users[i] = user;
        }
        return users;
    }

    private static void addAccount(Account account) {
        Account[] newAccounts = new Account[accounts.length + 1];
        System.arraycopy(accounts, 0, newAccounts, 0, accounts.length);
        newAccounts[accounts.length] = account;
        accounts = newAccounts;
    }

    public static Account[] getInitAccounts() {
        return accounts;
    }

    private static String getName() {
        return names[random.nextInt(names.length)];
    }
}
