package com.kitex.kitex.factory;

import com.kitex.kitex.customer.entity.Customer;
import com.kitex.kitex.user.entity.User;
import com.kitex.kitex.user.dto.NewUserDTO;
import org.springframework.stereotype.Component;

@Component
public class EntityFactory
{
    public User create(NewUserDTO user, String role )
    {
        return new User(
                0,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                role,
                null
        );
    }
}
