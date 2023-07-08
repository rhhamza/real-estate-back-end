package pi.dev.realestate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pi.dev.realestate.entities.Company;
import pi.dev.realestate.entities.CompanyImage;
import pi.dev.realestate.repositories.CompanyRepository;
import pi.dev.realestate.services.interfaces.ICompanyImageService;
import pi.dev.realestate.services.interfaces.ICoompanyService;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("company")
public class CompanyController {

    @Autowired
    ICoompanyService iCompanyService;

    @Autowired
    ICompanyImageService iCompanyImageService;
    @Autowired
    CompanyRepository companyRepository;

    /*
    @PostMapping(value = "/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Company addCompany(@RequestPart("company") Company company,
                              @RequestPart("imageFile") MultipartFile file) {
        try {
            CompanyImage image = iCompanyImageService.uploadImage(file);
            company.setImage(image);
            return iCompanyService.addCompany(company);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

*/
    @PostMapping(value = "/add")
    public Company addCompany(@RequestBody Company company) {

            return iCompanyService.addCompany(company);

    }
    @GetMapping(value ="/all")
    public ResponseEntity<Object> getAllCompanies() {
        try {
            List<Company> companies = companyRepository.findAll();
            return new ResponseEntity<>(companies, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Companies not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCompanyById(@PathVariable int id) {
        Company company= iCompanyService.getCompany(id);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCompany (@PathVariable int id, @RequestBody Company updatedCompany) {
        Company company = iCompanyService.updateCompany(id, updatedCompany);
        if (company  != null) {
            return new ResponseEntity<>(company , HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCompany(@PathVariable int id) {
        iCompanyService.deleteCompany (id);
        return new ResponseEntity<>("Company deleted successfully", HttpStatus.OK);
    }

}
