package com.axonactive.workshop.merchant.service.controller;

import com.axonactive.workshop.merchant.service.impl.MerchantService;
import com.axonactive.workshop.merchant.service.model.CreateMerchantRequestBody;
import com.axonactive.workshop.merchant.service.model.MerchantModel;
import com.axonactive.workshop.merchant.service.model.SearchMerchantModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/merchants")
public class MerchantServiceController {

    private MerchantService merchantService;

    @Autowired
    public MerchantServiceController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PostMapping
    public ResponseEntity<String> createMerchant(@RequestBody @Valid @NotNull CreateMerchantRequestBody requestBody) {
        String createdMerchantId = merchantService.createMerchant(requestBody);
        return new ResponseEntity<>(createdMerchantId, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MerchantModel> readMerchant(@PathVariable("id") String id) {
        MerchantModel merchantModel = merchantService.readMerchantById(id);
        return new ResponseEntity(merchantModel, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<MerchantModel> searchMerchant(@RequestParam(name = "name", required = false) String foodName, @RequestParam(name = "limit", required = false) Integer limit, @RequestParam(name = "offset", required = false) Integer offset) {
        SearchMerchantModel searchMerchantModel = new SearchMerchantModel()
            .setName(foodName).setLimit(limit).setOffset(offset);

        List<MerchantModel> merchantModels = merchantService.searchMerchants(searchMerchantModel);
        return new ResponseEntity(merchantModels, HttpStatus.OK);
    }

}
