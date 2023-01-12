package com.kitex.kitex.city.entity;

import com.kitex.kitex.entity.BaseEntity;
import com.kitex.kitex.profile.entity.Profile;
import com.kitex.kitex.restaurants.entity.Restaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cities")
public class City extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String cityName;

    private String postCode;

    @OneToOne(mappedBy = "city")
    private Restaurant restaurant;

}
