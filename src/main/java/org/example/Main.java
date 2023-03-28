package org.example;

import org.example.domain.Invoice;
import org.example.domain.Performance;
import org.example.domain.Play;

import java.util.HashMap;
import java.util.Map;

public class Main {

    static Chap1 chap1 = new Chap1();

    private static void initData(Invoice[] invoices, Map plays) {
        Play play1 = new Play("Hamlet", "tragedy");
        Play play2 = new Play("As You Like It", "comedy");
        Play play3 = new Play("Othello", "tragedy");
        plays.put("hamlet", play1);
        plays.put("as-like", play2);
        plays.put("othello", play3);

        Performance performance1 = new Performance("hamlet", 55);
        Performance performance2 = new Performance("as-like", 35);
        Performance performance3 = new Performance("othello", 40);
        Performance[] performances = new Performance[]{performance1, performance2, performance3};

        Invoice invoice = new Invoice();
        invoice.setCustomer("BigCo");
        invoice.setPerformances(performances);
        invoices[0] = invoice;
    }

    public static void main(String[] args) {
        Map<String, Play> plays = new HashMap<>();
        Invoice[] invoices = new Invoice[1];
        initData(invoices, plays);

        System.out.println(chap1.statement(invoices[0], plays));
    }
}