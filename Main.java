package com.cfbenchmarks.interview;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        OrderBookManagerImpl implementation = new OrderBookManagerImpl();
       // implementation.addOrder(null);
        implementation.addOrder(new Order("10AB71D", "Stock", Side.BUY, 93, 500));
        implementation.addOrder(new Order("16AF71C", "Derivative", Side.BUY, 26, 40));
        implementation.addOrder(new Order("23KB71C", "Currency Swap", Side.BUY, 13, 10000));
        implementation.addOrder(new Order("10ZN71C", "IRS", Side.SELL, 45, 50000));
        implementation.addOrder(new Order("78AB71C", "Stock", Side.BUY, 93, 1000));
        implementation.addOrder(new Order("10QP71C", "IRS", Side.SELL, 45, 10000));
        implementation.addOrder(new Order("27AB89C", "Stock", Side.BUY, 100, 300));
        implementation.addOrder(new Order("98AB52Z", "Stock", Side.SELL, 23, 500));
        implementation.addOrder(new Order("10UJ09C", "IRS", Side.SELL, 21, 10000));
        implementation.addOrder(new Order("99AB92F", "Stock", Side.BUY, 93, 1000));
        implementation.addOrder(new Order("19QO88K", "IRS", Side.SELL, 45, 10500));
        implementation.addOrder(new Order("88AV87Z", "Stock", Side.BUY, 56, 300));
        implementation.addOrder(new Order("43AW82Z", "Stock", Side.SELL, 3, 500));
        implementation.addOrder(new Order("10SQ75C", "IRS", Side.SELL, 88, 10000));


        //System.out.println(implementation.getOrderBooks().size());
       // System.out.println(implementation.getBestPrice("IRS", Side.SELL));
        //System.out.println(implementation.getTotalQuantityAtLevel("Stock", Side.BUY, 93));

        List<Order> orders = implementation.getOrdersAtLevel("IRS", Side.SELL, 45);

        for(int i = 0; i < orders.size(); i++){
            System.out.println(orders.get(i));
        }

/*        implementation.modifyOrder("10AB71D",600);
        implementation.modifyOrder("99AB92F",0);*/


 //       System.out.println(implementation.getOrderNumAtLevel(null,Side.BUY, 23));

/*        for(int i = 0; i < implementation.getOrderBooks().size(); i++){
            if(implementation.getOrderBooks().get(i).getInstrumentName().equals("Stock")){
                for(int j = 0; j < implementation.getOrderBooks().get(i).getOrders().getKey().getBuyList().size(); j++){
                    System.out.println(implementation.getOrderBooks().get(i).getOrders().getKey().getBuyList().get(j).getOrderId() + "----"
                            + implementation.getOrderBooks().get(i).getOrders().getKey().getBuyList().get(j).getPrice());
                }
            }
        }*/

/*        for(int i = 0; i < implementation.getOrderBooks().size(); i++){
            if(implementation.getOrderBooks().get(i).getInstrumentName().equals("IRS")){
                for(int j = 0; j < implementation.getOrderBooks().get(i).getOrders().getValue().getSellList().size(); j++){
                    System.out.println(implementation.getOrderBooks().get(i).getOrders().getValue().getSellList().get(j).getOrderId() + "----"
                            + implementation.getOrderBooks().get(i).getOrders().getValue().getSellList().get(j).getPrice());
                }
            }
        }*/

/*        for(OrderBook orderBook : implementation.getOrderBooks()){
            if()
        }*/

    }
}
