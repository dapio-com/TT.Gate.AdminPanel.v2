package com.ttranz.ttgateadmin.controllers;


import com.ttranz.ttgateadmin.models.Role;
import com.ttranz.ttgateadmin.models.User;
import com.ttranz.ttgateadmin.repo.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserController(PasswordEncoder passwordEncoder, UserRepository userRepository) {

        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    @GetMapping("/user-panel")
    public String getUserPanel(Model model){

        List<User> users = userRepository.selectLastN();
        for (User user : users) {
            if(user.getUserOrgGroupId() > 0){
                user.setUserOrgGroupName(userRepository.searchOrgGroupName(user.getUserOrgGroupId()));
            }
            if(user.getUserOrgId() > 0){
                user.setUserOrgName(userRepository.searchOrgName(user.getUserOrgId()));
            }
        }

        model.addAttribute("users", users);

        return "blocks/user_block";
    }

    @PostMapping("/user-check")
    public ResponseEntity<Boolean> userCheck(@RequestParam String userName){
        return ResponseEntity.ok(userRepository.selectUserNameCheck(userName));
    }


    @PostMapping("/user-add")
    public String userAdd(@RequestParam String userName, String userPassword, String userRole, String userDescription, Long userOrgGroupId, Long userOrgId, Model model){

        Set<Role> roles = new HashSet<>();
        switch (userRole){
            case "ADMIN" : {
                roles.add(Role.ADMIN);
                roles.add(Role.OPERATOR);
                roles.add(Role.AGENT);
                break;
            }
            case "OPERATOR" : {
                roles.add(Role.OPERATOR);
                roles.add(Role.AGENT);
                break;
            }
            case "AGENT" : {
                roles.add(Role.AGENT);
                break;
            }
        }

        String password = passwordEncoder.encode(userPassword);
        userRepository.save(new User(userName, password, userDescription, roles, userOrgGroupId, userOrgId));

        List<User> users = userRepository.selectLastN();

        for (User user : users) {
            if(user.getUserOrgGroupId() > 0){
                user.setUserOrgGroupName(userRepository.searchOrgGroupName(user.getUserOrgGroupId()));
            }
            if(user.getUserOrgId() > 0){
                user.setUserOrgName(userRepository.searchOrgName(user.getUserOrgId()));
            }
        }

        model.addAttribute("users", users);
        return "results/users_added";
    }


    @PostMapping("/user-delete")
    public String userDelete(@RequestParam Long id, Model model) {
        User user = userRepository.findUserById(id);
        userRepository.delete(user);

        //return "results/ok";
        return getUserPanel(model);


    }

    @GetMapping("/user-search")
    public String userSearch(@RequestParam String searchFor, Model model) {
        List<User> users = userRepository.searchForUser(searchFor);

        model.addAttribute("users", users);

        return "results/users_added";

    }


    @GetMapping("/user-edit-form")
    public String getUserEditForm(Long id, Model model){
        if(!userRepository.existsById(id)){
            return "/";
        }

        User user = userRepository.findUserById(id);
        if(user.getUserOrgGroupId() > 0){
            user.setUserOrgGroupName(userRepository.searchOrgGroupName(user.getUserOrgGroupId()));
        }
        if(user.getUserOrgId() > 0){
            user.setUserOrgName(userRepository.searchOrgName(user.getUserOrgId()));
        }

        model.addAttribute("user", user);
        return "blocks/user_edit_form_block";
    }

    @PostMapping("/user-edit")
    public String userUpdate(@RequestParam Long id, @RequestParam String userName, @RequestParam boolean userStatus, @RequestParam String userPassword, @RequestParam String userDescription, Model model){
        User user = userRepository.findUserById(id);
        user.setUserName(userName);
        user.setUserStatus(userStatus);
        user.setUserDescription(userDescription);
        if(userPassword.length() > 0){
            String password = passwordEncoder.encode(userPassword);
            user.setUserPassword(password);
        }
        userRepository.save(user);

        //return "results/ok";
        return getUserPanel(model);
    }
}
