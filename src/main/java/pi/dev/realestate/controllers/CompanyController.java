package pi.dev.realestate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pi.dev.realestate.entities.Company;
import pi.dev.realestate.services.interfaces.ICoompanyService;


import java.util.List;

@RestController
@RequestMapping("/order")
public class CompanyController {

    @Autowired
    ICoompanyService iCompanyService;

    @PostMapping("/add")
    public ResponseEntity<Object> addCompany(@RequestBody Company company) {
        Company addedCompany = iCompanyService.addCompany(company);
        return new ResponseEntity<>(addedCompany, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllCompanies() {
        List<Company> companies = iCompanyService.getAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
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
