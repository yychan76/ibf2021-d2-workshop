package ibf2021.d3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;


public class BankAccount {
/* all members are private. You should provide getters and setters.
o account holder’s name - string. This is a read only property viz. cannot be changed once it is set
o randomly generated account number - string
o account balance - float
o transactions for operations performed on the account - a list of strings
o closed to indicate if this account has been closed - boolean
o account creating date
o account closing date
account holder’s name and account number are read only properties. They are set when this class is created and cannot be changed during the lifetime of the instance. */
    final private String name;
    final private String accountNumber;
    private float balance;
    private List<String> transactions;
    private boolean closed = false;
    private String accountCreateDate;
    private String accountCloseDate;
    final private int ACCOUNT_NUMBER_LENGTH = 10;

    // constructors
    public BankAccount(String name) {
        this.name = name;
        this.accountNumber = generateAccountNumber(ACCOUNT_NUMBER_LENGTH);
        setBalance((float) 0.0);
        transactions = new ArrayList<String>();
    }

    public BankAccount(String name, float initialBalance) {
        this.name = name;
        this.accountNumber = generateAccountNumber(ACCOUNT_NUMBER_LENGTH);
        setBalance(initialBalance);
        transactions = new ArrayList<String>();
    }

    private String generateAccountNumber(int length) {
        Random random = new Random();
        String accountNumber = random.ints(0, 10).limit(length).collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
        return accountNumber;
    }

    // private String generateMathRandom(int length) {
    //     return Integer.toString((int) (Math.random() * Math.pow(10, length)));
    // }

    // methods
    public String getName() {
        return this.name;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public float getBalance() {
        return this.balance;
    }

    /* Note that if setting this setBalance method to public, child classes would
    be able to override this method, which then takes precedence when the parent
    constructor is called

    >>calling an overridable method from a constructor is a well-known antipattern.

    https://stackoverflow.com/questions/16558747/overriding-a-base-class-method-in-a-derived-class
    */
    private void setBalance(float balance) {
        this.balance = balance;
    }

    public List<String> getTransactions() {
        return this.transactions;
    }

    public void addTransaction(String transaction) {
        this.transactions.add(transaction);
    }

    public boolean isClosed() {
        return this.closed;
    }

    public boolean getClosed() {
        return this.closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public String getAccountCreateDate() {
        return this.accountCreateDate;
    }

    public void setAccountCreateDate(String accountCreateDate) {
        this.accountCreateDate = accountCreateDate;
    }

    public String getAccountCloseDate() {
        return this.accountCloseDate;
    }

    public void setAccountCloseDate(String accountCloseDate) {
        this.accountCloseDate = accountCloseDate;
    }

    public void deposit(float amount) {
        if (amount < 0 || isClosed()) {
            throw new IllegalArgumentException();
        }
        String transaction = String.format("deposit $%.2f", amount) + " at " + Calendar.getInstance().getTimeInMillis();
        addTransaction(transaction);
        balance += amount;
    }

    public void withdraw(float amount) {
        if (amount < 0 || isClosed() || amount > getBalance()) {
            throw new IllegalArgumentException();
        }
        String transaction = String.format("withdraw $%.2f", amount) + " at " + Calendar.getInstance().getTimeInMillis();
        addTransaction(transaction);
        balance -= amount;
    }

    public static void main(String[] args) {
        final int some;
        final String name;
        some = 3;
        name = "test";
        System.out.println(name);
        System.out.println(some);
        BankAccount baX = new BankAccount("MrX");
        BankAccount baY = new BankAccount("MrY", 1234);
        baX.deposit(1337);
        baY.withdraw(1232);
        baY.withdraw(1);
        // baY.withdraw(2);
        List<BankAccount> accts = Arrays.asList(baX, baY);
        for (BankAccount acct : accts) {
            System.out.printf("Bank Account of %s [%s] has $%.2f%n", acct.getName(), acct.getAccountNumber(), acct.getBalance());
            System.out.println(acct.getTransactions());
        }
    }
}
