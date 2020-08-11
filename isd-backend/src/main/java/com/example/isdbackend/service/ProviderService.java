package com.example.isdbackend.service;

import com.example.isdbackend.model.*;
import com.example.isdbackend.repository.MenuRepository;
import com.example.isdbackend.repository.OrderRepository;
import com.example.isdbackend.repository.ProviderRepository;
import com.example.isdbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService extends AbstractServiceCrud {

    @Autowired
    public ProviderService(MailSender mailSender, MenuRepository menuRepository, ProviderRepository providerRepository, OrderRepository orderRepository, UserRepository userRepository) {
        super(mailSender, menuRepository, providerRepository, orderRepository, userRepository);
    }

    public List<Provider> findAllProviders(){
        return providerRepository.findAll();
    }

    public void addProvider(Provider provider){
        providerRepository.save(provider);
    }


    public void updateProvider(Provider provider){
        providerRepository.save(provider);
    }

//    public void setIsOrdered(Long id, Boolean active){
//        Order order = orderRepository.findById(id).orElseThrow();
//        order.setOrdered(active);
//        orderRepository.save(order);
//    }
//
//    public void setUserEnabled(Long userId,Boolean enabled){
//        User user =  userRepository.findById(userId).orElseThrow();
//        user.setEnabled(enabled);
//        userRepository.save(user);
//    }
//
//    public void createNewUser(User user){
//        userRepository.save(user);
//    }
//
//    public void userEdit(User user){
//        userRepository.save(user);
//    }
//    public void deleteUser(Long id){
//        userRepository.delete(userRepository.findById(id).orElseThrow());
//    }
//
//    public void setUserGroup(Long id, Role role){
//        userRepository.findById(id).orElseThrow().getRoles().add(role);
//    }

//    public void save(Provider provider){
//        providerRepository.save(provider);
//    }

    public void deleteProvider(int id){
        providerRepository.deleteById((long) id);
    }


}
