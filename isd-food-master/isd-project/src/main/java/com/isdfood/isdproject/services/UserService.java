package com.isdfood.isdproject.services;

import com.isdfood.isdproject.exceptions.IdenticalPasswordException;
import com.isdfood.isdproject.model.Menu;
import com.isdfood.isdproject.model.Orders;
import com.isdfood.isdproject.model.Provider;
import com.isdfood.isdproject.model.User;
import com.isdfood.isdproject.repositories.*;
import org.springframework.stereotype.Service;

@Service
public class UserService extends PersonService {

    public UserService(MenuItemsRepository menuItemsRepository, MenuRepository menuRepository, NotificationRepository notificationRepository, OrdersRepository ordersRepository, ProviderRepository providerRepository, UserRepository userRepository) {
        super(menuItemsRepository, menuRepository, notificationRepository, ordersRepository, providerRepository, userRepository);
    }



    //NotificationSelect
    public void setNotification(boolean active, Long id){
        User user = getUserRepository().findById(id).orElseThrow();
        user.setNotificationActive(active);
        getUserRepository().save(user);


    }
    //SetProvider
    public void setProvider(Long provider_id, Long user_id){
        getUserRepository().findById(user_id).orElseThrow().setProvider(getProviderRepository().findById(provider_id).orElseThrow());
    }


    //UserOrders
    public Iterable<Orders> getUserOrders(Long id){
        return getUserRepository().findById(id).orElseThrow().getOrders();
    }

    //GetProvider
    public Provider getUserProvider(Long id){
        return getUserRepository().findById(id).orElseThrow().getProvider();
    }

    //UserProfileRefactor
    public void profileRefactor(Long id,String email,String firstName,String lastName,String skypeId,Byte[] img){
        User user = getUserRepository().findById(id).orElseThrow();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setSkypeId(skypeId);
        user.setImage(img);
        getUserRepository().save(user);
    }
    //UserProfileRefactor(with password)
    public void profileRefactor(Long id,String email,String firstName,String lastName,String skypeId,String password,Byte[] img) throws IdenticalPasswordException {
        User user = getUserRepository().findById(id).orElseThrow();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setSkypeId(skypeId);
        user.setImage(img);
        if(!user.getPassword().equals(password))
            user.setPassword(password);
        else
            throw new IdenticalPasswordException();
        getUserRepository().save(user);
    }






}
