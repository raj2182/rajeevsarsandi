package io.prbaa.service;

import io.prbaa.domain.Family;
import io.prbaa.exception.FamilyNotFoundException;
import io.prbaa.repo.FamilyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FamilyService {

    private final FamilyRepo familyRepo;

    public Family addFamily(Family family) {
        family.setFamilyCode(UUID.randomUUID().toString());
        return familyRepo.save(family);
    }

    public List<Family> findAllFamily() {
        return familyRepo.findAll();

    }

    public Family updateFamily(Family family) {
        ;
        return familyRepo.save(family);
    }

    public Family findFamilyById(Long id) throws Throwable {
        return familyRepo.findFamilyById(id).orElseThrow(() ->
                new FamilyNotFoundException("Family by id " + id + "was not found"));
    }

    public void deleteFamily(Long id) {
        familyRepo.deleteFamilyById(id);
    }

}
