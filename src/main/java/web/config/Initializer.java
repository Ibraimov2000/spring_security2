package web.config;

import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;


import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

@Component
public class Initializer {
    private final UserService userService;
    private final RoleService roleService;

    public Initializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void init() {
        roleService.addRole(new Role("ADMIN"));
        roleService.addRole(new Role("USER"));

        User commonUser = new User();
        commonUser.setName("CommonUserName");
        commonUser.setLastName("CommonUserLastName");
        commonUser.setCity("CommonUserCity");
        commonUser.setPassword("CommonUserPassword");
        commonUser.setRoles(Set.of(roleService.getRoleByName("USER")));
        userService.addUser(commonUser);

        User uncommonUser = new User();
        uncommonUser.setName("UncommonUserName");
        uncommonUser.setLastName("UncommonUserLastName");
        uncommonUser.setCity("UncommonUserCity");
        uncommonUser.setPassword("UncommonUserPassword");
        uncommonUser.setRoles(Set.of(roleService.getRoleByName("ADMIN"), roleService.getRoleByName("USER")));
        userService.addUser(uncommonUser);

        User admin = new User();
        admin.setName("AdminName");
        admin.setLastName("AdminLastName");
        admin.setCity("AdminCity");
        admin.setPassword("AdminPassword");
        admin.setRoles(Set.of(roleService.getRoleByName("ADMIN")));
        userService.addUser(admin);

        // проверяем что юзеры и роли ушли в базу
        List<User> users = userService.getUsers();
        List<Role> roles = roleService.getRoles();
        System.out.println("==============================================");
        System.out.println(users);
        System.out.println("==============================================");
        System.out.println(roles);
        System.out.println("==============================================");
    }
}
