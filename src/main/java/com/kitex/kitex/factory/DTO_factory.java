package com.kitex.kitex.factory;

import com.kitex.kitex.user.dto.UserDTO;
import com.kitex.kitex.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class DTO_factory {
    public UserDTO create(User user)
    {

        if(user == null)
        {
            return null;
        }

        UserDTO userDto = new UserDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                user.getCustomer()

        );

        return userDto;
    }
}
