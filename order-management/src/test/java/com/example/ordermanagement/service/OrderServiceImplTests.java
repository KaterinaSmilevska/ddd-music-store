package com.example.ordermanagement.service;

import com.example.ordermanagement.domain.exceptions.OrderIdNotExistException;
import com.example.ordermanagement.domain.model.Order;
import com.example.ordermanagement.domain.model.OrderId;
import com.example.ordermanagement.domain.valueobjects.MusicalInstrument;
import com.example.ordermanagement.domain.valueobjects.ProductId;
import com.example.ordermanagement.service.forms.OrderForm;
import com.example.ordermanagement.service.forms.OrderItemForm;
import com.example.ordermanagement.xport.client.ProductClient;
import com.example.sharedkernel.domain.financial.Currency;
import com.example.sharedkernel.domain.financial.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class OrderServiceImplTests {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductClient productClient;


    private static MusicalInstrument newProduct(String name, Money price) {
        MusicalInstrument p = new MusicalInstrument(ProductId.randomId(ProductId.class),
                name, price, 0);
        return p;
    }

    @Test
    public void testPlaceOrder() {

        OrderItemForm oi1 = new OrderItemForm();
        oi1.setMusicalInstrument(newProduct("Electric guitar JS Series",Money.valueOf(Currency.USD,400)));
        oi1.setQuantity(1);

        OrderItemForm oi2 = new OrderItemForm();
        oi2.setMusicalInstrument(newProduct("Drums Standard Set",Money.valueOf(Currency.USD,500)));
        oi2.setQuantity(2);

        OrderForm orderForm = new OrderForm();
        orderForm.setCurrency(Currency.USD);
        orderForm.setItems(Arrays.asList(oi1,oi2));

        OrderId newOrderId = orderService.placeOrder(orderForm);
        Order newOrder = orderService.findById(newOrderId).orElseThrow(OrderIdNotExistException::new);
        Assertions.assertEquals(newOrder.total().getAmount(),Money.valueOf(Currency.USD,1400).getAmount());

    }

    @Test
    public void testPlaceOrderWithRealData() {
        List<MusicalInstrument> productList = productClient.findAll();

        MusicalInstrument p1 = productList.get(0);
        MusicalInstrument p2 = productList.get(1);

        OrderItemForm oi1 = new OrderItemForm();
        oi1.setMusicalInstrument(p1);
        oi1.setQuantity(1);

        OrderItemForm oi2 = new OrderItemForm();
        oi2.setMusicalInstrument(p2);
        oi2.setQuantity(2);

        OrderForm orderForm = new OrderForm();
        orderForm.setCurrency(Currency.USD);
        orderForm.setItems(Arrays.asList(oi1,oi2));

        OrderId newOrderId = orderService.placeOrder(orderForm);
        Order newOrder = orderService.findById(newOrderId).orElseThrow(OrderIdNotExistException::new);

        Money outMoney = p1.getInstrumentPrice().multiply(oi1.getQuantity()).add(p2.getInstrumentPrice().multiply(oi2.getQuantity()));
        Assertions.assertEquals(newOrder.total().getAmount(),outMoney.getAmount());
    }
}
