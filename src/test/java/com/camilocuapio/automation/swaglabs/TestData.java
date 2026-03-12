package com.camilocuapio.automation.swaglabs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestData {

//List of expected products, including name, price, and description
    public static List<Product> getBaseProducts() {


        List<Product> expectedNamePriceDescription = new ArrayList<>();

        expectedNamePriceDescription.add(new Product(
                "Sauce Labs Backpack",
                "$29.99",
                "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection."
        ));
        expectedNamePriceDescription.add(new Product(
                "Sauce Labs Bike Light",
                "$9.99",
                "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included."
        ));
        expectedNamePriceDescription.add(new Product(
                "Sauce Labs Bolt T-Shirt",
                "$15.99",
                "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt."
        ));
        expectedNamePriceDescription.add(new Product(
                "Sauce Labs Fleece Jacket",
                "$49.99",
                "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office."
        ));
        expectedNamePriceDescription.add(new Product(
                "Sauce Labs Onesie",
                "$7.99",
                "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel."
        ));
        expectedNamePriceDescription.add(new Product(
                "Test.allTheThings() T-Shirt (Red)",
                "$15.99",
                "This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton."
        ));
        return expectedNamePriceDescription;
    }


    // Sort by name
    public static List<Product> getProductsSortedByName(boolean ascending) {
        List<Product> products = new ArrayList<>(getBaseProducts());

        Comparator<Product> comparator = Comparator.comparing(Product::getName);

        if (!ascending) {
            comparator = comparator.reversed();
        }

        products.sort(comparator);
        return products;
    }

    // Sort by price
    public static List<Product> getProductsSortedByPrice(boolean ascending) {
        List<Product> products = new ArrayList<>(getBaseProducts());

        Comparator<Product> comparator =
                Comparator.comparingDouble(p -> Double.parseDouble(p.getPrice().replace("$", "")));

        if (!ascending) {
            comparator = comparator.reversed();
        }

        products.sort(comparator);

        return products;
    }
}


