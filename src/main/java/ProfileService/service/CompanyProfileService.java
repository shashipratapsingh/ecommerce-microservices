package ProfileService.service;
import ProfileService.entity.CompanyProfile;
import java.util.List;
import java.util.Optional;
public interface CompanyProfileService {
      CompanyProfile createProfile(CompanyProfile profile);
      List<CompanyProfile> getAllProfiles();
      Optional<CompanyProfile> getProfileById(Long id);
      CompanyProfile updateProfile(Long id, CompanyProfile updatedProfile);
      void deleteProfile(Long id);
}
