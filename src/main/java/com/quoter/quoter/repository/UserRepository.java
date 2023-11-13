package com.quoter.quoter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.quoter.quoter.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);

    Optional<User> findById(Long id);

}
