package org.example.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.example.datac.AllData;
import org.example.datac.CartProduct;

public class CartScene {
    private Label totalLabel;
    private VBox pane;
    private VBox mainLayout ;
    private ScrollPane scrollPane;
    private HBox footerBox;
    public void onCartChanged() {
        totalLabel.setText("Total Price $" + AllData.getTotalPrice());

    }

    public void onCartDeleted(CartProductCard pc) {
        pane.getChildren().remove(pc);
        if(AllData.cartProducts.isEmpty()){
            Label noProductsLabel = new Label("No products found");
            mainLayout.getChildren().removeAll(scrollPane, footerBox);
            mainLayout.getChildren().add(noProductsLabel);
            mainLayout.setAlignment(Pos.CENTER);

        }
    }

    public Scene buildScene(Stage stage) {
        mainLayout = new VBox(20);
        mainLayout.setAlignment(Pos.TOP_CENTER);

        Button backToProductsButton = new Button("Back to products");
        backToProductsButton.setOnAction(e -> stage.setScene(new MainScene().buildScene(stage)));

        VBox topBox = new VBox(10);
        topBox.setAlignment(Pos.CENTER);
        topBox.getChildren().add(backToProductsButton);
        mainLayout.getChildren().add(topBox);

        if (AllData.cartProducts.isEmpty()){
            Label noProductsLabel = new Label("No products found");
            mainLayout.getChildren().add(noProductsLabel);
            mainLayout.setAlignment(Pos.CENTER);
        } else { // products found in cart
            pane = new VBox();
            pane.setPadding(new Insets(10, 10, 0, 10));


            for (CartProduct cp : AllData.cartProducts) {
                CartProductCard pc = new CartProductCard(cp, this);
                pc.setMaxWidth(Double.MAX_VALUE);
                pc.setStyle("-fx-border-color: #000; -fx-border-width: 1px;");

                pane.getChildren().add(pc);
                VBox.setMargin(pc, new Insets(0, 0, 10, 0));
            }


            scrollPane = new ScrollPane(pane);
            scrollPane.setFitToWidth(true);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            VBox.setVgrow(scrollPane, javafx.scene.layout.Priority.ALWAYS);

            // footer
            Button checkoutButton = new Button("Checkout");
            checkoutButton.setOnAction(e -> {
                for (CartProduct cp : AllData.cartProducts) {
                    cp.getProduct().setStock(cp.getProduct().getStock()- cp.getQuantity());

                }
                AllData.cartProducts.clear();
                stage.setScene(new MainScene().buildScene(stage));
            });
            totalLabel = new Label("Total Price $" + AllData.getTotalPrice());
            totalLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20));
            HBox.setMargin(totalLabel, new Insets(10, 50, 10, 10));

            footerBox = new HBox(10);
            footerBox.setAlignment(Pos.CENTER);
            footerBox.getChildren().addAll(totalLabel, checkoutButton);
            footerBox.setPadding(new Insets(10, 10, 10, 10));
            mainLayout.getChildren().addAll(scrollPane, footerBox);
        }

        return new Scene(mainLayout, 770, 600);
    }
}
