package com.MaxiFischer.SocialMediaAPI.jpa;

import com.MaxiFischer.SocialMediaAPI.model.Beitrag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BeitragRepo extends JpaRepository<Beitrag, Integer> {
    Optional<Beitrag> findById(Integer id);

    Optional<Beitrag> findByUeberschrift(String ueberschrift);
}
