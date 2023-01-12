package com.kitex.kitex.auth;

import com.kitex.kitex.exception.UnAuthorizedException;
import com.kitex.kitex.user.UserRepository;
import com.kitex.kitex.user.entity.SecurityUser;
import com.kitex.kitex.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.*;


@RequiredArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService
{
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {

        Optional<User> user = userRepository.findByEmail(email);

        System.out.println(user);
        if(user.isEmpty()) {
             throw new UnAuthorizedException("Account not found");
        }

        List<String> roles = new ArrayList<>();

        roles.add(user.get().getRole());

        Set<GrantedAuthority> gA = new HashSet<>();

        for (String role : roles ) {
            gA.add(new SimpleGrantedAuthority(role));
        }

        return new org.springframework.security.core.userdetails.User(email,user.get().getPassword(),gA);

    }
}
