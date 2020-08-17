package com.example.isdbackend.dto.converter;

import com.example.isdbackend.dto.OrderDTO;
import com.example.isdbackend.model.MenuType;
import com.example.isdbackend.model.Order;
import com.example.isdbackend.model.User;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter extends Converter<OrderDTO, Order> {

    public OrderConverter() {
        super(OrderConverter::convertToEntity, OrderConverter::convertToDto);
    }

    private static OrderDTO convertToDto(Order order) {
        return new OrderDTO(order.getDate(), order.getMenuType().getId(), order.getUser().getId());
    }

    private static Order convertToEntity(OrderDTO dto) {
        Order order = new Order();

        order.setDate(dto.getDate());

        MenuType menuType = new MenuType();
        menuType.setId(dto.getMenuTypeId());

        User user = new User();
        user.setId(dto.getUserId());

        order.setMenuType(menuType);
        order.setUser(user);
        order.setOrdered(false);

        return order;
    }
}
