package com.vungdo.tacocloudmaven.data;

import com.vungdo.tacocloudmaven.domain.model.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, String> {
    Optional<List<Ingredient>> findByType(Ingredient.Type type);
}