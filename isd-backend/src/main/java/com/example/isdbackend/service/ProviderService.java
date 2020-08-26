package com.example.isdbackend.service;

import com.example.isdbackend.dto.ProviderAvailableDTO;
import com.example.isdbackend.filter.OrderFilter;
import com.example.isdbackend.model.Menu;
import com.example.isdbackend.model.Order;
import com.example.isdbackend.model.Provider;
import com.example.isdbackend.model.User;
import com.example.isdbackend.repository.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProviderService extends AbstractServiceCrud {


    private MailSender mailSender;
    private GeneratePassword generatePassword;

    private final UserService userService;

    public ProviderService(UserService userService, MailSender mailSender, MenuRepository menuRepository, ProviderRepository providerRepository, OrderRepository orderRepository, UserRepository userRepository, MenuTypeRepository menuTypeRepository, MailSender mailSender1, GeneratePassword generatePassword) {
        super(mailSender, menuRepository, providerRepository, orderRepository, userRepository, menuTypeRepository);
        this.mailSender = mailSender1;
        this.generatePassword = generatePassword;
        this.userService = userService;
    }

    public Provider findById(Integer id) {
        return providerRepository.findById(id).orElseThrow();
    }

    public List<Provider> findAllProviders() {
        return providerRepository.findAll();
    }

    public List<ProviderAvailableDTO> getProviders(OrderFilter orderFilter) {
        List<String> availableProviders = providerRepository.findAvailableProviders(userService.getCurrentUserId(), orderFilter);
        List<String> providers = providerRepository.findAllBy();

        List<ProviderAvailableDTO> providerAvailableDTOList = new ArrayList<>();

        for (String provider : providers) {
            ProviderAvailableDTO providerAvailableDTO = new ProviderAvailableDTO();
            providerAvailableDTO.setName(provider);
            providerAvailableDTO.setNotAvailable(!availableProviders.contains(provider));

            providerAvailableDTOList.add(providerAvailableDTO);
        }

        return providerAvailableDTOList;
    }

    public Iterable<Menu> getProviderMenus(Integer providerId) {
        return providerRepository.findById(providerId).orElseThrow().getMenus();
    }

    public void addProvider(Provider provider) {
        providerRepository.save(provider);
    }

    public void setIsOrdered(Long id, Boolean active) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setOrdered(active);
        orderRepository.save(order);
    }

    public void setUserEnabled(Long userId, Boolean enabled) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setEnabled(enabled);
        userRepository.save(user);
    }

    public void sendMailWithPassword(String emailTo, String subject) {
        try {
            String pass = generatePassword.generatePassayPassword();
            mailSender.sendHtmlMessage(emailTo, subject, mailSender.getHtmlFromFile("mail/passwordSend.html", pass));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void createNewUser(User user) {
        userRepository.save(user);
    }

    public void userEdit(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.delete(userRepository.findById(id).orElseThrow());
    }

//    public void setUserGroup(Long id, Role role) {
//        userRepository.findById(id).orElseThrow().getRoles().add(role);
//    }

    public void updateProvider(Provider provider) {
        providerRepository.save(provider);
    }


    public void deleteProvider(int id) {
        providerRepository.deleteById((int) id);
    }

    public void delete(Integer id) {
        providerRepository.delete(providerRepository.findById(id).orElseThrow());
    }


}
