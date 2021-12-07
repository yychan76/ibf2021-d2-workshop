package ibf2021.d3;

public class FixedDepositAccount extends BankAccount {
    private float interest = 3f;
    private int durationInMonths = 6;
    private boolean isInterestChanged = false;
    private boolean isDurationChanged = false;

    public FixedDepositAccount(String name, Float balance) {
        super(name, balance);
    }

    public FixedDepositAccount(String name, Float balance, Float interest) {
        super(name, balance);
        this.interest = interest;
    }

    public FixedDepositAccount(String name, Float balance, Float interest, Integer duration) {
        super(name, balance);
        this.interest = interest;
        this.durationInMonths = duration;
    }

    public float getInterest() {
        return this.interest;
    }

    public void setInterest(float interest) {
        if (!isInterestChanged) {
            this.interest = interest;
            isInterestChanged = true;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int getDuration() {
        return this.durationInMonths;
    }

    public void setDuration(int duration) {
        if (!isDurationChanged) {
            this.durationInMonths = duration;
            isDurationChanged = true;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void deposit(float amount) {}

    @Override
    public void withdraw(float amount) {}

    @Override
    public float getBalance() {
        float initialBalance = super.getBalance();
        float interestAmount = initialBalance * (this.interest / 100) * (this.durationInMonths / 12f);
        return initialBalance + interestAmount;
    }

    public static void main(String[] args) {
        FixedDepositAccount fd = new FixedDepositAccount("Mr X", 100f, 3f, 12);
        System.out.printf("%s Fixed Deposit Account [%s] with interest %.2f and tenure %d months has balance of $%.2f %n",
                            fd.getName(),
                            fd.getAccountNumber(),
                            fd.getInterest(),
                            fd.getDuration(),
                            fd.getBalance());
        FixedDepositAccount account = new FixedDepositAccount("Jar Jar Binks", 100f, 5f);
        System.out.printf("%s Fixed Deposit Account [%s] with interest %.2f and tenure %d months has balance of $%.2f %n",
                            account.getName(),
                            account.getAccountNumber(),
                            account.getInterest(),
                            account.getDuration(),
                            account.getBalance());
    }
}
