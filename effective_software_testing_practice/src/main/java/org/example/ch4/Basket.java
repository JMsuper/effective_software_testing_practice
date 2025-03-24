package org.example.ch4;

import java.math.BigDecimal;
import java.util.*;

public class Basket {
    private BigDecimal totalValue = BigDecimal.ZERO;
    private Map<Product, Integer> basket = new HashMap<>();

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public Map<Product, Integer> getBasket() {
        return basket;
    }

    public int quantityOf(Product product){
        assert basket.containsKey(product);
        return basket.get(product);
    }

    public Set<Product> products(){
        return Collections.unmodifiableSet(basket.keySet());
    }

    public void add(Product product, int qtyToAdd){
        assert product != null : "Product cannot be null";
        assert qtyToAdd > 0 : "Quantity to add must be greater than 0";

        BigDecimal oldValue = totalValue;

        // 제품 추가
        int existingQuantity = basket.getOrDefault(product, 0);
        int newQuantity = existingQuantity + qtyToAdd;
        basket.put(product, newQuantity);

        // 합계 갱신
        BigDecimal valueAlreadyInTheCart = product.getPrice().multiply(BigDecimal.valueOf(existingQuantity));
        BigDecimal newFinalValueForTheProduct = product.getPrice().multiply(BigDecimal.valueOf(newQuantity));

        totalValue = totalValue.subtract(valueAlreadyInTheCart).add(newFinalValueForTheProduct);

        assert basket.containsKey(product) : "Product was not inserted into the basket";

        assert totalValue.compareTo(oldValue) == 1 : "Total value should be greater than old value";

        assert invarient() : "Invarient is not satisfied";
    }

    public void remove(Product product){
        assert product != null : "Product cannot be null";
        assert basket.containsKey(product) : "Product was not inserted into the basket";

        BigDecimal oldValue = totalValue;
        int qty = basket.get(product);

        // 합계 갱신
        BigDecimal productPrice = product.getPrice();
        BigDecimal productTimesQuantity = productPrice.multiply(BigDecimal.valueOf(qty));
        totalValue = totalValue.subtract(productTimesQuantity);

        // 제품 제거
        basket.remove(product);

        assert !basket.containsKey(product) : "Product was not removed from the basket";
        assert totalValue.compareTo(oldValue) == -1 : "Total value should be less than old value";

        assert invarient() : "Invarient is not satisfied";
    }

    private boolean invarient(){
        return totalValue.compareTo(BigDecimal.ZERO) >= 0;
    }
}


