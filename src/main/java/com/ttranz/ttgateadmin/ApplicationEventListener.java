package com.ttranz.ttgateadmin;

import com.ttranz.ttgateadmin.models.Role;
import com.ttranz.ttgateadmin.models.User;
import com.ttranz.ttgateadmin.repo.UserRepository;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ApplicationEventListener {


    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public ApplicationEventListener(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    @EventListener
    public void handleEvent(ApplicationStartedEvent event) {
        //System.out.println("event: " + event);

        String password;

        if(userRepository.findUserByUserName("superAdmin") == null){
            Set<Role> superRoles = new HashSet<>();
            superRoles.add(Role.SUPER_ADMIN);
            superRoles.add(Role.ADMIN);
            superRoles.add(Role.OPERATOR);
            superRoles.add(Role.AGENT);

            password = passwordEncoder.encode("!@superPassw0rd@!");
            userRepository.save(new User("superAdmin", password, "Super Admin", superRoles, 0L, 0L));
        }

        if(userRepository.findUserByUserName("admin") == null){
            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(Role.ADMIN);
            adminRoles.add(Role.OPERATOR);
            adminRoles.add(Role.AGENT);

            password = passwordEncoder.encode("password");
            userRepository.save(new User("admin", password, "Admin", adminRoles, 0L, 0L));
        }


    }

}
