package com.baltika.dataservice.repository;

import com.baltika.dataservice.model.db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("select u from User u join Ally ally")
    Optional<User> findByAlly(String ally);
}
