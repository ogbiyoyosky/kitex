package com.kitex.kitex.user;

import com.kitex.kitex.factory.EntityFactory;
import com.kitex.kitex.user.command.Command;
import com.kitex.kitex.user.command.CommandFactory;
import com.kitex.kitex.user.dto.NewUserDTO;
import com.kitex.kitex.user.entity.User;
import com.kitex.kitex.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EntityFactory entityFactory;
    private final StringUtils stringUtils;

    private final PasswordEncoder passwordEncoder;

    public User createUser(NewUserDTO user, String userRole) {

        User possibleNewUser = entityFactory.create(user, userRole);
        possibleNewUser.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(emailAlreadyInUse(possibleNewUser.getEmail()));
        if (emailAlreadyInUse(possibleNewUser.getEmail()))

        {
            return null;
        }

        return userRepository.save(possibleNewUser);
    }

    private boolean emailAlreadyInUse(String email)
    {
        return userRepository.findByEmail(email).isPresent();
    }
}
