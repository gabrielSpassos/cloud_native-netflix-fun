package com.gabrielspassos.poc.model;

public class AccountModel {

    private String code;
    private double balance;
    private PersonModel person;

    public AccountModel(String code, double balance, PersonModel person) {
        this.code = code;
        this.balance = balance;
        this.person = person;
    }

    public AccountModel() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public PersonModel getPerson() {
        return person;
    }

    public void setPerson(PersonModel person) {
        this.person = person;
    }
}
