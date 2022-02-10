package com.MaxiFischer.SocialMediaAPI.jpa;

import com.MaxiFischer.SocialMediaAPI.model.Liked;
import com.MaxiFischer.SocialMediaAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikedRepo extends JpaRepository<Liked, Integer> {

    Optional<Liked> findByUserid(Integer id);
}
