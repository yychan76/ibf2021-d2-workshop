package ibf2021.d3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void changeInterest_ChangeInterestOnce_ShouldChangeInterest() {
        FixedDepositAccount account = new FixedDepositAccount("Jar Jar Binks", 100f, 5f, 12);
        float initialInterest = account.getInterest();
        account.setInterest(initialInterest + 5);
        float interest = account.getInterest();
        assertEquals(initialInterest + 5, interest, DELTA);
    }

    @Test
    public void changeInterest_ChangeInterestMultipleTimes_ShouldThrowIllegalArgumentException() {
        FixedDepositAccount account = new FixedDepositAccount("Jar Jar Binks", 100f, 5f, 12);
        float initialInterest = account.getInterest();
        account.setInterest(initialInterest + 5);
        float interest = account.getInterest();
        assertEquals(initialInterest + 5, interest, DELTA);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.setInterest(interest + 5);
        });
        System.out.println(exception.getMessage());
    }

    @Test
    public void changeDuration_ChangeDurationOnce_ShouldChangeDuration() {
        FixedDepositAccount account = new FixedDepositAccount("Jar Jar Binks", 100f, 5f, 12);
        int initialDuration = account.getDuration();
        account.setDuration(initialDuration + 5);
        int duration = account.getDuration();
        assertEquals(initialDuration + 5, duration);
    }

    @Test
    public void changeDuration_ChangeDurationMultipleTimes_ShouldThrowIllegalArgumentException() {
        FixedDepositAccount account = new FixedDepositAccount("Jar Jar Binks", 100f, 5f, 12);
        int initialDuration = account.getDuration();
        account.setDuration(initialDuration + 6);
        int duration = account.getDuration();
        assertEquals(initialDuration + 6, duration);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.setDuration(duration + 6);
        });
        System.out.println(exception.getMessage());
    }

   /*  // this test is no longer needed because we no longer have access to setBalance from child class
    @Test
    public void setBalance_ChangeBalanceAfterAccountCreation_ShouldNotChangeBalance() {
        FixedDepositAccount account = new FixedDepositAccount("Jar Jar Binks", 100f, 5f, 12);
        float initialBalance = account.getBalance();
        account.setBalance(initialBalance + 200f);
        float balance = account.getBalance();
        assertEquals(initialBalance, balance);
    } */
}
