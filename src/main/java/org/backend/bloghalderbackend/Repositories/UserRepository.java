package org.backend.bloghalderbackend.Repositories;

import java.util.Optional;

import org.backend.bloghalderbackend.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
    boolean existsUserByEmail(String mail);
    void removeUserByEmail(String email);
}
