package se.iths.joakim.bankomatlabb2.component;

import org.springframework.stereotype.Component;

@Component
public class AccountComponent {
    private int balance = 0;

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public int balance() {
        return balance;
    }
}
