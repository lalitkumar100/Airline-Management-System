package com.crimsonlogic.arilinemanangmentsystem.service;

import com.crimsonlogic.arilinemanangmentsystem.model.Payment;
import com.crimsonlogic.arilinemanangmentsystem.utility.InputUtil;

public class PaymentService {

      InputUtil input = new InputUtil();

        public Payment makePayment(double amount) {

            String paymentMethod = choosePaymentType();

            return new Payment(
                    amount,
                    true,
                    paymentMethod
            );
        }
     String choosePaymentType() {

        while (true) {

            System.out.println("\n========== PAYMENT METHOD ==========");
            System.out.println("1. UPI");
            System.out.println("2. Credit Card");
            System.out.println("3. PayPal");
            System.out.println("0. Cancel Payment");

            int choice = input.getInt("Enter Choice : ");

            switch (choice) {

                case 1:
                    return Payment.UPI;

                case 2:
                    return Payment.CREDIT_CARD;

                case 3:
                    return Payment.PAYPAL;

                case 0:
                    return null;

                default:
                    System.out.println("Invalid Choice. Please try again.");
            }
        }
    }

}
