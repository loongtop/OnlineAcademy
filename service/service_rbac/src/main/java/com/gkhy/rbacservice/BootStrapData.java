package com.gkhy.rbacservice;

import com.gkhy.rbacservice.service.*;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class BootStrapData implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(BootStrapData.class);

    private UserService userService;
    private UserDetailsService userDetailsService;
    private UserMembershipService userMembershipService;
    private RoleService roleService;
    private PermissionService permissionService;
    private ResourceService resourceService;
    private ActionService actionService;
    private GroupService groupService;
    private GroupDetailsService groupDetailsService;

    public BootStrapData(UserService userService, UserDetailsService userDetailsService, UserMembershipService userMembershipService, RoleService roleService, PermissionService permissionService, ResourceService resourceService, ActionService actionService, GroupService groupService, GroupDetailsService groupDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.userMembershipService = userMembershipService;
        this.roleService = roleService;
        this.permissionService = permissionService;
        this.resourceService = resourceService;
        this.actionService = actionService;
        this.groupService = groupService;
        this.groupDetailsService = groupDetailsService;
    }

    @SneakyThrows
    @Override
    public void run(String... args) {

        System.out.println("-----BootStrapData--run-----------");

        String name = "resource";

        String actionName = "action";
//        for (int i = 1; i < 6; i++) {

//            Set<Action> actions = new HashSet<>();
//            for (int j = 1; j < 3; j++) {
//                Action action = new Action();
//                action.setLevel(Level.BEGINNER);
//                action.setName(actionName + j + "-" + name + i);
//
//                Thread.sleep(500);
//                actions.add(action);
//            }
//
//            Resource resource = new Resource();
//            resource.setName(name + i);
//            resource.setLevel(Level.BEGINNER);
//
//            for (Action a : actions) {
//                resource.getActions().add(a);
//                a.setResource(resource);
//            }
//
//            resource = resourceService.save(resource);
//
//            for (Action a : actions) {
//                a = actionService.save(a);
//            }
//            actions.clear();
//        }
//
//
//        List<Resource> resources = resourceService.findAll();
//        String privilegeName = "p";
//
//        int times = 0;
//        for (int i = 0; i < resources.size(); i++) {
//            Set<Action> actions = resources.get(i).getActions();
//
//            for (Action a : actions) {
//                times = times + 1;
//                Privilege privilege = new Privilege();
//                privilege.setName(privilegeName + i + times + "=>" + a.getName());
//                privilege.setPrivilegeId(com.gkhy.commonutils.uuid.UUID.generate());
//                TimeUnit.SECONDS.sleep(1);
//                privilegeService.save(privilege);
//            }
//        }


//        String permissionName = "permission";
//
//        for (int i = 1; i < 9; i++) {
//
//            Privilege privilege = privilegeService.findById(Long.valueOf(i)).get();
//
//            Permission permission = new Permission();
//            UUID uuid = privilege.getPrivilegeId();
//            permission.setPrivilegeId(uuid);
//            permission.setName(permissionName + i);
//
//            permissionService.save(permission);
//            privilege.setPermission(permission);
//            privilegeService.save(privilege);
//        }
//
//        String roleName = "Role";
//        List<Permission> permissions = permissionService.findAll();
//        for (int i = 0; i < 5; i++) {
//            Role role = new Role();
//            role.setName(roleName + i);

//            role = roleService.findById(Long.valueOf(i+1)).get();
//            Permission permission1 = permissions.get(i);
//
//            role.getPermissions().add(permission1);
//            permission1.getRoles().add(role);
//
//            roleService.save(role);
//            permissionService.save(permission1);
//        }
//
//
//        UserRbac user = new UserRbac();
//        UserDetails userDetails = new UserDetails();
//        UserMembership userMembership = new UserMembership();
//        name = "User";
//        String password = "123456";
//        Set<UserRbac> set1 = new HashSet<>();
//        Set<UserRbac> set2 = new HashSet<>();
//        Set<UserRbac> set3 = new HashSet<>();
//        Set<UserRbac> set4 = new HashSet<>();
//        Set<UserRbac> set5 = new HashSet<>();
//        for (int i = 1; i < 11; i++) {
//            user.setName(name + i);
//            user.setPassword(password);
//            user.setEmail(user.getName() + "@gmail.com");
//            user = userService.save(user);
//
//            if (i % 2 == 0) {
//                set4.add(user);
//            } else {
//                set5.add(user);
//            }
//
//            userDetails.setUser(user);
//            if (i % 2 == 0) {
//                userDetails.setGender(Gender.FEMALE);
//            }
//            userDetails.setGender(Gender.MALE);
//            userDetails = userDetailsService.save(userDetails);
//
//            userMembership.setUser(user);
//            userMembership.setPasswordAnswer("Leo" + i);
//            userMembership = userMembershipService.save(userMembership);
//
//
//            if (i < 3) {
//                set1.add(user);
//                if (i == 2) {
//                    role1.setLevel(Level.BEGINNER);
//                    role1.setName(roleName + i);
//                    roleService.save(role1);
//                }
//            }
//
//            if (i >= 3 && i < 6) {
//
//                set2.add(user);
//                if (i == 5) {
//                    role2.setLevel(Level.INTERMEDIATE);
//                    role2.setName(roleName + i);
//                    roleService.save(role1);
//                }
//            }
//            if (i >= 6) {
//                set3.add(user);
//                if (i == 9) {
//                    role3.setLevel(Level.ADVANCE);
//                    role3.setName(roleName + i);
//                    roleService.save(role1);
//                }
//            }
//
//        }
//
//        Permission permission = new Permission();
//        name = "permission";
//        Set<Privilege> list1 = privilegeService.findAll();
//        for (int i = 1; i < 3; i++) {
//
//            permission.setName(list1.get(i).getName());
//            permission.setPrivilegeId(list1.get(i).getPrivilegeId());
//            permission.setRoles(new HashSet<Role>(Collections.singleton(role1)));
//            permission = permissionService.save(permission);
//        }
//
//        for (int i = 4; i < 8; i++) {
//
//            permission.setName(list1.get(i).getName());
//            permission.setPrivilegeId(list1.get(i).getPrivilegeId());
//            permission.setRoles(new HashSet<Role>(Collections.singleton(role2)));
//            permission = permissionService.save(permission);
//        }
//        for (int i = 9; i < list1.size(); i++) {
//
//            permission.setName(list1.get(i).getName());
//            permission.setPrivilegeId(list1.get(i).getPrivilegeId());
//            permission.setRoles(new HashSet<Role>(Collections.singleton(role3)));
//            permission = permissionService.save(permission);
//        }
//
//        Group group = new Group();
//        GroupDetails groupDetails = new GroupDetails();
//        name = "group";
//        for (int i = 0; i < 2; i++) {
//            group.setName(name + i);
//            if (i == 0) {
//                group.setUsers(set4);
//            } else {
//                group.setUsers(set5);
//            }
//            group = groupService.save(group);
//
//            groupDetails.setGroup(group);
//            groupDetails.setDescription(name + i + "0");
//            groupDetails = groupDetailsService.save(groupDetails);
//        }
    }
}
