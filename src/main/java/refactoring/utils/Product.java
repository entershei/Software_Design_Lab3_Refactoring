package refactoring.utils;

public class Product {
    private final String name;
    private final Long price;

    public Product(String name, String price) {
        this.name = name;
        this.price = Long.parseLong(price);
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }
}
