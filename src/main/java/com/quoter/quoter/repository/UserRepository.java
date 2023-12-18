package com.quoter.quoter.repository;


import com.quoter.quoter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsernameOrEmail(String username, String email);

    boolean existsByUsername(String username);

    boolean existsByemail(String email);

    User findByEmail(String email);
}
