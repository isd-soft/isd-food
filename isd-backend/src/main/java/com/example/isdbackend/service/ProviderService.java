package com.example.isdbackend.service;

import com.example.isdbackend.model.*;
import com.example.isdbackend.repository.MenuRepository;
import com.example.isdbackend.repository.OrderRepository;
import com.example.isdbackend.repository.ProviderRepository;
import com.example.isdbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderService extends AbstractServiceCrud {


    public ProviderService(MenuRepository menuRepository, ProviderRepository providerRepository, OrderRepository orderRepository, UserRepository userRepository) {
        super(menuRepository, providerRepository, orderRepository, userRepository);
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
        Order order = orderRepository.findById(id).orElseThrow();
        order.setOrdered(active);
        orderRepository.save(order);
    }

    public void setUserEnabled(Long userId,Boolean enabled){
        User user =  userRepository.findById(userId).orElseThrow();
        user.setEnabled(enabled);
        userRepository.save(user);
    }

    public void createNewUser(User user){
        userRepository.save(user);
    }

    public void userEdit(User user){
        userRepository.save(user);
    }
    public void deleteUser(Long id){
        userRepository.delete(userRepository.findById(id).orElseThrow());
    }

    public void setUserGroup(Long id, Role role){
        userRepository.findById(id).orElseThrow().getRoles().add(role);
    }

    public void save(Provider provider){
        providerRepository.save(provider);
    }

    public void delete(Provider provider){
        providerRepository.delete(provider);
    }


}
