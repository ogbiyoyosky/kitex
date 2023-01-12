package com.kitex.kitex.user;

import com.kitex.kitex.profile.dto.NewProfileDto;
import com.kitex.kitex.profile.entity.Profile;
import com.kitex.kitex.profile.repository.ProfileRepository;
import com.kitex.kitex.profile.service.ProfileService;
import com.kitex.kitex.exception.ConflictException;
import com.kitex.kitex.exception.NotFoundException;
import com.kitex.kitex.factory.EntityFactory;
import com.kitex.kitex.user.dto.NewUserDTO;
import com.kitex.kitex.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements  IUserService {

    private final UserRepository userRepository;
    private final EntityFactory entityFactory;
    @Autowired
    ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    public User saveUser(NewUserDTO user,String userRole){
        if (emailAlreadyInUse(user.getEmail()))
        {
            throw new ConflictException("Email Already in use");
        }

        Optional<Profile> existingProfile =  profileRepository.findByContactPhone(user.getPhone());

        if (existingProfile.isPresent())
        {
            throw new ConflictException("Phone Already in use");
        }
        Profile savedProfile = profileRepository.save(entityFactory.create(NewProfileDto.builder()
                .address(user.getAddress())
                .phone(user.getPhone())
                .build()));

        return userRepository.save(entityFactory.create(user, userRole, savedProfile));

    }

    public User createUser(NewUserDTO user, String userRole) {


        if (emailAlreadyInUse(user.getEmail()))
        {
            throw new ConflictException("Email Already in use");
        }
        Optional<Profile> profile =  profileRepository.findByContactPhone(user.getPhone());
        if (profile.isPresent())
        {
            throw new ConflictException("Phone Already in use");
        }


        Profile savedProfile = profileRepository.save(entityFactory.create(NewProfileDto.builder()
                .phone(user.getPhone())
                .address(user.getAddress())
                .build()));

        User possibleNewUser = entityFactory.create(user, userRole, savedProfile);
        possibleNewUser.setPassword(passwordEncoder.encode(user.getPassword()));
        possibleNewUser.setProfile(savedProfile);

        User newUser = userRepository.save(possibleNewUser);

        return newUser;
    }

    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        if (!user.isPresent()) {
            throw new NotFoundException("User does not exist");
        }
        return user.get();
    }

    private boolean emailAlreadyInUse(String email)
    {
        return userRepository.findByEmail(email).isPresent();
    }
}
