# DZRT Market

DZRT Market is a JavaFX-based application designed to simulate an e-commerce platform for nicotine pouch products. This application allows users to browse products, add them to their shopping cart, and calculate the total cost of their selected items. It serves as an ideal foundation for beginners exploring JavaFX and e-commerce concepts.

## Features

- **Product Catalog**: Display a list of products with details such as name, price, stock, and an image.
- **Shopping Cart**: Add products to the cart, adjust quantities, and calculate the total price.
- **Interactive UI**: Easy-to-use graphical interface for navigating products and managing the cart.
- **Data Management**: Dynamically handles product data and updates stock levels.

## Project Structure

The project is organized into the following key files:

### 1. **Main.java**
- The entry point of the application.
- Loads initial data and sets up the main scene.

### 2. **CartProduct.java**
- Represents an item in the shopping cart.
- Tracks the product and its quantity, with methods to calculate the total price.

### 3. **AllData.java**
- Centralized data storage for products and cart items.
- Provides utility methods to load initial data, find cart products, and calculate total price.

### 4. **Product.java**
- Represents a product in the catalog.
- Includes attributes like ID, name, price, stock, and image path.
- Provides methods to manage stock levels.

## Initial Data

The application initializes with a predefined set of products:
- **Icy Rush**: Price: $50.0, Stock: 10
- **Highland Berries**: Price: $45.0, Stock: 100
- **Seaside Frost**: Price: $37.50, Stock: 100
- **Purple Mist**: Price: $25.0, Stock: 100
- **Edgy Mint**: Price: $15.0, Stock: 100

Images for these products are stored in the `resources/products` directory.
