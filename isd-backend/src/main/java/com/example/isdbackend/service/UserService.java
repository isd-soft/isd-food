package com.example.isdbackend.service;

import com.example.isdbackend.model.Menu;
import com.example.isdbackend.model.Order;
import com.example.isdbackend.model.Role;
import com.example.isdbackend.model.User;
import com.example.isdbackend.projection.UserView;
import com.example.isdbackend.repository.RoleRepository;
import com.example.isdbackend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final GeneratePassword generatePassword;
    private final MailSender mailSender;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(PasswordEncoder passwordEncoder, GeneratePassword generatePassword, MailSender mailSender, UserRepository userRepository, RoleRepository roleRepository) {
        this.generatePassword = generatePassword;
        this.mailSender = mailSender;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public UserView findByIdUser(Long id) {
        return userRepository.findAllById(id);
    }

    public void setNotificationSettings(Long id, Boolean active) {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Page<UserView> getAll(Pageable pageable) {
        return userRepository.findAllBy(pageable);
    }

    public void EditUserInfo(Long id, String firstName, String lastName, String SkypeId, String email, Boolean enableNotification, Date dataNotification) {
        User user = userRepository.findById(id).orElseThrow();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setSkypeId(SkypeId);
        user.setEmail(email);
        user.setNotificationEnabled(enableNotification);
        user.setDateToChange(dataNotification);
        userRepository.save(user);
    }

    public void changePass(Long id, String password) {
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

    public Iterable<Menu> filter(Long providerId, Long userId) {
        Set<Order> orders = userRepository.findById(userId).orElseThrow().getOrders();
        Set<Menu> menus = new HashSet<>();
        return menus;
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public void save(User user) {
        logger.debug("Saving an user");

        String password = generatePassword.generatePassayPassword();

        //NotificationSettings notificationSettings = new NotificationSettings();
        //notificationSettings.setEnabled(true);
        user.setNotificationEnabled(true);
        Role role = roleRepository.findByNameContaining(user.getRole().getName());
        user.setRoles(role);
        user.setPassword(passwordEncoder.encode(password).toCharArray());
        user.setEnabled(true);
        try {
            userRepository.save(user);
            logger.debug("The user is saved in the database");
        } catch (Exception e) {
            logger.error("Could not save the user");
            e.printStackTrace();
        }

        try {
            mailSender.sendHtmlMessage(user.getEmail(),"IsdFood",mailSender.getHtmlFromFile("mail/passwordSend.html",password));
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.debug("The user's password is sent on his email");
    }

    public void resetPassword(String email) {
        String password = generatePassword.generatePassayPassword();
        User user = userRepository.findByEmail(email);
        user.setPassword(passwordEncoder.encode(password).toCharArray());
        userRepository.save(user);

        mailSender.sendSimpleMessage(email, "ISD-food password reset", "Your new password: " + password);
    }

    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public User getCurrentUser() {
        return userRepository.findByEmail(getCurrentUserEmail());
    }

    public long getCurrentUserId() {
        return userRepository.findByEmail(getCurrentUserEmail()).getId();
    }

    public String getCurrentUserEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return auth.getPrincipal().toString();
    }

    public String getCurrentUserRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return auth.getAuthorities().toArray()[0].toString();
    }

    public void EditUserInfoBySupervisor(Long userId, String firstName, String lastName, String skypeId, String email,
                                         String role, Boolean enable, Date sqlDate) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setSkypeId(skypeId);
        user.setEmail(email);
        user.setEnabled(enable);
        user.setEmploymentDate(sqlDate);
        Role role1 = roleRepository.findByNameContaining(role);
        user.setRoles(role1);
        userRepository.save(user);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<String> search(String keywords) {
        return userRepository.search(keywords.toLowerCase());
    }

    public UserView getByName(String name) {
        return userRepository.findAllByName(name);
    }

}
