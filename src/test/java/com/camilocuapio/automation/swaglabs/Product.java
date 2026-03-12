package com.camilocuapio.automation.swaglabs;

import java.util.Objects;

public class Product {
// product variable
    private String name;
    private String price;
    private String description;
//builder
    public Product() {
    }

    public Product(String name, String price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
//Getters
    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }





//Methods to validate that Product objects are equal
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        return Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(description, product.description);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, price, description);
    }
}

