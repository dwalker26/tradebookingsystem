package com.cfbenchmarks.interview;

import java.util.Comparator;

public class BuyComparator implements Comparator<Order> {
    @Override
    public int compare(Order o2, Order o1) {
        if (o1.getcQuantity() == o1.getQuantity()) {
            if(o1.getPrice() == o2.getPrice()){
                return -1;
            } else if(o1.getPrice() > o2.getPrice()){
                return 1;
            }
            return -1;
        } else if (o1.getcQuantity() > o1.getQuantity()) {
            return -1;
        } else if (o1.getcQuantity() > 0) {
            return 1;
        }
        if(o1.getPrice() == o2.getPrice()){
            return 0;
        } else if(o1.getPrice() < o2.getPrice()){
            return -1;
        } else if(o1.getPrice() > o2.getPrice()){
            return 1;
        }
        return 0;
    }
}
