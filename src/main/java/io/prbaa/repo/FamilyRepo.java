package io.prbaa.repo;

import io.prbaa.domain.Family;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FamilyRepo extends JpaRepository<Family,Long> {
    void deleteFamilyById(Long id);

    Optional<Family> findFamilyById(Long id);
}
