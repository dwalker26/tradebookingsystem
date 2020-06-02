package com.cfbenchmarks.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Buy {
    private LinkedList<Order> buyList = new LinkedList<>();
    private Order buy;

    public Buy(Order buy) {
        this.buy = buy;
        this.buyList.add(buy);
        this.buyList.sort(new BuyComparator());
    }

    public Buy() {
    }

    public void setBuy(Order buy) {
        this.buy = buy;
        this.buyList.add(buy);
    }


    public LinkedList<Order> getBuyList() {
        return buyList;
    }

    public LinkedList<Order> sortList(LinkedList<Order> sortedList){
        for(int x = 0; x < 100; x++){
            sortedList.sort(new BuyComparator());
        }
        sortedList.sort(new BuyComparator());
        return sortedList;
    }

    public void setBuyList(LinkedList<Order> buyList) {
        this.buyList = sortList(buyList);
    }

}

