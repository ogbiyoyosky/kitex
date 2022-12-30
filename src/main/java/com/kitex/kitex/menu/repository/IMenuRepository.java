package com.kitex.kitex.menu.repository;

import com.kitex.kitex.menu.entity.MenuItem;
import com.kitex.kitex.restaurants.entity.Restaurant;
import com.kitex.kitex.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IMenuRepository extends JpaRepository<MenuItem,Integer> {
    List<MenuItem> findAllByRestaurantId(Integer restaurantId);

    @Query(value = "SELECT * from menu_items m  where m.restaurant_id = :restaurantId AND m.id = :menuId",
            nativeQuery = true
    )
    MenuItem findAllByRestaurantAndMenu(@Param("restaurantId") Integer restaurantId, @Param("menuId") Integer MenuId);
}
