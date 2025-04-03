package ProfileService.service.impl;
import ProfileService.entity.CompanyProfile;
import ProfileService.repository.CompanyProfileRepository;
import ProfileService.service.CompanyProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class CompanyProfileImpl implements CompanyProfileService {

    @Autowired
    private CompanyProfileRepository repository;


    public CompanyProfile createProfile(CompanyProfile profile) {
        return repository.save(profile);
    }
    public List<CompanyProfile> getAllProfiles() {
        return repository.findAll();
    }
    public Optional<CompanyProfile> getProfileById(Long id) {
        return repository.findById(id);
    }
    public CompanyProfile updateProfile(Long id, CompanyProfile updatedProfile) {
        return repository.findById(id).map(profile -> {
            profile.setCompanyName(updatedProfile.getCompanyName());
            profile.setOwnerName(updatedProfile.getOwnerName());
            profile.setEmail(updatedProfile.getEmail());
            profile.setPhone(updatedProfile.getPhone());
            profile.setAddress(updatedProfile.getAddress());
            profile.setWebsite(updatedProfile.getWebsite());
            return repository.save(profile);
        }).orElse(null);
    }
    public void deleteProfile(Long id) {
        repository.deleteById(id);
    }
}
