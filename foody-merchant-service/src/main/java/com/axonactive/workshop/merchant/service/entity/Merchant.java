package com.axonactive.workshop.merchant.service.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "merchant")
public class Merchant {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "image_url")
    private String img;

    @OneToMany(mappedBy = "merchant", orphanRemoval = true, cascade = CascadeType.ALL)
    List<Food> foods = new ArrayList<>();

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createDate;

    public String getId() {
        return id;
    }

    public Merchant setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Merchant setName(String name) {
        this.name = name;
        return this;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        foods.stream().filter(Objects::nonNull).forEach(food -> food.setMerchant(this));
        this.foods = foods;
    }

    public void addFood(Food food) {
        food.setMerchant(this);
        this.foods.add(food);
    }

    public void removeFood(Food food) {
        food.setMerchant(null);
        this.foods.remove(food);
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Merchant setCreateDate(Date createDate) {
        this.createDate = createDate;
        return this;
    }
}
