package pi.dev.realestate.services.impl;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pi.dev.realestate.entities.Company;
import pi.dev.realestate.entities.CompanyImage;
import pi.dev.realestate.entities.StatusType;
import pi.dev.realestate.repositories.CompanyImageRepository;
import pi.dev.realestate.services.interfaces.ICompanyImageService;

import javax.management.relation.Role;
import java.io.IOException;
import java.util.List;

@Service
public class CompanyImageService  implements ICompanyImageService {

    @Autowired
    CompanyImageRepository companyImageRepository;

    Role role;
    Company company;


    @Override
    public CompanyImage addCompanyImage(CompanyImage image){
        companyImageRepository.save(image);
        return (image);
    }

    @Override
    public List<CompanyImage> getAllImages(){
        return companyImageRepository.findAll();
    }
    @Override
    public CompanyImage getCompanyImage(int id){
        return companyImageRepository.findById(id).orElse(null);
    }


    @Override
    public void deleteCompanyImage(int id) {
        companyImageRepository.deleteById(id);
    }


    public CompanyImage uploadImage(MultipartFile file) throws IOException {

        CompanyImage companyImage = new CompanyImage();
        companyImage.setName(file.getOriginalFilename());
        companyImage.setType(file.getContentType());
        companyImage.setPicByte(file.getBytes());
        return companyImage;


    }

}
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pi.dev.realestate.config.FilenameUtils;
import pi.dev.realestate.entities.Company;
import pi.dev.realestate.entities.CompanyImage;
import pi.dev.realestate.repositories.CompanyImageRepository;
import pi.dev.realestate.repositories.CompanyRepository;
import pi.dev.realestate.services.interfaces.ICompanyImageService;

import java.io.IOException;
import java.util.Optional;

@Service
@Transactional
public class CompanyImageService implements ICompanyImageService {

    private final CompanyImageRepository companyImageRepository;

    @Autowired
    CompanyRepository companyRepository;


    @Autowired
    public CompanyImageService(CompanyImageRepository companyImageRepository) {
        this.companyImageRepository = companyImageRepository;
    }

    @Override
    public CompanyImage uploadImage(MultipartFile file, int id) throws IOException {
        CompanyImage companyImage = new CompanyImage();
        companyImage.setName(file.getOriginalFilename());
        companyImage.setType(file.getContentType());
        companyImage.setPicByte(FilenameUtils.compressImage(file.getBytes()));
        Company company = companyRepository.findById(id).orElse(null);
        if (company != null) {
            companyImage.setCompany(company);
            companyImageRepository.save(companyImage);


        }
        return companyImage;
    }

    public CompanyImage getImageById(int id) {
        return companyImageRepository.findCompanyImageByCompanyId(id);
    }

    public void deleteImageById(Long id) {
        companyImageRepository.deleteById(id);
    }

}
