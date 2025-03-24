package org.example.ch5;

import net.jqwik.api.*;
import net.jqwik.api.stateful.ActionSequence;
import org.example.ch4.Basket;
import org.example.ch4.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BasketTest {

    @Property
    void sequenceOfAddsAndRemoves(
            @ForAll("addsAndRemoves")
            ActionSequence<Basket> actions
    ){
        actions.run(new Basket());
    }

    @Provide
    Arbitrary<ActionSequence<Basket>> addsAndRemoves(){
        return Arbitraries.sequences(Arbitraries.oneOf(
                addAction(),
                removeAction()
        ));
    }

    private Arbitrary<RemoveAction> removeAction(){
        return Arbitraries.of(new RemoveAction());
    }

    private Arbitrary<AddAction> addAction(){
        // ~.oneOf : 주어진 집합에서 무작위로 고름
       Arbitrary<Product> products = Arbitraries.oneOf(
               randomProducts
                       .stream()
                       .map(product -> Arbitraries.of(product))
                       .toList()
       );

       Arbitrary<Integer> qtys = Arbitraries.integers().between(1,100);

       return Combinators
               .combine(products, qtys)
               .as((product, qty) -> new AddAction(product,qty));
    }

    static List<Product> randomProducts = new ArrayList<>(){{
        add(new Product("TV", new BigDecimal("100")));
        add(new Product("PlayStation", new BigDecimal("150.3")));
        add(new Product("Refrigerator", new BigDecimal("180.27")));
        add(new Product("Soda", new BigDecimal("2.69")));
    }};
}
