package com.kitex.kitex.menu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kitex.kitex.entity.BaseEntity;
import com.kitex.kitex.order.entity.PlacedOrder;
import com.kitex.kitex.order.entity.PlacedOrderItems;
import com.kitex.kitex.restaurants.entity.Restaurant;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
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

    @JsonIgnore
    @OneToMany(mappedBy = "menuItem")
    private Set<PlacedOrderItems> placedOrderItems;

    private Float price;

    private Boolean active;
}
