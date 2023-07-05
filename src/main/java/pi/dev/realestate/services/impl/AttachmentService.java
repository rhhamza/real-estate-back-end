package pi.dev.realestate.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pi.dev.realestate.config.FilenameUtils;
import pi.dev.realestate.entities.Attachment;
import pi.dev.realestate.repositories.AttachmentRepository;
import pi.dev.realestate.services.interfaces.IAttachmentService;

import java.io.IOException;
import java.util.Optional;

@Service
public class AttachmentService implements IAttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;
    @Override
    public Attachment uploadImage(MultipartFile file) throws IOException {
        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setType(file.getContentType());
        attachment.setData(FilenameUtils.compressImage(file.getBytes()));
        return attachmentRepository.save(attachment);
    }
    @Override
    public byte[] downloadImage(String fileName){
        Optional<Attachment> imageData = attachmentRepository.findByName(fileName);
        return FilenameUtils.decompressImage(imageData.get().getData());
    }


}
