package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "payment_method")
public class PaymentMethod implements Serializable {

    @Id
    @Column(name = "method_id")
    private String id;

    @Column(name = "method_name")
    private String methodName;

    @OneToMany(mappedBy = "paymentMethod",
            cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Oder> orders;

    public PaymentMethod(String id, String methodName) {
        this.id = id;
        this.methodName = methodName;
    }

    public PaymentMethod() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @JsonIgnore
    public Set<Oder> getOrder() {
        return orders;
    }

    public void setOrder(Set<Oder> orders) {
        this.orders = orders;
    }
}
