package com.vungdo.tacocloudmaven.web;

import com.vungdo.tacocloudmaven.domain.model.BunPhoOrder;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("bunPhoOrder")
public class OrderController {
    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }
    @PostMapping
    public String processOrder(@Valid BunPhoOrder bunPhoOrder, Errors errors) {
        if(errors.hasErrors()) {
            return "orderForm";
        }
        log.info("Order submitted: {}", bunPhoOrder);
        return "redirect:/";
    }
}
