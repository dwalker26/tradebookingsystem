package com.cfbenchmarks.interview;



import java.util.*;

public class OrderBookManagerImpl implements OrderBookManager {

  private ArrayList<OrderBook> orderBooks = new ArrayList<>();

  public OrderBookManagerImpl() {
  }

  public synchronized void addOrder(Order order) {
    if(order == null){
      throw new NullPointerException("Order cannot be null");
    }

    if(orderBooks.size() == 0){ ;
      switch (order.getSide()) {
        case BUY:
          orderBooks.add(new OrderBook(new Sell(), new Buy(order), order.getInstrument()));
          break;
        case SELL:
          orderBooks.add(new OrderBook(new Sell(order), new Buy(), order.getInstrument()));
          break;
        default:
          System.out.println("Order must have a buy or sell side defined");
      }
      orderBooks.get(0).getOrders().getKey().setBuyList(orderBooks.get(0).getOrders().getKey().getBuyList());
      orderBooks.get(0).getOrders().getValue().setSellList(orderBooks.get(0).getOrders().getValue().getSellList());
      return;
    } else {
      for (int i = 0; i < orderBooks.size(); i++) {
        if (orderBooks.get(i).getInstrumentName().equals(order.getInstrument())) {
          switch (order.getSide()) {
            case BUY:
              orderBooks.get(i).setBuy(order);
              break;
            case SELL:
              orderBooks.get(i).setSell(order);
              break;
            default:
              System.out.println("Order must have a buy or sell side defined");
          }
          orderBooks.get(i).getOrders().getKey().setBuyList(orderBooks.get(i).getOrders().getKey().getBuyList());
          orderBooks.get(i).getOrders().getValue().setSellList(orderBooks.get(i).getOrders().getValue().getSellList());
          return;
        }
      }
    }     switch (order.getSide()) {
            case BUY:
              orderBooks.add(new OrderBook(new Sell(), new Buy(order), order.getInstrument()));
              //orderBooks.get(orderBooks.size() - 1).setBuy(order);
              break;
            case SELL:
              orderBooks.add(new OrderBook(new Sell(order), new Buy(), order.getInstrument()));
              //orderBooks.get(orderBooks.size() - 1).setSell(order);
              break;
            default:
              System.out.println("Order must have a buy or sell side defined");
          }
    orderBooks.get(orderBooks.size() - 1).getOrders().getKey().setBuyList(orderBooks.get(orderBooks.size() - 1).getOrders().getKey().getBuyList());
    orderBooks.get(orderBooks.size() - 1).getOrders().getValue().setSellList(orderBooks.get(orderBooks.size() - 1).getOrders().getValue().getSellList());
    return;
  }

  public synchronized boolean modifyOrder(String orderId, long newQuantity) {
    if(Long.valueOf(newQuantity) < 0 ){
      throw new IllegalArgumentException("Quantity must be non-negative");
    }
    if(orderId == null || Long.valueOf(newQuantity) == null){
      String exception;
      if(orderId == null){
        exception = "orderId cannot be null";
      } else{
        exception = "Quantity cannot be null";
      }
      throw new NullPointerException(exception);
    }

    LinkedList<Order> copyBuyList = new LinkedList<>();
    LinkedList<Order> copySellList = new LinkedList<>();

      for(int i = 0; i < orderBooks.size(); i++){
        for(int j = 0; j < orderBooks.get(i).getOrders().getKey().getBuyList().size(); j++){
          if(orderId.equals(getOrderBooks().get(i).getOrders().getKey().getBuyList().get(j).getOrderId())){
            copyBuyList = getOrderBooks().get(i).getOrders().getKey().getBuyList();
            if(newQuantity == 0){
              deleteOrder(orderId);
            }
            if(newQuantity > 0){
              copyBuyList.get(j).setQuantity(newQuantity);
              getOrderBooks().get(i).getOrders().getKey().setBuyList(copyBuyList);
            }
            return true;
          }
        }
        for(int k = 0; k < orderBooks.get(i).getOrders().getValue().getSellList().size(); k++){
          if(orderId.equals(getOrderBooks().get(i).getOrders().getValue().getSellList().get(k).getOrderId())){
            copySellList = getOrderBooks().get(i).getOrders().getValue().getSellList();
            if(newQuantity == 0){
              deleteOrder(orderId);
            }
            if(newQuantity > 0){
              copySellList.get(k).setQuantity(newQuantity);
              getOrderBooks().get(i).getOrders().getValue().setSellList(copySellList);
            }
            return true;
          }
        }
      }
      if(copyBuyList.size() == 0 || copySellList.size() == 0) {
        throw new NoSuchElementException("OrderID does not exist in OrderBook");
      }
    return false;
  }
  public synchronized boolean deleteOrder(String orderId) {
    if(orderId == null){
      throw new NullPointerException("orderId cannot be null");
    }
    LinkedList<Order> copyBuyList = new LinkedList<>();
    LinkedList<Order> copySellList = new LinkedList<>();
    for(int i = 0; i < orderBooks.size(); i++){
      for(int j = 0; j < orderBooks.get(i).getOrders().getKey().getBuyList().size(); j++){
        if(orderId.equals(getOrderBooks().get(i).getOrders().getKey().getBuyList().get(j).getOrderId())){
          copyBuyList = getOrderBooks().get(i).getOrders().getKey().getBuyList();
          copyBuyList.remove(j);
          getOrderBooks().get(i).getOrders().getKey().setBuyList(copyBuyList);
          return true;
        }
      }
      for(int k = 0; k < orderBooks.get(i).getOrders().getValue().getSellList().size(); k++){
        if(orderId.equals(getOrderBooks().get(i).getOrders().getValue().getSellList().get(k).getOrderId())){
          copySellList = getOrderBooks().get(i).getOrders().getValue().getSellList();
          copySellList.remove(k);
          getOrderBooks().get(i).getOrders().getValue().setSellList(copySellList);
          return true;
        }
      }
    }
    if(copyBuyList.size() == 0 || copySellList.size() == 0) {
      throw new NoSuchElementException("OrderID does not exist in OrderBook");
    }
    return false;
  }

