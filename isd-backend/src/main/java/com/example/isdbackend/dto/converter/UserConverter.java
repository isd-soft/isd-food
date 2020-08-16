package com.example.isdbackend.dto.converter;

import com.example.isdbackend.dto.UserDTO;
import com.example.isdbackend.model.User;

public class UserConverter extends Converter<UserDTO, User> {

    public UserConverter() {
        super(UserConverter::convertToEntity, UserConverter::convertToDto);
    }

    private static UserDTO convertToDto(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setEmail(user.getEmail());
        userDTO.setEmploymentDate(user.getEmploymentDate());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEnabled(user.getEnabled());
        userDTO.setNotificationSettings(user.getNotificationSettings());
        userDTO.setPassword(user.getPassword());

        return userDTO;
    }

    private static User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setEmploymentDate(userDTO.getEmploymentDate());
        user.setNotificationSettings(userDTO.getNotificationSettings());
        user.setEnabled(userDTO.getEnabled());
        user.setPassword(userDTO.getPassword());

        return user;
    }
}
