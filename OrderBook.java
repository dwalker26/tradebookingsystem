package com.cfbenchmarks.interview;

import javafx.util.Pair;

import java.util.List;

public class OrderBook {

    private Buy buy;
    private Sell sell;
    private String instrumentName;

    private Pair<Buy, Sell> orders;

    public void setBuy(Order order) {
        this.orders.getKey().setBuy(order);
    }

    public void setSell(Order order) {
        this.orders.getValue().setSell(order);
    }

    public OrderBook(Sell sell, Buy buy, String instrumentName) {
        this.sell = sell;
        this.buy = buy;
        this.orders = new Pair<>(buy, sell);
        this.instrumentName = instrumentName;

    }

    public Buy getBuy() {
        return buy;
    }

    public Sell getSell() {
        return sell;
    }

    public Pair<Buy, Sell> getOrders() {
        return orders;
    }


    public String getInstrumentName() {
        return instrumentName;
    }
}
