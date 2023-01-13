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
    public String totalPrice(){
        int totalPrice = 0;
        for (int i = 0; i < currentOrder.getAllFoods().size(); i++) {
            totalPrice += Integer.parseInt(currentOrder.getAllFoods().get(i).getPrice());

        }
        return Integer.toString(totalPrice);
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