  public Optional<Long> getBestPrice(String instrument, Side side) {
    if(instrument == null || side == null){
      String exception;
      if(instrument == null){
        exception = "Instrument cannot be null";
      } else{
        exception = "Side not specified";
      }
      throw new NullPointerException(exception);
    }
    for(OrderBook orderBook : orderBooks){
      if(orderBook.getInstrumentName().equals(instrument)){
        switch(side){
          case BUY:
            Optional<Long> buyBest =
                    Optional.of(orderBook.getOrders().getKey().getBuyList().get(0).getPrice());
            return buyBest;
          case SELL:
            Optional<Long> sellBest =
                    Optional.of(orderBook.getOrders().getValue().getSellList().get(0).getPrice());
            return sellBest;
          default:
            System.out.println("Side must be 'BUY' or 'SELL'");
        }
      }
    }
    return Optional.empty();
  }

  public long getOrderNumAtLevel(String instrument, Side side, long price) {
    if(instrument == null || Long.valueOf(price) == null || side == null){
      String exception;
      if(instrument == null){
        exception = "Specify instrument";
      } else if( Long.valueOf(price) == null ){
        exception = "Specify price";
      } else{
        exception = "Specify side: Buy or Sell";
      }
      throw new NullPointerException(exception);
    }
    long orderNum = 0;
    for(OrderBook orderBook : orderBooks){
      if(orderBook.getInstrumentName().equals(instrument)){
        switch(side){
          case BUY:
            for(Order order: orderBook.getOrders().getKey().getBuyList()) {
              if (order.getPrice() == price) {
                orderNum++;
              }
            }
            return orderNum;
          case SELL:
            for(Order order: orderBook.getOrders().getValue().getSellList()) {
              if (order.getPrice() == price) {
                orderNum++;
              }
            }
            return orderNum;
          default:
            System.out.println("Side must be 'BUY' or 'SELL'");
        }
      }
    }
    return 0;
  }

  public long getTotalQuantityAtLevel(String instrument, Side side, long price) throws NullPointerException {
    if(instrument == null || Long.valueOf(price) == null || side == null){
      String exception;
      if(instrument == null){
        exception = "Specify instrument";
      } else if( Long.valueOf(price) == null ){
        exception = "Specify price";
      } else{
        exception = "Specify side: Buy or Sell";
      }
      throw new NullPointerException(exception);
    }
    long totalQ = 0;
    for(OrderBook orderBook : orderBooks){
      if(orderBook.getInstrumentName().equals(instrument)){
        switch(side){
          case BUY:
            for(Order order: orderBook.getOrders().getKey().getBuyList()) {
              if (order.getPrice() == price) {
                totalQ += order.getQuantity();
              }
            } return totalQ;
          case SELL:
            for(Order order: orderBook.getOrders().getValue().getSellList()) {
              if (order.getPrice() == price) {
                totalQ += order.getQuantity();
              }
            } return totalQ;
          default:
            System.out.println("Side must be 'BUY' or 'SELL'");
        }
      }
    }
    return 0;
  }

  public long getTotalVolumeAtLevel(String instrument, Side side, long price) {
    long totalVol = 0;
    for(OrderBook orderBook : orderBooks){
      if(orderBook.getInstrumentName().equals(instrument)){
        switch(side){
          case BUY:
            for(Order order: orderBook.getOrders().getKey().getBuyList()) {
              if (order.getPrice() == price) {
                totalVol += order.getQuantity() * order.getPrice();
              }
            } return totalVol;
          case SELL:
            for(Order order: orderBook.getOrders().getValue().getSellList()) {
              if (order.getPrice() == price) {
                totalVol += order.getQuantity() * order.getPrice();
              }
            } return totalVol;
          default:
            System.out.println("Side must be 'BUY' or 'SELL'");
        }
      }
    }
    return 0;
  }

  public List<Order> getOrdersAtLevel(String instrument, Side side, long price) {
    ArrayList<Order> ordersAtLevel = new ArrayList<>();
    for(OrderBook orderBook : orderBooks){
      if(orderBook.getInstrumentName().equals(instrument)){
        switch(side){
          case BUY:
            for(Order order: orderBook.getOrders().getKey().getBuyList()) {
              if (order.getPrice() == price) {
                ordersAtLevel.add(order);
              }
            } return ordersAtLevel;
          case SELL:
            for(Order order: orderBook.getOrders().getValue().getSellList()) {
              if (order.getPrice() == price) {
                ordersAtLevel.add(order);
              }
            } return ordersAtLevel;
          default:
            System.out.println("Side must be 'BUY' or 'SELL'");
        }
      }
    }
    return ordersAtLevel;
  }

  public ArrayList<OrderBook> getOrderBooks() {
    return orderBooks;
  }
}
