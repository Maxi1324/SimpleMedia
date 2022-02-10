package com.MaxiFischer.SocialMediaAPI.jpa;

import com.MaxiFischer.SocialMediaAPI.model.AuthentificationRequest;
import com.MaxiFischer.SocialMediaAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthentificationRequestRepository extends JpaRepository<AuthentificationRequest, Integer > {
    Optional<AuthentificationRequest> findByUserID(Integer id);
}
