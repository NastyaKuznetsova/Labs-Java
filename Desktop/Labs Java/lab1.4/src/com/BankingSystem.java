package com;

import com.account.Account;
import com.account.AccountingSystem;
import com.account.Card;
import com.order.Order;
import com.order.OrderingSystem;
import com.users.Administrator;
import com.users.CommonUser;
import com.users.User;
import com.utils.Generator;
import com.utils.Printer;
import com.utils.Reader;
import com.utils.UserList;

public class BankingSystem {
    private AccountingSystem accountingSystem;
    private OrderingSystem orderingSystem;
    private User[] users;
    private Reader reader = new Reader();

    private User activeUser;
    private boolean continueWork = true;

    public BankingSystem() {
        accountingSystem = new AccountingSystem();
        orderingSystem = new OrderingSystem();
        User[] users = Generator.getInitUsers();
        Account[] accounts = Generator.getInitAccounts();
        this.users = users;
        accountingSystem.setAccounts(accounts);
    }

    public void start() {
        do {
            if (activeUser == null) {
                drawInitialMenu();
            } else {
                drawMenuForUser();
            }
        } while (continueWork);
        Printer.print("Good bye!");
        reader.destroy();
    }

    private void drawMenuForUser() {
        Printer.print("Choose action: ");
        Printer.print("1 - create account");
        Printer.print("2 - add card");
        Printer.print("3 - top up card");
        Printer.print("4 - buy order");
        Printer.print("5 - view balance by card");
        Printer.print("6 - send money");
        Printer.print("7 - view cards");
        if (activeUser.getAccessLevel() == 1) {
            Printer.print("8 - view all users and cards");
            Printer.print("9 - block card by card number");
        }
        Printer.print("0 - logout");
        int choice = reader.readInt();
        switch (choice) {
            case 1:
                createAccount();
                break;
            case 2:
                addCard();
                break;
            case 3:
                topUpCard();
                break;
            case 4:
                buyOrder();
                break;
            case 5:
                viewBalance();
                break;
            case 6:
                sendMoney();
                break;
            case 7:
                viewCards();
                break;
            case 8:
                if (activeUser.getAccessLevel() == 1)
                    viewAllUsersAndCards();
                break;
            case 9:
                if (activeUser.getAccessLevel() == 1)
                    blockCardByNumber();
                break;
            case 0:
                activeUser = null;
                break;
        }
    }

    private void sendMoney() {
        Printer.print("Enter your card number: ");
        Card myCard = findCard();
        if (myCard == null) return;
        Printer.print("Enter card number target: ");
        String cardNum = reader.readString();
        Card targetCard = accountingSystem.findCardByNumber(cardNum);
        if (targetCard == null) {
            Printer.print("No such card");
            return;
        }
        Printer.print("Enter how much");
        int sum = reader.readInt();
        if (myCard.canPerformOperation(-sum)) {
            myCard.setBalance(-sum);
            targetCard.setBalance(sum);
        } else {
            Printer.print("Not enough money!");
        }
    }

    private void blockCardByNumber() {
        Printer.print("Enter card number: ");
        String cardNum = reader.readString();
        Card toBlock = accountingSystem.findCardByNumber(cardNum);
        if (toBlock == null) {
            Printer.print("No such card");
            return;
        }
        toBlock.setActive(false);
        Printer.print("Card has been blocked!");
    }

    private void viewAllUsersAndCards() {
        User[] commonUsers = getCommonUsers();
        for (User user : commonUsers) {
            Account account = accountingSystem.findAccountById(user.getAccountId());
            Printer.print(user, account);
            Printer.print("");
        }
    }

    private void createAccount() {
        if (activeUser.getAccountId() != null) {
            Printer.print("You already have an account!");
            return;
        }
        Account account = new Account();
        account.setHolderUsername(activeUser.getUsername());
        accountingSystem.addAccount(account);
        activeUser.setAccountId(account.getAccountId());
        Printer.print("Account created!");
    }

    private void addCard() {
        String accountId = activeUser.getAccountId();
        if (accountId == null) {
            Printer.print("You have to create account first!");
            return;
        }
        Account account = accountingSystem.findAccountById(accountId);
        Card card = new Card();
        card.setAccountId(account.getAccountId());
        account.addCard(card);
        Printer.print("Card has been added!");
    }

