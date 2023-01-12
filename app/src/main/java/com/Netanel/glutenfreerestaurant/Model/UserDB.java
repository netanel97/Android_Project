package com.Netanel.glutenfreerestaurant.Model;


import java.util.ArrayList;

public class UserDB {
    private ArrayList<Order> allOrders;
    private Order currentOrder;
    private String name;



    public UserDB() {
        this.allOrders = new ArrayList<Order>();
        this.currentOrder = new Order();
    }

    public ArrayList<Order> getAllOrders() {
        return allOrders;
    }

    public void setAllOrders(ArrayList<Order> allOrders) {
        this.allOrders = allOrders;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addOrder(Order order){
        allOrders.add(order);
    }

    @Override
    public String toString() {
        return "UserDB{" +
                "allOrders=" + allOrders +
                ", currentOrder=" + currentOrder +
                '}';
    }
}
