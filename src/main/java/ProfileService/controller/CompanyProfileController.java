package ProfileService.controller;
import ProfileService.entity.CompanyProfile;
import ProfileService.service.CompanyProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profile")
public class CompanyProfileController {

    @Autowired
    private CompanyProfileService service;

    @PostMapping
    public ResponseEntity<CompanyProfile> createProfile(@RequestBody CompanyProfile profile) {
        return ResponseEntity.ok(service.createProfile(profile));
    }

    @GetMapping
    public ResponseEntity<List<CompanyProfile>> getAllProfiles() {
        return ResponseEntity.ok(service.getAllProfiles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CompanyProfile>> getProfileById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProfileById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyProfile> updateProfile(@PathVariable Long id, @RequestBody CompanyProfile profile) {
        return ResponseEntity.ok(service.updateProfile(id, profile));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        service.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}
