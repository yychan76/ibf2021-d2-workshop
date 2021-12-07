package ibf2021.d3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FixedDepositAccountTest {
    private static final double DELTA = 1e-5;
    private final float DEFAULT_INTEREST = 3f;
    private final int DEFAULT_DURATION = 6;

    @Test
    public void accountCreation_CreateWithNameAndBalance_ShouldCreateAccount() {
        FixedDepositAccount account = new FixedDepositAccount("Jar Jar Binks", 100f);
        assertEquals("Jar Jar Binks", account.getName());
        assertEquals(101.5f, account.getBalance());
        assertEquals(DEFAULT_INTEREST, account.getInterest());
        assertEquals(DEFAULT_DURATION, account.getDuration());
    }

    @Test
    public void accountCreation_CreateWithNameBalanceInterest_ShouldCreateAccount() {
        FixedDepositAccount account = new FixedDepositAccount("Jar Jar Binks", 100f, 5f);
        assertEquals("Jar Jar Binks", account.getName());
        assertEquals(102.50f, account.getBalance(), DELTA);
        assertEquals(5f, account.getInterest());
        assertEquals(DEFAULT_DURATION, account.getDuration());
    }

    @Test
    public void accountCreation_CreateWithNameBalanceInterestDuration_ShouldCreateAccount() {
        FixedDepositAccount account = new FixedDepositAccount("Jar Jar Binks", 100f, 5f, 12);
        assertEquals("Jar Jar Binks", account.getName());
        assertEquals(105f, account.getBalance(), DELTA);
        assertEquals(5f, account.getInterest());
        assertEquals(12, account.getDuration());
    }

    @Test
    public void depositAmount_DepositValidAmount_ShouldNotUpdateBalance() {
        FixedDepositAccount account = new FixedDepositAccount("Jar Jar Binks", 100f, 5f, 12);
        float initialBalance = account.getBalance();
        account.deposit(50f);
        assertEquals(initialBalance, account.getBalance(), DELTA);
    }

    @Test
    public void depositAmount_DepositNegativeAmount_ShouldNotUpdateBalance() {
        FixedDepositAccount account = new FixedDepositAccount("Jar Jar Binks", 100f, 5f, 12);
        float initialBalance = account.getBalance();
        account.deposit(-50f);
        assertEquals(initialBalance, account.getBalance(), DELTA);
    }

    @Test
    public void withdrawAmount_WithdrawValidAmount_ShouldNotUpdateBalance() {
        FixedDepositAccount account = new FixedDepositAccount("Jar Jar Binks", 100f, 5f, 12);
        float initialBalance = account.getBalance();
        account.withdraw(50f);
        assertEquals(initialBalance, account.getBalance(), DELTA);
    }

    @Test
    public void withdrawAmount_WithdrawNegativeAmount_ShouldNotUpdateBalance() {
        FixedDepositAccount account = new FixedDepositAccount("Jar Jar Binks", 100f, 5f, 12);
        float initialBalance = account.getBalance();
        account.withdraw(-50f);
        assertEquals(initialBalance, account.getBalance(), DELTA);
    }
}
