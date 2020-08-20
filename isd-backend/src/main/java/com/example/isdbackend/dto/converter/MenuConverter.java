package com.example.isdbackend.dto.converter;

import com.example.isdbackend.dto.MenuDTO;
import com.example.isdbackend.model.Menu;
import com.example.isdbackend.model.MenuType;
import com.example.isdbackend.repository.MenuTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class MenuConverter extends Converter<MenuDTO, Menu> {

    public MenuTypeRepository menuTypeRepository;

    public MenuConverter(Function<MenuDTO, Menu> fromDto, Function<Menu, MenuDTO> fromEntity, MenuTypeRepository menuTypeRepository) {
        super(fromDto, fromEntity);
        this.menuTypeRepository = menuTypeRepository;
    }


    public MenuConverter() {
        super(MenuConverter::convertToEntity, MenuConverter::convertToDto);
    }

    private static MenuDTO convertToDto(Menu menu) {
        return new MenuDTO(menu.getName(), menu.getDayOfWeek(),  menu.getImage(), menu.getProvider(), menu.getMenuTypes());
    }

    private static Menu convertToEntity(MenuDTO dto) {
        Menu menu = new Menu();

        menu.setName(dto.getName());
        menu.setDayOfWeek(dto.getDayOfWeek());
        menu.setImage(dto.getImage());
        menu.setProvider(dto.getProvider());

        for (MenuType menuType: dto.getMenuTypes()) {
            menuType.setMenu(menu);
        }
        return menu;
    }
}