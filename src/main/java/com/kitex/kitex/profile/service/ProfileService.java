package com.kitex.kitex.profile.service;

import com.kitex.kitex.profile.entity.Profile;
import com.kitex.kitex.profile.repository.ProfileRepository;
import com.kitex.kitex.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Component
public class ProfileService {
    private final ProfileRepository profileRepository;

    public Profile saveCustomer(Profile profile) {
    return this.profileRepository.save(profile);
   }
}
