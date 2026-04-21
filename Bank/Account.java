import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
class Account {
    String accountNumber;
    String last4Digits;
    int pin;
    double balance;
    List<String> transactions = new ArrayList<>();
    public Account(String accno, int pin, double balance) {
        this.accountNumber = accno;
        this.last4Digits = accno.substring(accno.length() - 4);
        this.pin = pin;
        this.balance = balance;
    }
    public boolean auth(String last4, int pin) {
        return this.last4Digits.equals(last4) && this.pin == pin;
    }
    private String getTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return LocalDateTime.now().format(dtf);
    }
    public void deposit(double amount) {
        balance += amount;
        transactions.add("Deposited amount successfully" + amount + " | " + getTime());
    }
    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance");
            return;
        }
        balance -= amount;
        transactions.add("Withdraw successful" + amount + " | " + getTime());
    }
    public void fastCash(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance for Fast Cash");
            return;
        }
        balance -= amount;
        transactions.add("Fast Cash" + amount + " | " + getTime());
        System.out.println("Cash Withdrawn:" + amount);
    }
    public void transfer(Account receiver, double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance");
            return;
        }
        balance -= amount;
        receiver.balance += amount;
        transactions.add("Sent" + amount + " to " + receiver.accountNumber + "  " + getTime());
        receiver.transactions.add("Money Received Successfully" + amount + " from " + this.accountNumber + " | " + getTime());
    }
    public void showStatement() {
        System.out.println("\n Mini Statement");
        for (String t : transactions) {
            System.out.println(t);
        }
    }
    public void showBalance() {
        System.out.println("Current Balance:" + balance);
    }
}