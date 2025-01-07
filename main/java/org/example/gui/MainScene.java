package org.example.gui;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import org.example.datac.AllData;
import org.example.datac.Product;
import javafx.stage.Stage;

public class MainScene {
    public Scene buildScene(Stage stage) {
        VBox mainLayout = new VBox(20);
        mainLayout.setAlignment(Pos.TOP_CENTER);

        Button goToCartButton = new Button("Go to Cart");
        goToCartButton.setOnAction(e -> {
            stage.setScene(new CartScene().buildScene(stage));
        });

        VBox topBox = new VBox(10);
        topBox.setAlignment(Pos.CENTER);
        topBox.getChildren().add(goToCartButton);

        FlowPane pane = new FlowPane();
        pane.setPadding(new Insets(10));
        pane.setVgap(10);
        pane.setHgap(10);

        for (Product product : AllData.products) {
            ProductCard productCard = new ProductCard(product);
            productCard.setStyle("-fx-border-color: #000; -fx-border-width: 1px; -fx-padding: 10px;");
            pane.getChildren().add(productCard);
        }

        ScrollPane scrollPane = new ScrollPane(pane);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        mainLayout.getChildren().addAll(topBox, scrollPane);

        return new Scene(mainLayout, 770, 600);
    }
}
