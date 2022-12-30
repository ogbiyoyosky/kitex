package com.kitex.kitex.user.entity;

import com.kitex.kitex.customer.entity.Customer;
import com.kitex.kitex.entity.BaseEntity;
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
@Table(name = "users")
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private String role;

	@OneToOne(mappedBy = "user")
	private Customer customer;


}
