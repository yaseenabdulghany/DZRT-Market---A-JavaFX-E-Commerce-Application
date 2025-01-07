package org.example.datac;


import java.io.File;

public class Product {
    private int id;
    private String name;
    private float price;
    private File imagePath;
    private int stock;

    public Product(int id, String name, float price, int stock, File imagePath) {
        this.id = id;
        this.name = name;
        setPrice(price);
        setStock(stock);
        setImageFile(imagePath);
    }


    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        if(price >= 0)
            this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if(stock >= 0)
            this.stock = stock;
    }

    public void addToStock(int stock) {
        if (stock > 0)
            this.stock += stock;

    }

    public void removeFromStock(int stock) {
        if (stock > 0 && this.stock >= stock)
            this.stock -= stock;
    }

    public File getImageFile() {
        return imagePath;
    }

    public void setImageFile(File imagePath) {
        this.imagePath = imagePath;
    }
}
