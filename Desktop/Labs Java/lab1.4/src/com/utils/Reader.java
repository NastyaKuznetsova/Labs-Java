package com.utils;

import java.util.Scanner;

public class Reader {
    private Scanner scanner;

    public Reader() {
        scanner = new Scanner(System.in);
    }

    public String readString() {
        return scanner.nextLine();
    }

    public int readInt() {
        return Integer.valueOf(scanner.nextLine());
    }

    public void destroy() {
        scanner.close();
    }
}
