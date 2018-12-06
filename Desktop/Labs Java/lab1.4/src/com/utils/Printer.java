package com.utils;

import com.account.Account;
import com.account.Card;
import com.order.Order;
import com.users.User;

public class Printer {
    public static void print(String s) {
        System.out.println(s);
    }

    public static void print(int a) {
        System.out.println(a);
    }

    public static void print(User[] users) {
        for (User user: users) {
            print(user);
        }
    }

    public static void print(User user) {
        print(user.toString());
    }

    public static void print(User user, Account account) {
        print(user);
        print("Cards: ");
        print(account);
        print("-------------");
    }

    private static void print(Account account) {
        Card[] cards = account.getLinkedCards();
        print(cards);
    }

    private static void print(Card[] cards) {
        for (Card card : cards) {
            print(card);
        }
    }

    private static void print(Card card) {
        print(card.toString());
    }

    public static void print(Order[] orders) {
        for (Order order: orders) {
            print(order);
        }
    }

    private static void print(Order order) {
        print(order.toString());
        print("");
    }
}
