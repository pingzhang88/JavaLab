/**
 * This class models a special type of BankAccount, which inherits the
 * functionality of a BankAccount, but also charges fees on transactions
 * (withdraws and deposits) made on the account.
 *
 * @author Ping Zhang Oct. 2015
 * @version %I%, %G%
 * @since 1.0
 */
public class CheckingAccount extends BankAccount {

    /**
     * The amount of all the transactions of withdraws and deposits
     */
    protected int transactions;

    /**
     * The transaction fee of each transaction.
     */
    protected double transactionFee;

    CheckingAccount() {
        super();
        transactionFee = 0.0;
    }

    CheckingAccount(Customer owner, double transactionFee) {
        super(owner);
        this.transactionFee = transactionFee;
    }

    /**
     * Call the overridden method first, if it succeeds, increment the amount of
     * transactions.
     *
     * @return a boolean value to mark whether the deposit succeeds or not.
     */
    
    @Override
    protected boolean deposit(double amount) {
        if (super.deposit(amount)) {
            ++transactions;
            return true;
        }
        return false;
    }


    /**
     * Call the overridden method first, if it succeeds, increment the amount of
     * transactions.
     *
     * @return a boolean value to mark whether the withdrawal succeeds or not.
     */
    @Override
    protected boolean withdraw(double amount) {
        if (super.withdraw(amount)) {
            ++transactions;
            return true;
        }
        return false;
    }

    /**
     * To determine the transaction fee by multiplying the transactionFee by the
     * number of transactions made by this account.
     *
     * @return the amount of transaction fee.
     */
    protected double CalculateFees() {
        return transactionFee * transactions;
    }

    /**
     * To implement the performPeriodicProcessing method specified by its
     * superclass BankAccount. It calculate fees, and then withdraw the fee
     * amount from this account and reset the transactions to zero.
     */
    @Override
    protected void performPeriodicProcessing() {
        if (withdraw(CalculateFees())) {
            transactions = 0;
        }
    }

    @Override
    public String toString() {
        String s = super.toString() + "\n"
                + "Checking Account" + "\n"
                + "Transctions: " + transactions;
        return s;
    }

}
