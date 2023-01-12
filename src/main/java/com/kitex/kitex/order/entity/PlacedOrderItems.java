package com.kitex.kitex.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kitex.kitex.menu.entity.MenuItem;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="placed_order_items")
public class PlacedOrderItems implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "menu_item_id",insertable=false, updatable=false)
    private Integer menuItemId;

    @Column(name = "placed_order_id", insertable=false, updatable=false)
    private Integer placedOrderId;

    @Column
    private Integer quantity;

    @Column
    private Float itemPrice;

    @Column
    private Float price;

    @ManyToOne
    @JoinColumn(name="menu_item_id")
    private MenuItem menuItem;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="placed_order_id")
    private PlacedOrder placedOrder;

}
