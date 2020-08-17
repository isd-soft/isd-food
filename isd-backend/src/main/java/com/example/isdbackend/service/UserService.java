package com.example.isdbackend.service;

import com.example.isdbackend.exception.IsOrderedException;
import com.example.isdbackend.model.Menu;
import com.example.isdbackend.model.NotificationSettings;
import com.example.isdbackend.model.Order;
import com.example.isdbackend.model.User;

import com.example.isdbackend.repository.*;

import com.example.isdbackend.projection.UserView;
import com.example.isdbackend.repository.MenuRepository;
import com.example.isdbackend.repository.OrderRepository;
import com.example.isdbackend.repository.ProviderRepository;
import com.example.isdbackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService extends AbstractServiceCrud {
    PasswordEncoder passwordEncoder;

    public UserService(MailSender mailSender, MenuRepository menuRepository, ProviderRepository providerRepository, OrderRepository orderRepository, UserRepository userRepository, MenuTypeRepository menuTypeRepository, PasswordEncoder passwordEncoder) {
        super(mailSender, menuRepository, providerRepository, orderRepository, userRepository, menuTypeRepository);
        this.passwordEncoder = passwordEncoder;
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public UserView findByIdUser(Long id){
        return userRepository.findAllById(id);
    }

    public void setNotificationSettings(Long id, Boolean active) {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.save(user);
    }

//    public void setNotificationSettings(Long id, NotificationSettings notificationSettings) {
//        User user = userRepository.findById(id).orElseThrow();
//        user.setNotificationSettings(notificationSettings);
//        userRepository.save(user);
//    }
    public List<User> findAll(){
        return userRepository.findAll();
    }

//    public void EditUserInfo(Long id, String firstName, String lastName, String SkypeId, String email){
//        User user = userRepository.findById(id).orElseThrow();
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//        user.setSkypeId(SkypeId);
//        user.setEmail(email);
//        userRepository.save(user);
//    }

    public void EditUserInfo(Long id, String firstName, String lastName, String SkypeId, String email, Boolean enable, String data){
        User user = userRepository.findById(id).orElseThrow();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setSkypeId(SkypeId);
        user.setEmail(email);
        user.setNotificationEnabled(enable);
        user.setNotificationData(data);
        userRepository.save(user);
    }

    public void changePass(Long id, String password){
        User user = userRepository.findById(id).orElseThrow();

        user.setPassword(passwordEncoder.encode(password).toCharArray());
        userRepository.save(user);
    }



    public Set<Order> getHistory(Long id) {
        return userRepository.findById(id).orElseThrow().getOrders();
    }


    public List<Order> getCurrentOrders(Long id) {
        return userRepository.findById(id).orElseThrow().getOrders().stream().filter(order -> order.isOrdered()).collect(Collectors.toList());
    }

    public Iterable<Menu> getProviderMenus(Long providerId) {
        return providerRepository.findById(providerId).orElseThrow().getMenus();
    }

    public Iterable<Menu> filter(Long providerId, Long userId) {
        Set<Order> orders = userRepository.findById(userId).orElseThrow().getOrders();
        Set<Menu> menus = new HashSet<>();
        return menus;
    }

    public void newOrder(Long id, Order order) {
        User user = userRepository.findById(id).orElseThrow();
        user.getOrders().add(order);
        userRepository.save(user);
    }

    public void orderUpload(Order orderUpload) throws IsOrderedException {
        if (!orderRepository.findById(orderUpload.getId()).orElseThrow().isOrdered())
            orderRepository.save(orderUpload);
        else
            throw new IsOrderedException();
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
