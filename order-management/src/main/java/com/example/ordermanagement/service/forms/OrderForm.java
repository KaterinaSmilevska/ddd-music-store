package com.example.ordermanagement.service.forms;

import com.example.sharedkernel.domain.financial.Currency;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderForm {

    @NotNull
    private Currency currency;

    @Valid
    @NotEmpty
    private List<OrderItemForm> items = new ArrayList<>();
}
