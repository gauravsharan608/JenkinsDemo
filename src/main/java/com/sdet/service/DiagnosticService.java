package com.sdet.service;

import java.util.ArrayList;
import com.sdet.dto.ReportDto;
import com.sdet.entities.Patient;
import com.sdet.entities.Report;
import com.sdet.exceptions.ServiceException;

public interface DiagnosticService 
{
	void addTests(Patient p,String testName) throws ServiceException;
	public ArrayList<Report> getReports(Patient p,String date) throws ServiceException;
	ReportDto generateReportsByPhone(long phone, String date) throws ServiceException;
	ReportDto generateReportsByEmail(String email, String date) throws ServiceException;
	void addDiagnosticTestsByEmail(String email, String testName) throws ServiceException;
	void addDiagnosticTestsByPhone(long number, String testName) throws ServiceException;
}
