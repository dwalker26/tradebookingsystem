package com.cfbenchmarks.interview;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;

public class Sell {
    private LinkedList<Order> sellList = new LinkedList();
    private Order sell;

    public Sell(Order sell) {
        this.sell = sell;
        this.sellList.add(sell);
        this.sellList.sort(new SellComparator());
    }

    public Sell() {
    }

    public void setSell(Order sell) {
        this.sell = sell;
        this.sellList.add(sell);
    }

    public LinkedList<Order> getSellList() {
        return sellList;
    }

    public LinkedList<Order> sortList(LinkedList<Order> sortedList){
        for(int y = 0; y < 100; y++){
            sortedList.sort(new SellComparator());
        }
        sortedList.sort(new SellComparator());
        return sortedList;
    }

    public void setSellList(LinkedList<Order> sellList) {
        this.sellList = sortList(sellList);
    }
}
