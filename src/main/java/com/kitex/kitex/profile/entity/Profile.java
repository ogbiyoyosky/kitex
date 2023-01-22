package com.kitex.kitex.profile.entity;

import com.kitex.kitex.entity.BaseEntity;
import com.kitex.kitex.user.entity.User;
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
@Table(name = "profiles")
public class Profile extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String address;

    private String contactPhone;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.PERSIST, mappedBy = "profile")
    private User user;

}

