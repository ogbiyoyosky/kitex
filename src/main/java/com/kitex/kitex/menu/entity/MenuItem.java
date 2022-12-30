package com.kitex.kitex.menu.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kitex.kitex.entity.BaseEntity;
import com.kitex.kitex.restaurants.entity.Restaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "menu_items")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "restaurant"})
public class MenuItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String itemName;

    private String description;

    private String ingredients;

    private  String recipe;

    @ManyToOne
    @JoinColumn(name = "restaurant_id" )
    private Restaurant restaurant;

    private Float price;

    private Boolean active;
}
