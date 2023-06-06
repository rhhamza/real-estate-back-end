package pi.dev.realestate.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.dev.realestate.entities.Company;
import pi.dev.realestate.repositories.CompanyRepository;
import pi.dev.realestate.services.interfaces.ICoompanyService;

import java.sql.Timestamp;
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

    @Override
    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }
    @Override
    public Company getCompany(int id){
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public Company updateCompany(int id, Company updatedCompany) {
        Company existingCompany = companyRepository.findById(id).orElse(null);
        if (existingCompany != null) {
            existingCompany.setName(updatedCompany.getName());
            existingCompany.setAddress(updatedCompany.getAddress());
            existingCompany.setPhone(updatedCompany.getPhone());
            existingCompany.setEmail(updatedCompany.getEmail());
            existingCompany.setLogo(updatedCompany.getLogo());
            existingCompany.setStatus(updatedCompany.getStatus());
            existingCompany.setDescription(updatedCompany.getDescription());
            existingCompany.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            companyRepository.save(existingCompany);

        }
        return existingCompany;
    }

    @Override
    public void deleteCompany(int id) {
        companyRepository.deleteById(id);
    }
}
