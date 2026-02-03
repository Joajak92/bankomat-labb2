package se.iths.joakim.bankomatlabb2.service;

import se.iths.joakim.bankomatlabb2.component.AccountComponent;
import se.iths.joakim.bankomatlabb2.exception.InsufficientFundsException;
import se.iths.joakim.bankomatlabb2.exception.InvalidAmountException;
import se.iths.joakim.bankomatlabb2.exception.MaxWithdrawalExceededException;

public class ATMService {
    private final AccountComponent accountComponent;
    private final int withdrawalLimit = 5000;

    public ATMService(AccountComponent accountComponent) {
        this.accountComponent = accountComponent;
    }

    public void deposit(int amount) {
        if (amount > 0) {
            accountComponent.deposit(amount);
        } else {
            throw new InvalidAmountException("Summan du vill sätta in måste vara större än 0.");
        }
    }

    public void withdraw(int amount) {
        if (amount < 0) {
            throw new InvalidAmountException("Summan du vill ta ut måste vara större än 0.");
        } else if (amount > withdrawalLimit) {
            throw new MaxWithdrawalExceededException("Du kan inte ta ut mer än 5000kr åt gången.");
        } else if (amount > accountComponent.balance()) {
            throw new InsufficientFundsException("Ditt saldo är för lågt.");
        } else {
            accountComponent.withdraw(amount);
        }
    }

    public int checkBalance() {
        return accountComponent.balance();
    }
}
