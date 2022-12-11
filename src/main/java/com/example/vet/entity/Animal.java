package com.example.vet.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "animal")
@Getter
@Setter
public class Animal {
    @Id
    @Column(name="animal_category")
    private int AnimalCategory;

    @Column(name = "animal_category_name")
    private String AnimalCategoryName;

    public Animal() {
    }

    public Animal(String AnimalCategoryName) {
        this.AnimalCategoryName = AnimalCategoryName;
    }

}
