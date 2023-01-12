package com.kitex.kitex.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kitex.kitex.profile.entity.Profile;
import com.kitex.kitex.entity.BaseEntity;
import com.kitex.kitex.restaurants.entity.Restaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "placed_orders")
public class PlacedOrder  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime orderTime;

    private LocalDateTime estimatedDeliveryTime;

    private LocalDateTime foodReadyAt;

    private String deliveryAddress;

    private Float price;

    private  float subTotal;

    private String orderReference;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "restaurant_id" )
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "profile_id" )
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "status_id" )
    private Status status;

    @JsonIgnore
    @OneToMany(mappedBy = "placedOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PlacedOrderItems> placedOrderItems;
}

