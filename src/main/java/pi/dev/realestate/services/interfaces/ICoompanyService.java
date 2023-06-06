package pi.dev.realestate.services.interfaces;

import pi.dev.realestate.entities.Company;

import java.util.List;

public interface ICoompanyService {
    Company addCompany(Company company);

    List<Company> getAllCompanies();

    Company getCompany(int id);

    Company updateCompany(int id, Company updatedCompany);

    void deleteCompany(int id);
}
