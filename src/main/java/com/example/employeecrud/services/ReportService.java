package com.example.employeecrud.services;




import com.example.employeecrud.entities.Report;
import com.example.employeecrud.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    @Autowired
    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public void saveReport(Report report) {
        reportRepository.save(report);
    }

    public Iterable<Report> getAllReports() {
        return reportRepository.findAll();
    }
}

