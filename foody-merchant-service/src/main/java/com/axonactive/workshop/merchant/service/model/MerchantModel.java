package com.axonactive.workshop.merchant.service.model;

import java.util.ArrayList;
import java.util.List;

public class MerchantModel {

    private String id;

    private String name;

    private String img;

    private List<MerchantFoodModel> foods = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<MerchantFoodModel> getFoods() {
        return foods;
    }

    public void setFoods(List<MerchantFoodModel> foods) {
        this.foods = foods;
    }
}
