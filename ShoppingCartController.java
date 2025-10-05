import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/shop")
public class ShoppingCartController {

    // Store carts in memory (in real app, this would be a database)
    private Map<String, Cart> carts = new HashMap<>();

    // Endpoint to add item to cart
    @PostMapping("/addItem")
    public String addItem(@RequestParam String cartId,
                                @RequestParam String itemName,
                                @RequestParam BigDecimal price,
                                @RequestParam int quantity) {

        Cart cart = carts.computeIfAbsent(cartId, id -> new Cart());
        cart.addItem(new CartItem(itemName, price, quantity));

        return "Item added to cart. Total: " + cart.getTotal();
    }

    // Endpoint to get cart total
    @GetMapping("/cartTotal")
    public String getTotal(@RequestParam String cartId) {

    
        Cart cart = carts.get(cartId);
        
        if (cart == null) {
            return "Cart not found.";
        }
        return "Cart total: " + cart.getTotal();

    }

}


// Value object that represents an item

class CartItem {
    
    private String name;
    private BigDecimal price;
    private int quantity;


    public CartItem(String name, BigDecimal price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }

    public BigDecimal getTotalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

}


// model class that represents a shopping cart
class Cart {

    private List<CartItem> items = new ArrayList<>(); 

    public void addItem(CartItem newItem) {
        // Check if item already exists, if so, increase quantity
        for (CartItem existingItem : items) {
            if (existingItem.getName().equals(newItem.getName()) &&
                existingItem.getPrice().equals(newItem.getPrice())) {
                existingItem.increaseQuantity(newItem.getQuantity());
                return;
            }
        }
        items.add(newItem);
    }

    public BigDecimal getTotal() {
        // Calculate total of all items
        return items.stream()
            .map(CartItem::getTotalPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
