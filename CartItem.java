import java.math.BigDecimal;

public class CartItem {
    private String name;
    private BigDecimal price; // BigDecimal for currency, better precision
    private int quantity;

    public CartItem(String name, BigDecimal price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters
    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTotalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    // Setters
    public void increaseQuantity(int qty) {
        this.quantity += qty;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}