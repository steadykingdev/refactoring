package org.example;

import org.example.domain.Invoice;
import org.example.domain.Performance;
import org.example.domain.Play;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Chap1 {

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

        System.out.println(statement(invoices[0], plays));
    }

    public static String statement(Invoice invoice, Map<String, Play> plays) {
        int totalAmount = 0;
        int volumeCredits = 0;
        String result = "청구 내역 (고객명: " + invoice.getCustomer() + "\n";

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
        for (Performance perf : invoice.getPerformances()) {
            Play play = plays.get(perf.getPlayID());
            int thisAmount = 0;

            switch (play.getType()) {
                case "tragedy": // 비극
                    thisAmount = 40000;
                    if (perf.getAudience() > 30) {
                        thisAmount += 1000 * (perf.getAudience() - 30);
                    }
                    break;
                case "comedy": // 희극
                    thisAmount = 30000;
                    if (perf.getAudience() > 20) {
                        thisAmount += 10000 + 500 * (perf.getAudience() - 20);
                    }
                    thisAmount += 300 * perf.getAudience();
                    break;
                default:
                    throw new Error("알 수 없는 장르 : " + play.getType());
            }
            // 포인트 적립
            volumeCredits += Math.max(perf.getAudience() - 30, 0);
            // 희극 관객 5명마다 추가 포인트 제공
            if (play.getType().equals("comedy")) {
                volumeCredits += Math.floor(perf.getAudience() / 5);
            }

            // 청구 내역을 출력한다.
            result += " " + play.getName() + ": " + currencyFormatter.format(thisAmount / 100) + " (" + perf.getAudience() + "석)\n";
            totalAmount += thisAmount;
        }
        result += "총액: " + currencyFormatter.format(totalAmount / 100) + "\n";
        result += "적립 포인트: " + volumeCredits + "점\n";
        return result;
    }
}
