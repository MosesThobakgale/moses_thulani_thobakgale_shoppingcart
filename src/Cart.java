package src;
import java.math.BigDecimal;
import java.util.*;

// Cart class to hold multiple CartItems and calculate total
public class Cart{

    private List<CartItem> items = new ArrayList<>();

    public void addItem(CartItem newItem) {

        // Check if item already exists (same name and price), if so, increase quantity, if not, add new item
        for (CartItem item : items) {
            if (item.getName().equals(newItem.getName()) && item.getPrice().equals(newItem.getPrice())) {
                item.increaseQuantity(newItem.getQuantity());
                return;
            }
        }
        items.add(newItem);
    }

    public BigDecimal getTotal() {

        // Calculate total using BigDecimal for precision
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem item : items) {
            total = total.add(item.getTotalPrice());
        }
        
        return total;
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }


}