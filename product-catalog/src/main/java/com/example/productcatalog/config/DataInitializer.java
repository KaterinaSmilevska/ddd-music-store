package com.example.productcatalog.config;

import com.example.productcatalog.domain.models.Category;
import com.example.productcatalog.domain.models.Manufacturer;
import com.example.productcatalog.domain.models.MusicalInstrument;
import com.example.productcatalog.domain.repository.CategoryRepository;
import com.example.productcatalog.domain.repository.ManufacturerRepository;
import com.example.productcatalog.domain.repository.MusicalInstrumentRepository;
import com.example.sharedkernel.domain.financial.Currency;
import com.example.sharedkernel.domain.financial.Money;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class DataInitializer {

    private final MusicalInstrumentRepository musicalInstrumentRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final CategoryRepository categoryRepository;

    @PostConstruct
    public void initData() {
        Manufacturer m1 = new Manufacturer("Jackson", "USA");
        Manufacturer m2 = new Manufacturer("Yamaha", "Japan");
        manufacturerRepository.saveAll(List.of(m1, m2));

        Category c1 = new Category("Electric guitars");
        Category c2 = new Category("Drums");
        categoryRepository.saveAll(List.of(c1, c2));

        MusicalInstrument p1 = MusicalInstrument.build("Electric guitar JS Series", Money.valueOf(Currency.USD,400),
                10, m1,c1, true);
        MusicalInstrument p2 = MusicalInstrument.build("Drums Standard Set", Money.valueOf(Currency.USD,500),
                5, m2, c2, false);
        if (musicalInstrumentRepository.findAll().isEmpty()) {
            musicalInstrumentRepository.saveAll(Arrays.asList(p1,p2));
        }
    }

}
