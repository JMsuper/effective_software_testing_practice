package org.example.ch5;

import net.jqwik.api.stateful.Action;
import org.example.ch4.Basket;
import org.example.ch4.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RemoveAction implements Action<Basket> {

    @Override
    public Basket run(Basket basket){
        BigDecimal currentValue = basket.getTotalValue();
        Set<Product> productsInBasket = basket.products();

        if(productsInBasket.isEmpty()){
            return basket;
        }

        Product randomProduct = pickRandom(productsInBasket);
        double currentProductQty = basket.quantityOf(randomProduct);
        basket.remove(randomProduct);

        BigDecimal basketValueWithoutRandomProduct = currentValue
                .subtract(randomProduct.getPrice().multiply(BigDecimal.valueOf(currentProductQty)));

        assertThat(basket.getTotalValue()).isEqualByComparingTo(basketValueWithoutRandomProduct);

        return basket;
    }

    private Product pickRandom(Set<Product> productSet){
        List<Product> productList = productSet.stream().toList();
        return productList.get((int)Math.floor(Math.random() * productList.size()));
    }
}
