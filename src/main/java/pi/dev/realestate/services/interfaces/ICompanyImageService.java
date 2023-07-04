package pi.dev.realestate.services.interfaces;

import org.springframework.web.multipart.MultipartFile;
import pi.dev.realestate.entities.CompanyImage;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ICompanyImageService {
    CompanyImage addCompanyImage(CompanyImage image);

    List<CompanyImage> getAllImages();

    CompanyImage getCompanyImage(int id);

    void deleteCompanyImage(int id);
    public CompanyImage uploadImage(MultipartFile file) throws IOException;
}
