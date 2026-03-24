package com.zyj.hiddendanger.auth;

import com.zyj.hiddendanger.auth.service.RoleService;
import com.zyj.hiddendanger.auth.service.UserService;
import com.zyj.hiddendanger.core.context.UserIdContextHolder;
import com.zyj.hiddendanger.model.po.Role;
import com.zyj.hiddendanger.model.po.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestUserTable {
    @Resource
    private RoleService roleService;

    @Test
    public void insertRole() {
        UserIdContextHolder.set("0");
        Role role = new Role();
        role.setRoleName(Role.RoleEnum.ADMIN);
        System.out.println(roleService.save(role));
    }

    @Resource
    private UserService userService;

    @Test
    public void insertUser() {
        UserIdContextHolder.set("0");
        User user = new User();
        user.setAccount("admin")
            .setPassword("123")
            .setRealName("zyj")
            .setPhoneNumber("15347758353")
            .setDepartmentId("0")
            .setStatus(User.UserStatus.NORMAL)
            .setRoleId("2036343894605582338");

        System.out.println(userService.save(user));
    }
}
