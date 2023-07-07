package pi.dev.realestate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pi.dev.realestate.entities.CompanyImage;
import pi.dev.realestate.services.impl.CompanyImageService;
import pi.dev.realestate.services.interfaces.ICompanyImageService;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("company-images")
public class CompanyImageController {

    private ICompanyImageService companyImageService;

    @Autowired
    public CompanyImageController(ICompanyImageService companyImageService) {
        this.companyImageService = companyImageService;
    }

    @PostMapping(value = "upload/{coId}")
    public ResponseEntity<CompanyImage> uploadImage(
            @RequestParam("name") String name,
            @RequestParam("type") String type,
            @RequestParam("file") MultipartFile file,
            @PathVariable("coId") int companyId
            ) {
        try {
            CompanyImage uploadedImage = companyImageService.uploadImage(name, type, file, companyId);
            return ResponseEntity.ok(uploadedImage);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable Long id) {
        Optional<CompanyImage> imageOptional = companyImageService.getImageById(id);
        if (imageOptional.isPresent()) {
            CompanyImage image = imageOptional.get();
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + image.getName() + "\"")
                    .body(image.getPicByte());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImageById(@PathVariable Long id) {
        Optional<CompanyImage> imageOptional = companyImageService.getImageById(id);
        if (imageOptional.isPresent()) {
            companyImageService.deleteImageById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
