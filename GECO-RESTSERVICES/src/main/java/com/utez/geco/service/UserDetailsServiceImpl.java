package com.utez.geco.service;

import com.utez.geco.model.User;
import com.utez.geco.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository ur;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = ur.findByEmail(email);
        return new UserDetailsImpl(user);
    }
//@Override
//public UserDetailsService userDetailsService(){
//    return new UserDetailsService() {
//        @Override
//        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//            return userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not found")) ;
//        }
//    };
//}
}
