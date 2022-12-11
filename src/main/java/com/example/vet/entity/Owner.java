package com.example.vet.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "owner")
@Getter
@Setter

public class Owner {
    @Id
    @Column(name="owner_code")
    private int OwnerCode;

    @Column(name = "owner_name")
    private String OwnerName;

    @Column(name = "owner_telephone")
    private String OwnerTelephone;

    public Owner() {
    }

    public Owner(String OwnerName, String OwnerTelephone) {
        this.OwnerName = OwnerName;
        this.OwnerTelephone = OwnerTelephone;
    }

}
