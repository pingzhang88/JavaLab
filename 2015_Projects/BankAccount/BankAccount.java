package Asg3;

import static java.lang.System.out;


/**
 * This class models a bank account identified by three attributes:
 * accountNumber, balance, and owner
 *
 * @author Ping Zhang Oct. 2015
 * @version %I%, %G%
 * @since 1.0
 */
abstract public class BankAccount {

    static private int nextAccountNumber = 1001;

    /**
     * A unique and immutable integer, automatically generated during
     * construction.
     */
    protected final int ACCOUNT_NUMBER;

    /**
     * The bank customer.
     */
    protected Customer owner;

    /**
     * The balance of the bank account
     */
    protected double balance;

    /**
     * The no-argument constructor
     */
    protected BankAccount() {
        this(new Customer());
    }

    /**
     * A normal constructor that receives a Customer as its owner
     *
     * @param owner
     */
    protected BankAccount(Customer owner) {
        ACCOUNT_NUMBER = nextAccountNumber;
        this.owner = owner;
        this.balance = 0.0;
        ++nextAccountNumber;
    }

    /**
     * Return the account number for a new customer
     *
     * @return
     */
    protected int getACCOUNT_NUMBER() {
        return ACCOUNT_NUMBER;
    }

    /**
     * @return the owner
     */
    protected Customer getOwner() {
        return new Customer(this.owner);
    }

    /**
     * @return the account balance
     */
    protected double getBalance() {
        return balance;
    }

    /**
     * Set the account owner to a Customer parameter
     *
     * @param owner the customer of the account
     */
    protected void setOwner(Customer owner) {
        this.owner = owner;
    }

    /**
     * To withdraw a given amount from the account, with a boolean return type
     * to specify the result.
     *
     * @param amount a given withdraw amount which should not exceed the
     * balance.
     * @return true if the withdraw succeeds, false for a failure
     */
    protected boolean withdraw(double amount) {
        if (amount > balance) {
            sopln("Error: withdraw amount exceeds account balance.");
            sopln("The balance does not change.");
            return false;
        } else if (amount < 0) {
            sopln("Error: cannot deposit negative amounts into the account.");
            sopln("The balance does not change.");
            return false;
        } else {
            balance -= amount;
            return true;
        }
    }

    /**
     * To deposit a given amount to the account, with a boolean return type to
     * specify the result.
     *
     * @param amount a given withdraw amount which should not be negative.
     * @return true if the deposit succeeds, false for a failure
     */
    protected boolean deposit(double amount) {
        if (amount < 0) {
            sopln("Error: cannot deposit negative amounts into the account.");
            sopln("The balance does not change.");
            return false;
        } else {
            balance += amount;
            return true;
        }
    }

    /**
     * To withdraw a given amount from this account and deposit the amount to a
     * specified bank account of any type.
     * Used by {@link #transferTo(double, Asg3.BankAccount) }
     *
     * @param amount a given transfer amount
     * @param recipient the bank account to receive the money
     * @return true if transfer succeeds
     */
    protected boolean transferTo(double amount, BankAccount recipient) {
        if (withdraw(amount)) {
            recipient.deposit(amount);
            return true;
        }
        return false;
    }

    /**
     * To withdraw a given amount from another given account and deposit the
     * amount to this bank account.
     * using {@link #transferFrom(double, Asg3.BankAccount)}
     *
     * @param amount a given transfer amount
     * @param sender the bank account to send the money to this account.
     * @return true if transfer succeeds
     */
    protected boolean transferFrom(double amount, BankAccount sender) {
        return sender.transferTo(amount, this);
    }

    /**
     * An abstract method performPeriodicProcessing that must be overridden by
     * all subclasses of this general bank account to implement their own
     * special periodic processing, such as paying interest to this account,
     * deducting fees from this account, etc.
     */
    protected abstract void performPeriodicProcessing();

    @Override
    public String toString() {
        return "\n"
                + owner + "\n"
                + "ACCOUNT_NUMBER: " + ACCOUNT_NUMBER + "\n"
                + "Balance: " + balance;
    }

    /**
     * To compare two BankAccount objects based on whether they have identical
     * account number.
     *
     * @param obj another BankAccount object to compare with this account
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        //qqq: why auto generated equals does not include this?
        if (this == obj) {
            return true;
        }

        final BankAccount other = (BankAccount) obj;

        if (this.ACCOUNT_NUMBER != other.ACCOUNT_NUMBER) {
            return false;
        }

        return true;
    }
    
    /**
     * A simplified method for System.out.println method
     * @param obj the Object to be printed
     */
    public static void sopln(Object obj) {
        out.println(obj);
    }    

}
