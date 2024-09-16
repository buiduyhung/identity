package com.devtia.identity.repository;

import com.devtia.identity.entity.User;
import org.hibernate.query.criteria.JpaInPredicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
