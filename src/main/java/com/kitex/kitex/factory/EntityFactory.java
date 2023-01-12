package com.kitex.kitex.factory;

import com.kitex.kitex.city.entity.City;
import com.kitex.kitex.profile.dto.NewProfileDto;
import com.kitex.kitex.profile.entity.Profile;
import com.kitex.kitex.menu.entity.MenuItem;
import com.kitex.kitex.order.dto.CreateMenuDto;
import com.kitex.kitex.restaurants.dto.CreateRestaurantDto;
import com.kitex.kitex.restaurants.entity.Restaurant;
import com.kitex.kitex.user.entity.User;
import com.kitex.kitex.user.dto.NewUserDTO;
import org.springframework.stereotype.Component;

@Component
public class EntityFactory
{
    public User create(NewUserDTO user, String role, Profile profile)
    {
        return new User(
                0,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                role,
                profile,
                null
        );
    }

    public Profile create(NewProfileDto profile)
    {
        return new Profile(
               0,
                profile.getAddress(),
                profile.getPhone(),
                null
        );
    }

    public Restaurant create(CreateRestaurantDto restaurant, User user, City city)
    {
        return new Restaurant(
               0,
                restaurant.getName(),
                restaurant.getAddress(),
                null,
                user,
                city

        );
    }

    public MenuItem create(CreateMenuDto menu, Restaurant restaurant) {
        return MenuItem.builder()
                .id(0)
                .description(menu.getDescription())
                .ingredients(menu.getIngredients())
                .itemName(menu.getItemName())
                .price(menu.getPrice())
                .recipe(menu.getRecipe())
                .restaurant(restaurant)
                .build();

    }

}
