package com.vungdo.tacocloudmaven.data;

import com.vungdo.tacocloudmaven.domain.model.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}