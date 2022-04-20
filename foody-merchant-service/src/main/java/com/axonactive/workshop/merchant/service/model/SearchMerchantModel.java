package com.axonactive.workshop.merchant.service.model;

import java.util.Optional;

public class SearchMerchantModel {

    private String name;

    private Integer limit;

    private Integer offset;

    public String getName() {
        return name;
    }

    public SearchMerchantModel setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public SearchMerchantModel setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public Integer getOffset() {
        return offset;
    }

    public SearchMerchantModel setOffset(Integer offset) {
        this.offset = offset;;
        return this;
    }
}
