package io.prbaa.resource;

import io.prbaa.domain.Family;
import io.prbaa.service.FamilyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/family")
@RequiredArgsConstructor
@Transactional
public class FamilyResource {

    private final FamilyService familyService;
    @GetMapping("/all")
    public ResponseEntity<List<Family>> findAllFamily() {
        List<Family> allFamily =familyService.findAllFamily();
        return new ResponseEntity<>(allFamily,HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Family> findAllFamily(@PathVariable("id") Long id) throws Throwable {

        return new ResponseEntity<>(familyService.findFamilyById(id),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Family> findAllFamily(@RequestBody Family family) {

        return new ResponseEntity<>(familyService.addFamily(family),HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFamily(@PathVariable("id") Long id) {
           familyService.deleteFamily(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Family> updateFamily(@RequestBody Family family) {

        return new ResponseEntity<>(familyService.updateFamily(family),HttpStatus.OK);
    }

}
