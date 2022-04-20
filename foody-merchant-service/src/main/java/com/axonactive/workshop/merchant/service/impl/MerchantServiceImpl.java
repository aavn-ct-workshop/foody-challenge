package com.axonactive.workshop.merchant.service.impl;

import com.axonactive.workshop.merchant.service.entity.Merchant;
import com.axonactive.workshop.merchant.service.mapper.MerchantMapper;
import com.axonactive.workshop.merchant.service.model.CreateMerchantRequestBody;
import com.axonactive.workshop.merchant.service.model.MerchantModel;
import com.axonactive.workshop.merchant.service.model.SearchMerchantModel;
import com.axonactive.workshop.merchant.service.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MerchantServiceImpl implements MerchantService {

    private MerchantRepository repository;

    @Autowired
    public MerchantServiceImpl(MerchantRepository repository) {
        this.repository = repository;
    }

    @Override
    public String createMerchant(CreateMerchantRequestBody requestBody) {
        Merchant merchant = MerchantMapper.INSTANCE.toMerchant(requestBody);
        merchant = repository.save(merchant);
        return merchant.getId();
    }

    @Override
    public List<MerchantModel> searchMerchants(SearchMerchantModel searchModel) {
        List<Merchant> merchants;
        if (searchModel.getLimit() == null || searchModel.getOffset() == null) {
            merchants = repository.findAll();
        } else {
            Page<Merchant> all = repository.findAll(PageRequest.of(searchModel.getOffset(), searchModel.getLimit()));
            merchants = all.toList();
        }
        return MerchantMapper.INSTANCE.toMerchantModels(merchants);
    }

    @Override
    public MerchantModel readMerchantById(String id) {
        if (id == null || id.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("Invalid id [%s].", id)
            );
        }
        Merchant merchant = getMerchant(id);
        return MerchantMapper.INSTANCE.toMerchantModel(merchant);
    }

    private Merchant getMerchant(String merchantId) {
        Optional<Merchant> merchant = repository.findById(merchantId);
        if (merchant.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Merchant with id [%s] not found.", merchantId)
            );
        }
        return merchant.get();
    }
}
