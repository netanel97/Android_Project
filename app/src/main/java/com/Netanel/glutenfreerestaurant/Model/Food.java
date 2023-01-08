package com.Netanel.glutenfreerestaurant.Model;

public class Food {
    private String name;
    private String description;
    private String image;
    private String price;
    private String key;

    public Food(){}


    public Food(String name, String description, String image, String price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;

    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }


    public String getPrice() {
        return price;
    }


}
