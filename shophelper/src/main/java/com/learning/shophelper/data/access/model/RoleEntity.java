package com.learning.shophelper.data.access.model;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String type;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}