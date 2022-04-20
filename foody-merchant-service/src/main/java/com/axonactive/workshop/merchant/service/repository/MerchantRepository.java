package com.axonactive.workshop.merchant.service.repository;

import com.axonactive.workshop.merchant.service.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant, String> {

}
