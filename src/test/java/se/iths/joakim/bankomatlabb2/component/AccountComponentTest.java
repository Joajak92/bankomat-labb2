package se.iths.joakim.bankomatlabb2.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountComponentTest {

    private AccountComponent accountComponent;

    @BeforeEach
    public void setUp() {
        accountComponent = new AccountComponent();
    }

    @Test
    public void shouldDepositAndIncreaseBalance() {
        accountComponent.deposit(10);

        assertEquals(10, accountComponent.showBalance());
    }

    @Test
    public void shouldWithdrawAndDecreaseBalance() {
        accountComponent.withdraw(10);

        assertEquals(-10, accountComponent.showBalance());
    }

    @Test
    public void shouldDepositAndWithdraw() {
        accountComponent.deposit(100);
        accountComponent.withdraw(50);

        assertEquals(50, accountComponent.showBalance());
    }

    @Test
    public void shouldCheckIfBalanceIsZeroFromTheBeginning() {
        accountComponent.showBalance();

        assertEquals(0, accountComponent.showBalance());
    }
}
