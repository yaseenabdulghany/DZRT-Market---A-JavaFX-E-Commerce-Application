package org.example.gui;
import javafx.geometry.Insets;
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

public class CartProductCard extends HBox {

    private Label quantityLabel;
    private Label totalPrice;
    private int previousCart = 0;
    private VBox labelsBox ;

    public CartProductCard(CartProduct cp, CartScene parent) {
        Product product = cp.getProduct();
        previousCart = cp.getQuantity();

        setSpacing(20);
        setAlignment(Pos.CENTER);
        setStyle("-fx-border-color: gray;");
        setPadding(new javafx.geometry.Insets(10));

        ImageView imageView = new ImageView();
        Image image = new Image(product.getImageFile().toURI().toString(), 160, 160, true, true);
        imageView.setImage(image);

        Label nameLabel = new Label(product.getName());
        Label priceLabel = new Label("Price: $" + product.getPrice());
        totalPrice = new Label("Product Total Price: $" + cp.getTotalPrice());

        labelsBox = new VBox(10);
        labelsBox.setAlignment(Pos.CENTER);
        labelsBox.getChildren().addAll(nameLabel, priceLabel, totalPrice);

        HBox quantityBox = new HBox(10);
        quantityBox.setAlignment(Pos.CENTER);
        Button minusButton = new Button("-");
        quantityLabel = new Label(String.valueOf(previousCart));
        Button plusButton = new Button("+");
        quantityBox.getChildren().addAll(minusButton, quantityLabel, plusButton);

        minusButton.setOnAction(e -> {
            previousCart = cp.getQuantity();
            if (previousCart > 0) {
                previousCart--;


                cp.setQuantity(previousCart);
                labelsBox.getChildren().removeAll(nameLabel, priceLabel, totalPrice);
                totalPrice.setText("Product Total Price: $" + cp.getTotalPrice());
                labelsBox.getChildren().addAll(nameLabel, priceLabel, totalPrice);
                quantityLabel.setText(String.valueOf(previousCart));
                try{
                    parent.onCartChanged();
                } catch (Exception exp) {
                    System.out.println(exp.getMessage());
                }

            }
        });

        plusButton.setOnAction(e -> {
            previousCart = cp.getQuantity();
            if (previousCart < product.getStock()) {
                previousCart++;

                cp.setQuantity(previousCart);
                labelsBox.getChildren().removeAll(nameLabel, priceLabel, totalPrice);
                totalPrice.setText("Product Total Price: $" + cp.getTotalPrice());
                labelsBox.getChildren().addAll(nameLabel, priceLabel, totalPrice);
                quantityLabel.setText(String.valueOf(previousCart));
                try{
                    parent.onCartChanged();
                } catch (Exception exp) {
                    System.out.println(exp.getMessage());
                }
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("You don't have enough stock to add this product to the cart.");
                alert.showAndWait();
            }
        });

        Button removeButton = new Button("Remove");
        removeButton.setOnAction(e -> {
            AllData.cartProducts.remove(cp);
            try{
                parent.onCartChanged();
                parent.onCartDeleted(this);
            } catch (Exception exp) {
                System.out.println(exp.getMessage());
            }

        });

        if (product.getStock() <= 0) {
            minusButton.setDisable(true);
            plusButton.setDisable(true);
            removeButton.setDisable(true);
        }

        getChildren().addAll(imageView, labelsBox, quantityBox, removeButton);

        setSpacing(80);
    }
}
