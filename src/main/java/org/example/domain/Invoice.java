package org.example.domain;

public class Invoice {

    private String customer;
    private Performance[] performances;

    public String getCustomer() {
        return customer;
    }

    public Performance[] getPerformances() {
        return performances;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setPerformances(Performance[] performances) {
        this.performances = performances;
    }
}
