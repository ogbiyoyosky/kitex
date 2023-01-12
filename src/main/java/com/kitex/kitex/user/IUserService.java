package com.kitex.kitex.user;

import com.kitex.kitex.user.dto.NewUserDTO;
import com.kitex.kitex.user.entity.User;

public interface IUserService {
    User createUser(NewUserDTO user, String userRole);
    User findByEmail(String email);
}
