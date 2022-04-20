package com.axonactive.workshop.merchant.service.model;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

public class CreateMerchantRequestBody {

    private String name;

    private String img;

    @Valid
    private List<CreateMerchantFoodRequestBody> foods = new ArrayList<>();

    @NotEmpty
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

    @NotEmpty
    public List<CreateMerchantFoodRequestBody> getFoods() {
        return foods;
    }

    public void setFoods(List<CreateMerchantFoodRequestBody> foods) {
        this.foods = foods;
    }
}
