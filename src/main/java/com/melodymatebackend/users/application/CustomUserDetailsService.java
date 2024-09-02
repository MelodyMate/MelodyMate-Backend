package com.melodymatebackend.users.application;

import com.melodymatebackend.users.domain.User;
import com.melodymatebackend.users.domain.UsersRepository;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = usersRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException(email));

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole().getKey());

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
            user.getPassword(), Collections.singletonList(grantedAuthority));
    }
}
