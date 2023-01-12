package com.kitex.kitex.order.repository;

import com.kitex.kitex.order.entity.PlacedOrder;
import com.kitex.kitex.restaurants.entity.Restaurant;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IOrderRepository extends JpaRepository<PlacedOrder, Integer> {

    List<PlacedOrder> findAllByRestaurant(Restaurant restaurant);

    Optional<PlacedOrder> findByOrderReference(String ref);
    @Modifying
    @Transactional
    @Query(value = "Insert into placed_order_items (placed_order_id, menu_item_id, quantity, item_price, price) values (:placedOrderId,:menuItemId,:quantity,:itemPrice,:price)",
            nativeQuery = true
    )
    void createOrderWithItems(@Param("placedOrderId") Integer placedOrderId,@Param("menuItemId") Integer menuItemId,
                              @Param("quantity") Integer quantity,@Param("itemPrice") Float itemPrice, @Param("price") Float price);

}
