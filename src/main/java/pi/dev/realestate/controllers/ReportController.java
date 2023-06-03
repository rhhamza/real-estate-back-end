package pi.dev.realestate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;
import pi.dev.realestate.entities.Report;
import pi.dev.realestate.services.interfaces.IReportService;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    IReportService iReportService;

    @PostMapping("/add/{ID}")
    public Report addReport(@RequestBody Report report, @PathVariable("ID") int userId) throws ChangeSetPersister.NotFoundException {
        return iReportService.addReport(report,userId);
    }

}
