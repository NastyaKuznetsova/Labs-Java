package com.account;

public class Card {
    private String accountId;
    private int balance;
    private String cardNum;
    private int creditLimit = -1500;
    private boolean isActive;

    public Card() {
        cardNum = String.valueOf(System.currentTimeMillis());
        balance = 0;
        isActive = true;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public boolean setBalance(int howMuch) {
        if (isActive) {
            if (balance < creditLimit && howMuch < 0) {
                return false;
            }
            balance = balance + howMuch;
            return true;
        } else return false;
    }

    public boolean canPerformOperation(int howMuch) {
        if (isActive) {
            return balance >= creditLimit || howMuch >= 0;
        } else return false;
    }

    public int getBalance() {
        return balance;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Card #" + cardNum + ";  balance: " + balance + "; Credit limit: " + (creditLimit) + "; is blocked: " + !isActive;
    }
}
