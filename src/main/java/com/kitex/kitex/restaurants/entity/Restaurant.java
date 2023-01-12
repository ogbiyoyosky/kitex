package com.kitex.kitex.restaurants.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kitex.kitex.city.entity.City;
import com.kitex.kitex.entity.BaseEntity;
import com.kitex.kitex.menu.entity.MenuItem;
import com.kitex.kitex.profile.entity.Profile;
import com.kitex.kitex.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "restaurants")
public class Restaurant  extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String address;

    @OneToMany(mappedBy = "restaurant")
    private List<MenuItem> menuItems;

    @ManyToOne
    @JoinColumn(name = "user_id" )
    private User user;

    @OneToOne()
    @JoinColumn(name = "city_id")
    private City city;

}
