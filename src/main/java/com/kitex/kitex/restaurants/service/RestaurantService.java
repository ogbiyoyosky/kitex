package com.kitex.kitex.restaurants.service;

import com.kitex.kitex.city.entity.City;
import com.kitex.kitex.city.repository.CityRepository;
import com.kitex.kitex.exception.BadRequestException;
import com.kitex.kitex.exception.ForbiddenException;
import com.kitex.kitex.exception.NotFoundException;
import com.kitex.kitex.factory.EntityFactory;
import com.kitex.kitex.menu.entity.MenuItem;
import com.kitex.kitex.menu.repository.IMenuRepository;
import com.kitex.kitex.menu.services.MenuService;
import com.kitex.kitex.order.entity.PlacedOrder;
import com.kitex.kitex.order.service.IOrderService;
import com.kitex.kitex.restaurants.dto.CreateRestaurantDto;
import com.kitex.kitex.restaurants.dto.PlaceOrderPayload;
import com.kitex.kitex.restaurants.dto.RestaurantResponseDto;
import com.kitex.kitex.restaurants.dto.RestaurantWithMenusResponseDto;
import com.kitex.kitex.restaurants.entity.Restaurant;
import com.kitex.kitex.restaurants.repository.IRestaurantRepository;
import com.kitex.kitex.user.UserService;
import com.kitex.kitex.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
@Component
@Slf4j
public class RestaurantService implements  IRestaurantService {
    private final IRestaurantRepository restaurantRepository;
    private final IMenuRepository menuRepository;
    private final UserService userService;
    private final CityRepository cityRepository;
    @Lazy
    private  final IOrderService orderService;
    private final EntityFactory entityFactory;
    public Restaurant findRestaurantById(Integer restaurantId) {
        Optional<Restaurant> restaurant = this.restaurantRepository.findById(restaurantId);
        if(restaurant.isEmpty()) {
         throw new NotFoundException("Restaurant with id " + restaurantId + " not found");
        }
        return restaurant.get();
    }

    public List<RestaurantResponseDto> findRestaurants() {

        List<RestaurantResponseDto> response = new ArrayList<>();

        List<Restaurant> restaurants = this.restaurantRepository.findAll();
        for (Restaurant restaurant : restaurants) {
            RestaurantResponseDto data = RestaurantResponseDto.builder()
                    .id(restaurant.getId())
                    .address(restaurant.getAddress())
                    .name(restaurant.getName())
                    .build();

            response.add(data);
        }
        return response;
    }

    public PlacedOrder placeOrder(Integer restaurantId, PlaceOrderPayload payload, String userIdentifier) {
        return orderService.createOrder(restaurantId, payload, userIdentifier);
    }

    private Boolean validatePlaceOrderPayload(PlaceOrderPayload payload) {
        return true;
    }

    public MenuItem findRestaurantSingleMenu(Integer restaurantId, Integer menuId) {
        MenuItem menuItem = this.menuRepository.findAllByRestaurantAndMenu(restaurantId, menuId);
        if (menuItem == null) {
            throw new NotFoundException("Menu with id " + menuId + " not found");
        }
        return menuItem;
    }

    public RestaurantResponseDto createRestaurant(CreateRestaurantDto payload, String userIdentifier) {
        log.info("user : {} ", userIdentifier);
        User user = userService.findByEmail(userIdentifier);
        Optional<City> city = cityRepository.findById(payload.getCityId());
        if(city.isEmpty()) {
            throw new BadRequestException("Invalid city id");
        }
        Restaurant restaurant = this.restaurantRepository.save(entityFactory.create(payload, user, city.get()));
        return RestaurantResponseDto.builder()
                .name(restaurant.getName())
                .id(restaurant.getId())
                .address(restaurant.getAddress())
                .build();
    }

    public void  deleteRestaurant(String email ,Integer restaurantId) {
        User user = userService.findByEmail(email);
        Optional<Restaurant> restaurant = this.restaurantRepository.findById(restaurantId);
        if(user.getId() != restaurant.get().getUser().getId()) {
            throw new ForbiddenException("You don't have the permission to carry out this request");
        }
        this.restaurantRepository.delete(restaurant.get());

    }

    public RestaurantWithMenusResponseDto getRestaurantWithMenus(Integer restaurantId) {
        Optional<Restaurant> restaurant = this.restaurantRepository.findById(restaurantId);

        RestaurantWithMenusResponseDto response = RestaurantWithMenusResponseDto
                .builder()
                .menus(restaurant.get().getMenuItems())
                .name(restaurant.get().getName())
                .address(restaurant.get().getAddress())
                .id(restaurantId)
                .build();
        return response;
    }
}
