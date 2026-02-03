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
        accountComponent.deposit(20);

        assertEquals(20, accountComponent.balance());
    }

    @Test
    public void shouldWithdrawAndDecreaseBalance() {
        accountComponent.withdraw(10);

        assertEquals(-10, accountComponent.balance());
    }

    @Test
    public void shouldDepositAndWithdraw() {
        accountComponent.deposit(100);
        accountComponent.withdraw(50);

        assertEquals(50, accountComponent.balance());
    }

    @Test
    public void shouldCheckIfBalanceIsZeroFromTheBeginning() {
        accountComponent.balance();

        assertEquals(0, accountComponent.balance());
    }
}
