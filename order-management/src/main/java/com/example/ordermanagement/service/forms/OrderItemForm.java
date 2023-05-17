package com.example.ordermanagement.service.forms;

import com.example.ordermanagement.domain.valueobjects.MusicalInstrument;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class OrderItemForm {

    @NotNull
    private MusicalInstrument musicalInstrument;

    @Min(1)
    private int quantity = 1;
}
