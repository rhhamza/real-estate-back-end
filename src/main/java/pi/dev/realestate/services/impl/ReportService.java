package pi.dev.realestate.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import pi.dev.realestate.entities.Report;
import pi.dev.realestate.entities.UserEntity;
import pi.dev.realestate.repositories.ReportRepository;
import pi.dev.realestate.repositories.UserRepository;
import pi.dev.realestate.services.interfaces.IReportService;


import java.util.List;

@Service
@AllArgsConstructor
public class ReportService implements IReportService {

    ReportRepository reportRepository;

    UserRepository userRepository;


    @Override
    public Report addReport(Report report, int userId) throws ChangeSetPersister.NotFoundException {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        report.setUserEntity(userEntity);
        reportRepository.save(report);
        return report;
    }


    @Override
    public List<Report> getAllReport(){
        return reportRepository.findAll();
    }
    @Override
    public Report getReportbyId(int id){
        return reportRepository.findById(id).orElse(null);
    }
}
