package com.vungdo.tacocloudmaven.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;


import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class BunPhoOrder {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotBlank(message="Delivery name is required")
    private String deliveryName;
    @NotBlank(message="Delivery street is required")
    private String deliveryStreet;
    @NotBlank(message="Delivery city is required")
    private String deliveryCity;
    @NotBlank(message="Delivery state is required")
    private String deliveryState;
    @NotBlank(message="Delivery zip code is required")
    private String deliveryZip;
    @CreditCardNumber(message="Not a valid credit card number")
    private String ccNumber;
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;
    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;
    @OneToMany(cascade = CascadeType.ALL)
    private List<BunPho> bunPhos = new ArrayList<>();
    public void addBunPho(BunPho bunPho) {
        this.bunPhos.add(bunPho);
    }
}