    private void topUpCard() {
        Card card = findCard();
        if (card == null) return;
        if (!card.isActive()) {
            Printer.print("This card is blocked");
            return;
        }
        Printer.print("Current balance: " + card.getBalance());
        Printer.print("How much you want to add?");
        int howMuch = reader.readInt();
        card.setBalance(howMuch);
        Printer.print("Success");
    }

    private void buyOrder() {
        if (activeUser.getAccountId() == null) {
            Printer.print("You have to create account first!");
            return;
        }
        Printer.print("choose a product(by id): ");
        Order[] orders = orderingSystem.getOrders();
        Printer.print(orders);
        String id = reader.readString();
        Order order = orderingSystem.getOrderById(id);
        if (order == null) {
            Printer.print("Order not found");
            return;
        }
        Printer.print("Type a card number: ");
        String cardNum = reader.readString();
        Account account = accountingSystem.findAccountById(activeUser.getAccountId());
        Card card = account.findCardByNum(cardNum);
        if (card == null) {
            Printer.print("No such card");
            return;
        }
        if (!card.isActive()) {
            Printer.print("This card is blocked");
            return;
        }
        boolean isSuccess = card.setBalance(-order.getPrice());
        if (isSuccess) {
            Printer.print("Success buy!");
        } else {
            Printer.print("You don't have enough money");
        }
    }

    private void viewBalance() {
        Card card = findCard();
        if (card == null) return;
        Printer.print("Current balance: " + card.getBalance());

    }

    private void viewCards() {
        if (activeUser.getAccountId() == null) {
            Printer.print("You have to create account first!");
            return;
        }
        Account account = accountingSystem.findAccountById(activeUser.getAccountId());
        Card[] cards = account.getLinkedCards();
        for (Card card : cards) {
            Printer.print(card.toString());
        }
    }

    private void drawInitialMenu() {
        Printer.print("Welcome to bank!");
        Printer.print("Please, choose an option: ");
        Printer.print("1 - Login");
        Printer.print("2 - Register");
        Printer.print("3 - Exit");
        int choice = reader.readInt();
        switch (choice) {
            case 1:
                loginUser();
                break;
            case 2:
                registerUser();
                break;
            case 3:
                continueWork = false;
                break;
            default:
                drawInitialMenu();
                break;
        }
    }

    private void registerUser() {
        Printer.print("Will it be Admin(1) or common user(2)?");
        int role = reader.readInt();
        Printer.print("Username: ");
        String username = reader.readString();
        Printer.print("Password: ");
        String password = reader.readString();
        User user;
        switch (role) {
            case 1:
                user = new Administrator(username, password);
                break;
            case 2:
                user = new CommonUser(username, password);
                break;
            default:
                user = new CommonUser(username, password);
                break;
        }
        addUser(user);
        activeUser = user;
    }

    private void loginUser() {
        Printer.print("Enter username: ");
        String username = reader.readString();
        Printer.print("Enter password: ");
        String password = reader.readString();
        User user = findUserByUsernameAndPassword(username, password);
        if (user != null) activeUser = user;
        else Printer.print("User not found");
    }

    private User findUserByUsernameAndPassword(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) return user;
            }
        }
        return null;
    }

    private Card findCard() {
        Card card;
        Printer.print("Enter card num:  ");
        String cardNum = reader.readString();
        Account account = accountingSystem.findAccountById(activeUser.getAccountId());
        if (account == null) {
            Printer.print("You don't have an account!");
            return null;
        }
        card = account.findCardByNum(cardNum);
        if (card == null) {
            Printer.print("Card not found");
            return null;
        }
        return card;
    }

    private void addUser(User user) {
        User[] newUsers = new User[users.length + 1];
        System.arraycopy(users, 0, newUsers, 0, users.length);
        newUsers[users.length] = user;
        users = newUsers;
    }

    private User[] getCommonUsers() {
        UserList list = new UserList();
        for (User user : users) {
            if (user.getAccessLevel() == 0) {
                list.addUser(user);
            }
        }
        return list.getUsers();
    }
}
