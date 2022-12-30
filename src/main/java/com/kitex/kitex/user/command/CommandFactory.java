package com.kitex.kitex.user.command;


import com.kitex.kitex.factory.EntityFactory;
import com.kitex.kitex.user.UserRepository;
import com.kitex.kitex.user.dto.NewUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.kitex.kitex.user.command.Command.CREATE_USER;

@Component
@RequiredArgsConstructor
public class CommandFactory
{
    private final UserRepository userRepository;
    private final EntityFactory entityFactory;


}
