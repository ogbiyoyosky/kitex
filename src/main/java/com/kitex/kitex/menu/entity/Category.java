package com.kitex.kitex.menu.entity;

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
@Table(name = "categories")
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.PERSIST, mappedBy = "category")
    private MenuItem menuItem;
}
