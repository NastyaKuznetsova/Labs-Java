package com.account;

import com.utils.CardList;

public class AccountingSystem {
    private Account[] accounts;

    public AccountingSystem() {
        accounts = new Account[0];
    }

    public void addAccount(Account account) {
        Account[] newAccounts = new Account[accounts.length + 1];
        System.arraycopy(accounts, 0, newAccounts, 0, accounts.length);
        newAccounts[accounts.length] = account;
        accounts = newAccounts;
    }

    public Account findAccountById(String accountId) {
        Account foundAcc = null;
        for (Account account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                foundAcc = account;
                break;
            }
        }
        return foundAcc;
    }

    public Card[] getAllCards() {
        CardList list = new CardList();
        for (Account account : accounts) {
            list.addCard(account.getLinkedCards());
        }
        return list.getCards();
    }

    public Card findCardByNumber(String cardNum) {
        Card[] cards = getAllCards();
        for (Card card: cards) {
            if (card.getCardNum().equals(cardNum)) {
                return card;
            }
        }
        return null;
    }

    public void setAccounts(Account[] accounts) {
        this.accounts = accounts;
    }
}
