package com.ruleengine.droolengine.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String name;
    private String cardType;
    private int discount;
    private int price;
}
