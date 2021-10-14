package com.ttranz.ttgateadmin.services;


import com.ttranz.ttgateadmin.models.User;
import com.ttranz.ttgateadmin.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.findUserByUserName(s);

        if(user == null){
            throw new UsernameNotFoundException("Пользователь с именем " + s + " не найден !");
        }

        return user;

    }
}
