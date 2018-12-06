package com.account;

public class Account {
    private String accountId;
    private Card[] linkedCards;
    private String holderUsername;

    public Account() {
        linkedCards = new Card[0];
        accountId = String.valueOf(System.currentTimeMillis());
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Card[] getLinkedCards() {
        return linkedCards;
    }

    public void addCard(Card card) {
        Card[] newCards = new Card[linkedCards.length + 1];
        System.arraycopy(linkedCards, 0, newCards, 0, linkedCards.length);
        newCards[linkedCards.length] = card;
        linkedCards = newCards;
    }

    public void setLinkedCards(Card[] linkedCards) {
        this.linkedCards = linkedCards;
    }

    public String getHolderUsername() {
        return holderUsername;
    }

    public void setHolderUsername(String holderUsername) {
        this.holderUsername = holderUsername;
    }

    public Card findCardByNum(String cardNum) {
        Card foundCard = null;
        for (Card card : linkedCards) {
            if (card.getCardNum().equals(cardNum)) {
                foundCard = card;
                break;
            }
        }
        return foundCard;
    }
}
