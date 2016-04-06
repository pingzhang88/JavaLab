/**
 * This class models a special type of BankAccount. It inherits the
 * functionality of a BankAccount, but also earns interest on the minimum
 * account balance in a given time period.
 *
 * @author Ping Zhang Oct. 2015
 * @version %I%, %G%
 * @since 1.0
 */
public class SavingsAccount extends BankAccount {

    /**
     * The standard interest per year
     */
    protected double interestRate;

    /**
     * The balance amount which is initialized to the amount deposited into this
     * account for the very first time, and be updated to the balance amount
     * whenever when there is a transaction.
     */
    protected double minimumBalance;
    private boolean minBalanceIsInit;

    SavingsAccount() {
        super();
        interestRate = 0;
    }

    SavingsAccount(Customer owner, double interestRate) {
        super(owner);
        this.interestRate = interestRate;
    }

    /**
     * Call the overridden method first, if it succeeds, update the amount of
     * minimumBalance and reset minBalanceIsInit to true to assure the
     * minBalanceIsInit be initialized for the very first deposit
     *
     * @return a boolean value to mark whether the deposit succeeds or not.
     */
    @Override
    protected boolean deposit(double amount) {
        if (super.deposit(amount)) {
            if (!minBalanceIsInit) {
                minimumBalance = amount;
                minBalanceIsInit = true;
            }
            return true;
        }
        return false;
    }

    /**
     * Call the overridden method first, if it succeeds, update the amount of
     * minimumBalance return true.
     *
     * @return a boolean value to mark whether the withdraw succeeds or not.
     */
    @Override
    protected boolean withdraw(double amount) {
        if (super.withdraw(amount)) {
            minimumBalance = balance;
            return true;
        }
        return false;

        /*
         minimumBalance -= amount;
         if (minimumBalance < 0) {
         minimumBalance = 0;
         }
         */
    }

    /**
     * To determine the amount of interest by multiply the interest rate by
     * minimumBalance. The interestRate should be divided by 100.
     *
     * @return the amount of interest
     */
    protected double CalculateInterest() {
        return interestRate / 100 * minimumBalance;
    }

    /**
     * To implement the performPeriodicProcessing method specified by its
     * superclass BankAccount. It calculate interest, and then deposit the fee
     * amount to this balance and reset the minimumBalance to balance.
     */
    @Override
    public void performPeriodicProcessing() {
        if (deposit(CalculateInterest())) {
            minimumBalance = balance;
        }
    }

    @Override
    public String toString() {
        String s = super.toString() + "\n"
                + "Saving Account" + "\n"
                + "Interest Rate: " + interestRate;
        return s;
    }

}
