package com.kitex.kitex.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kitex.kitex.customer.entity.Customer;
import com.kitex.kitex.entity.BaseEntity;
import com.kitex.kitex.restaurants.entity.Restaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "restaurant_id" )
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "customer_id" )
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "status_id" )
    private Status status;

}

