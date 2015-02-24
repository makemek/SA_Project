package com.makemek.component.movie;

import java.io.Serializable;

/**
 * Created by Apipol on 21/02/15.
 */
public class Movie implements Serializable {
    String name;
    int price;
    int id;
    int quantity;

    public int sell() {
        quantity--;
        return stock();
    }

    public int stock() {
        return quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
