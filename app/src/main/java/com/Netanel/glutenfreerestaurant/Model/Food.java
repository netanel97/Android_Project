package com.Netanel.glutenfreerestaurant.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Parcelable {
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

    private Food(Parcel in) {
        name = in.readString();
        description = in.readString();
        image = in.readString();
        price = in.readString();
        key = in.readString();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

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


    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price='" + price + '\'' +
                ", key='" + key + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(image);
        dest.writeString(price);
        dest.writeString(key);
    }
}
