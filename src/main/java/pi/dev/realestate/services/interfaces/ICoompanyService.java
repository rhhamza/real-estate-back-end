package pi.dev.realestate.services.interfaces;

import org.springframework.web.multipart.MultipartFile;
import pi.dev.realestate.entities.Company;
import pi.dev.realestate.entities.StatusType;

import java.util.List;

import java.util.List;

public interface ICoompanyService {
    Company addCompany(Company company);

    List<Company> getAllCompanies();

    Company getCompany(int id);

    Company updateCompany(int id, Company updatedCompany);

    void deleteCompany(int id);
    //double calculateTotalRevenue(Integer companyId);

    List<Company> getCompaniesWithOrdersByStatus(StatusType status);
}
