package com.crimsonlogic.arilinemanangmentsystem.model;

import com.crimsonlogic.arilinemanangmentsystem.utility.IdGenerator;

public class Passenger extends Human {

    private String passengerId;
    private  String password;
    private LoyaltyAccount account ;
    public Passenger(Human human, String password) {
        super(human);
        this.passengerId = IdGenerator.generatePassengerId();
        account = createLoyaltyAccount();
        this.password =password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public LoyaltyAccount getAccount() {
        return account;
    }

    public void setAccount(LoyaltyAccount account) {
        this.account = account;
    }

    @Override
    public void displayInfo() {

        System.out.println("===== Passenger Details =====");
        System.out.println("Passenger Id   :"+passengerId);
        super.displayInfo();
        account.displayInfo();
    }

    public  LoyaltyAccount  createLoyaltyAccount(){
       return new LoyaltyAccount(this.passengerId);
    }
}
