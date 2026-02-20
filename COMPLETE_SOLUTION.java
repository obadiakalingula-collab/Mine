/**
 * Payment Processing System
 * Composition and Polymorphism Design
 */

import java.util.ArrayList;
import java.util.List;

// ─────────────────────────────────────────
// PaymentMethod Interface
// ─────────────────────────────────────────
interface PaymentMethod {
    void verify();
    double getAmount();
}

// ─────────────────────────────────────────
// Bank Payment Class
// ─────────────────────────────────────────
class BankPayment implements PaymentMethod {

    private double amount;

    public BankPayment(double amount) {
        this.amount = amount;
    }

    @Override
    public void verify() {
        System.out.println("Bank confirmation verified.");
    }

    @Override
    public double getAmount() {
        return amount;
    }
}

// ─────────────────────────────────────────
// Mobile Money Payment Class
// ─────────────────────────────────────────
class MobileMoneyPayment implements PaymentMethod {

    private double amount;

    public MobileMoneyPayment(double amount) {
        this.amount = amount;
    }

    @Override
    public void verify() {
        System.out.println("Mobile Money transaction verified.");
    }

    @Override
    public double getAmount() {
        return amount;
    }
}

// ─────────────────────────────────────────
// Payment Class (Supports Split Payments)
// ─────────────────────────────────────────
class Payment {

    private List<PaymentMethod> methods = new ArrayList<>();

    public void addPaymentMethod(PaymentMethod method) {
        methods.add(method);
    }

    public void processPayment() {

        double total = 0;

        for (PaymentMethod method : methods) {
            method.verify();
            total += method.getAmount();
        }

        System.out.println("Total Paid: UGX " + total);
        System.out.println("Receipt Generated.");
    }
}

// ─────────────────────────────────────────
// Main Application
// ─────────────────────────────────────────
public class VUPay {

    public static void main(String[] args) {

        // Example: UGX 120,000 split 40% Bank + 60% Mobile Money

        double totalAmount = 120000;

        double bankAmount = totalAmount * 0.4;     // 48000
        double mobileAmount = totalAmount * 0.6;   // 72000

        Payment payment = new Payment();

        payment.addPaymentMethod(new BankPayment(bankAmount));
        payment.addPaymentMethod(new MobileMoneyPayment(mobileAmount));

        payment.processPayment();
    }
}