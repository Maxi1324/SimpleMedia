package com.MaxiFischer.SocialMediaAPI.jpa;

import com.MaxiFischer.SocialMediaAPI.model.AuthentificationRequest;
import com.MaxiFischer.SocialMediaAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByNutzername(String nutzername);

    Optional<User> findByMail(String mail);

    Optional<User> findById(Integer id);

}
