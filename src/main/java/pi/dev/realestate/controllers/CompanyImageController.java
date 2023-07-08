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


    @PostMapping("/upload/{id}")
    public void uploadImage(@RequestParam("attachement")MultipartFile file, @PathVariable("id") int id) throws IOException{
        companyImageService.uploadImage(file, id);
    }

    @GetMapping("/{id}")
    public CompanyImage getImageById(@PathVariable("id") int id) {
        return companyImageService.getImageById(id);
    }


}
