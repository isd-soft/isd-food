package com.example.isdbackend.service;

import com.example.isdbackend.model.Menu;
import com.example.isdbackend.model.Order;
import com.example.isdbackend.model.Provider;
import com.example.isdbackend.repository.ProviderRepository;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderService {
    private final ProviderRepository providerRepository;
    private final OrderService orderService;

    public ProviderService(ProviderRepository providerRepository, OrderService orderService) {
        this.providerRepository = providerRepository;
        this.orderService = orderService;
    }

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

    public Iterable<Menu> getProviderMenus(Integer id){
        return providerRepository.findById(id).orElseThrow().getMenus();
    }
    public Provider getProvider(Integer id){
        return providerRepository.findById(id).orElseThrow();
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

    public void setIsOrdered(Long id, Boolean active){
       orderService.setIsOrdered(id,active);

    }
}
