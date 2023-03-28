package org.example;

import org.assertj.core.api.Assertions;
import org.example.domain.Invoice;
import org.example.domain.Performance;
import org.example.domain.Play;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class Chap1Test {

    Map<String, Play> plays = new HashMap<>();
    Invoice[] invoices = new Invoice[1];

    Chap1 chap1 = new Chap1();

    @BeforeEach
    void init() {

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

    @Test
    void statement() {
        String expectedResult = "청구 내역 (고객명: BigCo\n" +
                " Hamlet: $650.00 (55석)\n" +
                " As You Like It: $580.00 (35석)\n" +
                " Othello: $500.00 (40석)\n" +
                "총액: $1,730.00\n" +
                "적립 포인트: 47점\n";
        String result = chap1.statement(invoices[0], plays);
        assertThat(result).isEqualTo(expectedResult);
    }
}