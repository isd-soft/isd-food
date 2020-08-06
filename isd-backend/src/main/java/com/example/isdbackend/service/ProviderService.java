package com.example.isdbackend.service;

import com.example.isdbackend.model.Provider;
import com.example.isdbackend.repository.ProviderRepository;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderService {
    private ProviderRepository providerRepository;

    public List<Provider> findAll(){
        var it = providerRepository.findAll();

        var providers = new ArrayList<Provider>();
        it.forEach(providers::add);
        return providers;
    }

    public void addProvider( String name, Integer deliveryPrice, String contactInfo, String description, URL imageURL, Boolean isActive){
        Provider provider = new Provider(name,deliveryPrice,contactInfo,description,imageURL,isActive);
        providerRepository.save(provider);
    }

    public void update (Integer providerId, String name){
        Provider provider;
        if (providerRepository.findById(providerId).isPresent()) {
            provider = providerRepository.findById(providerId).get();
            provider.setName(name);
            providerRepository.save(provider);
        }
    }
    public void deleteById(Integer providerId) {
        providerRepository.deleteById(providerId);
    }
}
