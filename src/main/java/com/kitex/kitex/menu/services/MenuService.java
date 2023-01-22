package com.kitex.kitex.menu.services;

import com.kitex.kitex.exception.NotFoundException;
import com.kitex.kitex.factory.EntityFactory;
import com.kitex.kitex.menu.entity.Category;
import com.kitex.kitex.menu.entity.MenuItem;
import com.kitex.kitex.menu.repository.CategoryRepository;
import com.kitex.kitex.menu.repository.IMenuRepository;
import com.kitex.kitex.menu.dto.CreateMenuDto;
import com.kitex.kitex.restaurants.entity.Restaurant;
import com.kitex.kitex.restaurants.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Lazy
@RequiredArgsConstructor
public class MenuService implements IMenuService {
    private final IMenuRepository menuRepository;
    @Lazy
    private final IRestaurantRepository restaurantRepository;

    private final CategoryRepository categoryRepository;
    private final EntityFactory entityFactory;

    public List<MenuItem> getRestaurantMenu(Integer restaurantId) {
        List<MenuItem> menus = menuRepository.findAll();
        return menus;
    }

    public  MenuItem getSingleRestaurantMenu(Integer restaurantId, Integer menuId) {
        MenuItem menus = menuRepository.findAllByRestaurantAndMenu(restaurantId, menuId);
        return menus;
    }


    public MenuItem createRestaurantMenu(Integer restaurantId, CreateMenuDto payload) {
        Optional<Restaurant> restaurant = this.restaurantRepository.findById(restaurantId);
        if(restaurant.isEmpty()) {
            throw new NotFoundException("Restaurant not found");
        }

        Optional<Category> category = this.categoryRepository.findById(payload.getCategoryId());
        if(restaurant.isEmpty()) {
            throw new NotFoundException("Category not found");
        }
        return this.menuRepository.save(this.entityFactory.create(payload, restaurant.get(), category.get()));

    }

    public void deleteRestaurantMenu(Integer menuId) {
        Optional<MenuItem> menuItem = this.menuRepository.findById(menuId);
        if(menuItem.isEmpty()) {
            throw new NotFoundException("menu not found");
        }
        this.menuRepository.delete(menuItem.get());
    }

//    public  MenuItem updateRestaurantMenu(Integer restaurantId, CreateMenuDto payload) {
//        Optional<Restaurant> restaurant = this.restaurantRepository.findById(restaurantId);
//        if(restaurant.isEmpty()) {
//            throw new NotFoundException("Restaurant not found");
//        }
//        return this.menuRepository.save();
//    }
}
