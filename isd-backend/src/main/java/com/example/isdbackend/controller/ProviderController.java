package com.example.isdbackend.controller;

import com.example.isdbackend.dto.ProviderCreationDTO;
import com.example.isdbackend.dto.ProviderUpdateDTO;
import com.example.isdbackend.model.Provider;
import com.example.isdbackend.service.ProviderService;
import com.example.isdbackend.util.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProviderController {
    private final ProviderService providerService;

    @Autowired
    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @PostMapping
    public void addProvider(@DTO(ProviderCreationDTO.class) Provider provider){
        providerService.addProvider(provider);
    }

    @GetMapping
    public List<Provider> getAllProviders(){
        return providerService.findAllProviders();
    }


    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateProvider(@DTO(ProviderUpdateDTO.class) Provider provider){
        providerService.updateProvider(provider);
    }


    @DeleteMapping
    public void deleteProvider(Provider provider){
        providerService.delete(provider);
    }

}
