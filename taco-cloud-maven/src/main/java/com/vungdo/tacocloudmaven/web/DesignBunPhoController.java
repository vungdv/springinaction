package com.vungdo.tacocloudmaven.web;

import com.vungdo.tacocloudmaven.data.IngredientRepository;
import com.vungdo.tacocloudmaven.domain.model.BunPho;
import com.vungdo.tacocloudmaven.domain.model.BunPhoOrder;
import com.vungdo.tacocloudmaven.domain.model.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Slf4j // Lombok annotation to generate a logger of SLF4J in this class
@RequestMapping("/design")
@SessionAttributes("bunPhoOrder")
@Controller
public class DesignBunPhoController {
    private IngredientRepository ingredientRepository;

    public DesignBunPhoController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public String showDesignForm(){
        return "design";
    }

    @PostMapping
    public String processBunPho(@Validated BunPho bunpho, Errors errors, @ModelAttribute BunPhoOrder bunPhoOrder){
        if(errors.hasErrors()){
            return "design";
        }
        log.info("Processing bunpho: " + bunpho);
        bunPhoOrder.addBunPho(bunpho);
        return "redirect:/orders/current";
    }
    @ModelAttribute
    public void addCustomFunctionToModel(Model model){
        log.info("addCustomFunctionToModel");
        Optional<List<Ingredient>> custom = ingredientRepository.findByType(Ingredient.Type.CHEESE);
        model.addAttribute("customFunction", custom);
        log.info("custom:" + custom);
    }
    @ModelAttribute
    public void addIngredientsToModel(Model model){
        log.info("addIngredientsToModel");
        Stream<Ingredient> ingredientStream = StreamSupport.stream(ingredientRepository.findAll().spliterator(), false);
        Map<Ingredient.Type, List<Ingredient>> typeListMap =
                ingredientStream.collect(Collectors.groupingBy(Ingredient::getType));
        for (Ingredient.Type type : typeListMap.keySet()) {
            model.addAttribute(type.toString().toLowerCase(), typeListMap.get(type));
        }
        log.info("size: " + typeListMap.size());
    }
    @ModelAttribute(name = "bunPhoOrder")
    public BunPhoOrder bunPhoOrder(){
        log.info("bunPhoOrder");
        return new BunPhoOrder();
    }
    @ModelAttribute(name = "bunPho")
    public BunPho bunPho(){
        log.info("bunPho");
        return new BunPho();
    }
    @ModelAttribute(name = "message")
    public String getMessage(){
        log.info("getMessage");
        return "BunPho List";
    }
    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
