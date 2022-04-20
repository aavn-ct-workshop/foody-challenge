package com.axonactive.workshop.merchant.service.impl;

import com.axonactive.workshop.merchant.service.model.CreateMerchantRequestBody;
import com.axonactive.workshop.merchant.service.model.MerchantModel;
import com.axonactive.workshop.merchant.service.model.SearchMerchantModel;

import java.util.List;

public interface MerchantService {

    public String createMerchant(CreateMerchantRequestBody requestBody);

    public List<MerchantModel> searchMerchants(SearchMerchantModel searchModel);

    public MerchantModel readMerchantById(String id);

}
