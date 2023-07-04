package pi.dev.realestate.services.interfaces;
import org.springframework.web.multipart.MultipartFile;
import pi.dev.realestate.entities.Attachment;
import java.io.IOException;

public interface IAttachmentService {
    Attachment uploadImage(MultipartFile file) throws IOException;

    byte[] downloadImage(String fileName);
}
