package com.Netanel.glutenfreerestaurant.Model;

public class Category {
    private String name;
    private String image;
    private String key;

    public Category(){}

    public Category(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getKey() {
        return key;
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

    public String getImage() {
        return image;
    }
    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
