package com.kitex.kitex.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kitex.kitex.profile.entity.Profile;
import com.kitex.kitex.entity.BaseEntity;
import com.kitex.kitex.restaurants.entity.Restaurant;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String firstName;

	private String lastName;

	private String email;

	@JsonIgnore
	private String password;

	private String role;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "profile_id")
	private Profile profile;

	@OneToMany(mappedBy = "user")
	private List<Restaurant> restaurants;

}
