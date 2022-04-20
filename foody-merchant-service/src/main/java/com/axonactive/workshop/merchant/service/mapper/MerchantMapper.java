package com.axonactive.workshop.merchant.service.mapper;

import com.axonactive.workshop.merchant.service.entity.Merchant;
import com.axonactive.workshop.merchant.service.model.CreateMerchantRequestBody;
import com.axonactive.workshop.merchant.service.model.MerchantModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MerchantMapper {

    public static final MerchantMapper INSTANCE = Mappers.getMapper(MerchantMapper.class);

    public Merchant toMerchant(CreateMerchantRequestBody createMerchantRequestBody);

    public List<MerchantModel> toMerchantModels(List<Merchant> merchants);

    public MerchantModel toMerchantModel(Merchant merchant);

}
