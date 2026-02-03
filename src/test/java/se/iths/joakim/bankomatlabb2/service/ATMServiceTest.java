package se.iths.joakim.bankomatlabb2.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.joakim.bankomatlabb2.component.AccountComponent;
import se.iths.joakim.bankomatlabb2.exception.InsufficientFundsException;
import se.iths.joakim.bankomatlabb2.exception.InvalidAmountException;
import se.iths.joakim.bankomatlabb2.exception.MaxWithdrawalExceededException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ATMServiceTest {

    @InjectMocks
    private ATMService atmService;

    @Mock
    private AccountComponent accountComponent;

    @Test
    public void successfulWithdraw() {
        when(accountComponent.balance()).thenReturn(6000);

        atmService.withdraw(2000);

        verify(accountComponent).withdraw(2000);
    }

    @Test
    public void successfulDeposit() {
        atmService.deposit(2000);

        verify(accountComponent).deposit(2000);
    }

    @Test
    public void checkBalanceSuccessfully() {
        when(accountComponent.balance()).thenReturn(6000);

        int balance = atmService.checkBalance();

        assertEquals(6000, balance);
        verify(accountComponent).balance();
    }

    @Test
    public void withdrawNegativeAmountThrowsInvalidAmountException() {
        InvalidAmountException invalidAmount = assertThrows(InvalidAmountException.class, () ->
                atmService.withdraw(-2000));

        assertEquals("Summan du vill ta ut måste vara större än 0.", invalidAmount.getMessage());
    }

    @Test
    public void depositNegativeAmountThrowsInvalidAmountException() {
        InvalidAmountException invalidAmount = assertThrows(InvalidAmountException.class, () ->
                atmService.deposit(-2000));

        assertEquals("Summan du vill sätta in måste vara större än 0.", invalidAmount.getMessage());
    }

    @Test
    public void exceedWithdrawalLimitThrowsMaxWithdrawalExceededException() {
        MaxWithdrawalExceededException exceededLimit = assertThrows(MaxWithdrawalExceededException.class, () ->
                atmService.withdraw(8000));

        assertEquals("Du kan inte ta ut mer än 5000kr åt gången.", exceededLimit.getMessage());
    }

    @Test
    public void insufficientFundsThrowsInsufficientFundsException() {
        InsufficientFundsException insufficientFunds = assertThrows(InsufficientFundsException.class, () ->
                atmService.withdraw(4000));

        assertEquals("Ditt saldo är för lågt.", insufficientFunds.getMessage());
    }
}
