package pi.dev.realestate.services.interfaces;


import org.springframework.data.crossstore.ChangeSetPersister;
import pi.dev.realestate.entities.Report;

import java.util.List;

public interface IReportService {


    Report addReport(Report report, int userId) throws ChangeSetPersister.NotFoundException;

    List<Report> getAllReport();

    Report getReportbyId(int id);
}
