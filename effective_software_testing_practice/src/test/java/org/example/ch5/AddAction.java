package org.example.ch5;


import net.jqwik.api.stateful.Action;
import org.example.ch4.Basket;
import org.example.ch4.Product;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AddAction implements Action<Basket> {
    private final Product product;
    private final int qty;

    // 입력받은 값은 jqwik에 의해 무작위로 생성된 값
    public AddAction(Product product, int qty){
        this.product = product;
        this.qty = qty;
    }

    @Override
    public Basket run(Basket basket){
        BigDecimal currentValue = basket.getTotalValue();

        basket.add(product, qty);

        BigDecimal newProductValue = product.getPrice().multiply(BigDecimal.valueOf(qty));
        BigDecimal newValue = currentValue.add(newProductValue);

        assertThat(basket.getTotalValue())
                .isEqualByComparingTo(newValue);

        return basket;
    }
}
