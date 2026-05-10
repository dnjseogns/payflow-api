package com.dhwon.payflow_api.api.menu.mapper;

import com.dhwon.payflow_api.api.menu.dto.MenuRequestDto;
import com.dhwon.payflow_api.api.menu.dto.MenuResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MenuMapperTest {

    @Autowired
    private MenuMapper menuMapper;

    @Test
    @DisplayName("메뉴 목록 조회")
    void selectMenuListTest() {

        List<MenuResponseDto> result =
                menuMapper.selectMenuList();

        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("메뉴 등록")
    void insertMenuTest() {

        Long menuId = 10001L;

        MenuRequestDto dto =
                MenuRequestDto.builder()
                        .menuId(menuId)
                        .menuName("테스트메뉴")
                        .menuUrl("/test/menu")
                        .menuLevel(1)
                        .sortOrder(1)
                        .iconName("home")
                        .useYn("Y")
                        .build();

        int result =
                menuMapper.insertMenu(dto, "SYSTEM");

        assertThat(result).isEqualTo(1);

        MenuResponseDto saved =
                menuMapper.selectMenuDetail(menuId);

        assertThat(saved).isNotNull();

        assertThat(saved.getMenuName())
                .isEqualTo("테스트메뉴");
    }

    @Test
    @DisplayName("메뉴 수정")
    void updateMenuTest() {

        Long menuId = 10002L;

        MenuRequestDto insertDto =
                MenuRequestDto.builder()
                        .menuId(menuId)
                        .menuName("기존메뉴")
                        .menuUrl("/old")
                        .menuLevel(1)
                        .sortOrder(1)
                        .iconName("old")
                        .useYn("Y")
                        .build();

        menuMapper.insertMenu(insertDto, "SYSTEM");

        MenuRequestDto updateDto =
                MenuRequestDto.builder()
                        .menuName("수정메뉴")
                        .menuUrl("/new")
                        .menuLevel(2)
                        .sortOrder(2)
                        .iconName("new")
                        .useYn("N")
                        .build();

        int result =
                menuMapper.updateMenu(updateDto, menuId, "SYSTEM");

        assertThat(result).isEqualTo(1);

        MenuResponseDto updated =
                menuMapper.selectMenuDetail(menuId);

        assertThat(updated).isNotNull();

        assertThat(updated.getMenuName())
                .isEqualTo("수정메뉴");

        assertThat(updated.getUseYn())
                .isEqualTo("N");
    }

    @Test
    @DisplayName("메뉴 삭제")
    void deleteMenuTest() {

        Long menuId = 10003L;

        MenuRequestDto insertDto =
                MenuRequestDto.builder()
                        .menuId(menuId)
                        .menuName("삭제메뉴")
                        .menuUrl("/delete")
                        .menuLevel(1)
                        .sortOrder(1)
                        .iconName("delete")
                        .useYn("Y")
                        .build();

        menuMapper.insertMenu(insertDto, "SYSTEM");

        int result =
                menuMapper.deleteMenu(menuId);

        assertThat(result).isEqualTo(1);

        MenuResponseDto deleted =
                menuMapper.selectMenuDetail(menuId);

        assertThat(deleted).isNull();
    }
}