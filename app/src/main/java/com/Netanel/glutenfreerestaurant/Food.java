package com.Netanel.glutenfreerestaurant;

public class Food {
    private String name;
    private String description;
    private String image;
    private String price;

    public Food(){}


    public Food(String name, String description, String image, String price, String menuId) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;

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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
