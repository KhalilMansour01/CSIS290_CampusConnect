package com.example.campus_connect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.campus_connect.Entity.VerificationToken;
import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
    Optional<VerificationToken> findByUserId(String userId);

}