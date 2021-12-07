package ibf2021.d3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class BankAccountTest {
    private static final double DELTA = 1e-5;

    @Test
    public void accountCreation_CreateWithName_ShouldCreateAccount() {
        BankAccount account = new BankAccount("Jar Jar Binks");
        assertEquals("Jar Jar Binks", account.getName());
        assertEquals(0f, account.getBalance(), DELTA);
    }

    @Test
    public void accountCreation_CreateWithNameAndBalance_ShouldCreateAccount() {
        BankAccount account = new BankAccount("Jar Jar Binks", 1234f);
        assertEquals("Jar Jar Binks", account.getName());
        assertEquals(1234f, account.getBalance(), DELTA);
    }

    @Test
    public void depositAmount_DepositValidAmount_ShouldDeposit() {
        BankAccount account = new BankAccount("Jar Jar Binks", 100f);
        account.deposit(0.1f);
        assertEquals(100.1f, account.getBalance(), DELTA);
    }

    @Test
    public void depositAccount_DepositNegativeAmount_ShouldThrowIllegalArgumentException() {
        BankAccount account = new BankAccount("Jar Jar Binks", 100f);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-1f);
        });
        System.out.println(exception.getMessage());
    }

    @Test
    public void depositAccount_DepositIntoClosedAccount_ShouldThrowIllegalArgumentException() {
        BankAccount account = new BankAccount("Jar Jar Binks", 100f);
        account.setClosed(true);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(100f);
        });
        System.out.println(exception.getMessage());
    }

    @Test
    public void withdrawAmount_WithdrawValidAmount_ShouldWithdraw() {
        BankAccount account = new BankAccount("Jar Jar Binks", 100.1f);
        account.withdraw(100f);
        // assertTrue(Float.compare(0.1f, account.getBalance()) == 0);
        assertEquals(0.1f, account.getBalance(), DELTA);
    }

    @Test
    public void withdrawAmount_WithdrawFromClosedAccount_ShouldThrowIllegalArgumentException() {
        BankAccount account = new BankAccount("Jar Jar Binks", 100f);
        account.setClosed(true);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(100f);
        });
        System.out.println(exception.getMessage());
    }

    @Test
    public void withdrawAmount_WithdrawNegativeAmount_ShouldThrowIllegalArgumentException() {
        BankAccount account = new BankAccount("Jar Jar Binks", 1234f);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(-1f);
        });
        System.out.println(exception.getMessage());
    }

    @Test
    public void withdrawAmount_WithdrawAmountExceedingBalance_ShouldThrowIllegalArgumentException() {
        BankAccount account = new BankAccount("Jar Jar Binks", 1234f);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(1235f);
        });
        System.out.println(exception.getMessage());
    }

    @Test
    public void amountTransaction_DepositOnceWithdrawTwice_ShouldShowInHistory() {
        BankAccount account = new BankAccount("Jar Jar Binks");
        account.deposit(100f);
        account.withdraw(10f);
        account.withdraw(20f);
        List<String> transactions = account.getTransactions();
        assertEquals(3, transactions.size());
        assertTrue(transactions.get(0).startsWith("deposit"));
        assertTrue(transactions.get(1).startsWith("withdraw"));
        assertTrue(transactions.get(2).startsWith("withdraw"));
    }
}
