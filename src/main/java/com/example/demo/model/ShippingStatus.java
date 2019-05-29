package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "shipping_status")
public class ShippingStatus implements Serializable {

    @Id
    @Column(name = "status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status_name")
    private String name;

    @OneToMany(mappedBy = "shippingStatus",fetch = FetchType.LAZY,
            orphanRemoval = true,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Oder> orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Oder> getOrders() {
        return orders;
    }

    public void setOrders(Set<Oder> orders) {
        this.orders = orders;
    }

    public ShippingStatus(String name) {
        this.name = name;
    }

    public ShippingStatus() {
    }
}
