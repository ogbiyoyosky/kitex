package com.kitex.kitex.profile.repository;

import com.kitex.kitex.profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile,Integer> {
    Optional<Profile> findByContactPhone(String contactPhone);
}
