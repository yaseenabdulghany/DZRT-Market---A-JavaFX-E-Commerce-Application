package org.example.datac;

import java.io.File;
import java.util.ArrayList;

public class AllData {
    public static ArrayList<Product> products = new ArrayList<Product>();
    public static ArrayList<CartProduct> cartProducts = new ArrayList<CartProduct>();

    public static void loadInitData() {
        products.add(new Product(1, "Icy Rush", 50.0f, 10, new File("./src/main/resources/products/1.png")));
        products.add(new Product(2, "Highland Berries", 45.0f, 100, new File("./src/main/resources/products/2.png")));
        products.add(new Product(3, "Seaside Frost", 37.50f, 100, new File("./src/main/resources/products/3.png")));
        products.add(new Product(4, "Purple Mist", 25.0f, 100, new File("./src/main/resources/products/4.png")));
        products.add(new Product(5, "Edgy Mint", 15.0f, 100, new File("./src/main/resources/products/5.png")));
    }


    public static CartProduct findCartProductFromProduct(Product product) {
        CartProduct found = null;
        for(CartProduct cp: AllData.cartProducts) {
            if(cp.getProduct().getId() == product.getId()) {
                found = cp;
            }
        }
        return found;
    }

    public static float getTotalPrice() {
        float total = 0.0f;
        for(CartProduct cp: AllData.cartProducts) {
            total += cp.getTotalPrice();

        }
        return total;
    }

}
