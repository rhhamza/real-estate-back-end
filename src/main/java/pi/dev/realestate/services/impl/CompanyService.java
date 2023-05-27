package pi.dev.realestate.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.dev.realestate.entities.Company;
import pi.dev.realestate.repositories.CompanyRepository;
import pi.dev.realestate.services.interfaces.ICoompanyService;

import java.util.List;

@Service
public class CompanyService implements ICoompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public Company addCompany(Company company){
        companyRepository.save(company);
        return (company);
    }

    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }
    public Company getCompany(int id){
        return companyRepository.findById(id).orElse(null);
    }
}
