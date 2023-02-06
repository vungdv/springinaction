package com.vungdo.tacocloudmaven.web;

import com.vungdo.tacocloudmaven.domain.model.BunPho;
import com.vungdo.tacocloudmaven.domain.model.BunPhoOrder;
import com.vungdo.tacocloudmaven.domain.model.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j // Lombok annotation to generate a logger of SLF4J in this class
@RequestMapping("/design")
@SessionAttributes("bunPhoOrder")
@Controller
public class DesignBunPhoController {
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
    public void addIngredientsToModel(Model model){
        System.out.println("addIngredientsToModel");
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
        );

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }
    @ModelAttribute(name = "bunPhoOrder")
    public BunPhoOrder bunPhoOrder(){
        System.out.println("bunPhoOrder");
        return new BunPhoOrder();
    }
    @ModelAttribute(name = "bunPho")
    public BunPho bunPho(){
        System.out.println("bunPho");
        return new BunPho();
    }
    @ModelAttribute(name = "message")
    public String getMessage(){
        System.out.println("getMessage");
        return "BunPho List";
    }
    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
