package org.example.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import org.example.datac.*;

public class ProductCard extends VBox {

    private int quantity = 0;
    Label quantityLabel;


    public ProductCard(Product product) {
        setSpacing(10);
        setAlignment(Pos.CENTER);

        setStyle("-fx-border-color: gray; -fx-border-width: 2px; -fx-background-color: white;");
        setPadding(new javafx.geometry.Insets(10));

        ImageView imageView = new ImageView();
        Image image = new Image(product.getImageFile().toURI().toString(), 150, 150, true, true);
        imageView.setImage(image);

        Label nameLabel = new Label(product.getName());

        Label priceLabel = new Label("Price: $" + product.getPrice());

        Label stockLabel = new Label("Stock: " + product.getStock());

        HBox quantityBox = new HBox(10);
        quantityBox.setAlignment(Pos.CENTER);

        Button minusButton = new Button("-");
        quantityLabel = new Label(String.valueOf(quantity));
        Button plusButton = new Button("+");



        minusButton.setOnAction(e -> {
            if (quantity > 0) {
                quantity--;
                quantityLabel.setText(String.valueOf(quantity));
            }
        });

        plusButton.setOnAction(e -> {
            int previousCart = 0;
            CartProduct found = AllData.findCartProductFromProduct(product);
            if (found != null) {
                previousCart = found.getQuantity();
            }

            if (quantity+previousCart < product.getStock()) {
                quantity++;
                quantityLabel.setText(String.valueOf(quantity));
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("You don't have enough stock to add this product in the cart");
                alert.showAndWait();
            }
        });

        quantityBox.getChildren().addAll(minusButton, quantityLabel, plusButton);

        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.setOnAction(e -> addToCart(product));

        if(product.getStock() <= 0){
            minusButton.setDisable(true);
            plusButton.setDisable(true);
            addToCartButton.setDisable(true);
        }

        getChildren().addAll(imageView, nameLabel, priceLabel, stockLabel, quantityBox, addToCartButton);
    }

    private void addToCart(Product product) {

        if (quantity > 0) {
                CartProduct found = AllData.findCartProductFromProduct(product);

                if(found != null) { //exists
                    found.setQuantity(found.getQuantity() + quantity);
                } else { // not found
                    AllData.cartProducts.add(new CartProduct(product, quantity));
                }



                quantity = 0;
                this.quantityLabel.setText(String.valueOf(quantity));


                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setHeaderText(null);
                alert.setContentText("Product added to your cart successfully!");
                alert.showAndWait();

            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Oops");
                alert.setHeaderText(null);
                alert.setContentText("You can't add more than 1 product to your cart!");
                alert.showAndWait();
            }

    }
}
