import java.util.*;
public class BankSystem {
    static List<Account> accounts = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        accounts.add(new Account("1165101008", 9876, 5000));
        accounts.add(new Account("1165101009", 7820, 6000));
        accounts.add(new Account("1165101011", 6543, 7000));
        accounts.add(new Account("1165101012", 4321, 8000));
        accounts.add(new Account("1165101013", 6198, 9000));
        System.out.print("Enter last 4 digits: ");
        String last4 = sc.next();
        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();
        Account current = null;
        for (Account acc : accounts) {
            if (acc.auth(last4, pin)) {
                current = acc;
                break;
            }
        }
        if (current == null) {
            System.out.println("Sorry invalid credentials kindly enter valid details");
            return;
        }
        System.out.println("\nLogin Successful\n");
        while (true) {
            System.out.println("\n1.Deposit\n2.Withdraw\n3.Transfer\n4.Statement\n5.Balance\n6.FastCash\n7.Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter amount: ");
                    current.deposit(sc.nextDouble());
                    break;
                case 2:
                    System.out.print("Enter amount: ");
                    current.withdraw(sc.nextDouble());
                    break;
                case 3:
                    System.out.print("Enter receiver last 4 digits: ");
                    String rec = sc.next();
                    Account receiver = null;
                    for (Account acc : accounts) {
                        if (acc.last4Digits.equals(rec)) {
                            receiver = acc;
                            break;
                        }
                    }
                    if (receiver == null) {
                        System.out.println("Account not found");
                        break;
                    }
                    System.out.print("Enter amount:");
                    current.transfer(receiver, sc.nextDouble());
                    break;
                case 4:
                    current.showStatement();
                    break;
                case 5:
                    current.showBalance();
                    break;
                case 6:
                    System.out.println("\nFast cash:");
                    System.out.println("1.100  2.500  3.1000  4.2000");
                    int opt = sc.nextInt();
                    double amt = 0;
                    switch (opt) {
                    case 1: amt = 100; break;
                    case 2: amt = 500; break;
                    case 3: amt = 1000; break;
                    case 4: amt = 2000; break;
                    default:
                        System.out.println("Invalid option");
                    }
                    if (amt > 0) {
                    current.fastCash(amt);
                    }
                break;
                case 7:
                System.out.println("Thank you for using ATM!");
                return;
            }
        }
    }
}