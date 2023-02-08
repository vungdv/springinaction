package com.vungdo.tacocloudmaven.web.converter;

import com.vungdo.tacocloudmaven.data.IngredientRepository;
import com.vungdo.tacocloudmaven.domain.model.Ingredient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
    private final IngredientRepository ingredientRepository;

    public IngredientByIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;

    }

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findById(id).get();
    }
}
