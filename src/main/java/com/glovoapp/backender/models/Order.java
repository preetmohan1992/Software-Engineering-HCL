package com.glovoapp.backender.models;

import javax.validation.constraints.NotBlank;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @NotBlank
    private String description;
    private boolean food;
    private boolean vip;

    @OneToOne(cascade = CascadeType.ALL)
    private Location pickup;

    @OneToOne(cascade = CascadeType.ALL)
    private Location delivery;

    private Date createdAt;

    protected Order() { }

    public Order(
            String description,
            boolean food,
            boolean vip,
            Location pickup,
            Location delivery
    ) {
        this.description = description;
        this.food = food;
        this.vip = vip;
        this.pickup = pickup;
        this.delivery = delivery;
        this.createdAt = DateTime.now(DateTimeZone.UTC).toDate();
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean getFood() {
        return food;
    }

    public boolean getVip() {
        return vip;
    }

    public Location getPickup() {
        return pickup;
    }

    public Location getDelivery() {
        return delivery;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
