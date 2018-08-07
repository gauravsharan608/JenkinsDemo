package com.sdet.service;

import com.sdet.dao.*;
import com.sdet.dto.ReportDto;
import com.sdet.entities.Patient;
import com.sdet.entities.Report;
import com.sdet.entities.Test;
import com.sdet.exceptions.DAOException;
import com.sdet.exceptions.ServiceException;
import java.util.ArrayList;
/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 * ***********************SERVICE LAYER ****************** * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
public class DiagnosticServiceImplementations implements DiagnosticService 
{

	DiagnosticDao daoObject;
	/* * * * * * * *
	 * Constructor * 
	 * * * * * * * */
	public DiagnosticServiceImplementations() 
	{
		super();
		daoObject=new DiagnosticDaoImplementations();
	}
	/** * * * * * * * * * * * * * * * * * * * * * *
	 * Function to add test to database by email * 
	 * * * * * * * * * * * * * * * * * * * * * * */
	@Override
	public void addDiagnosticTestsByEmail(String email,String testName) throws ServiceException
	{
		try 
		{
			DiagnosticDaoImplementations obj=new DiagnosticDaoImplementations();
			addTests(obj.fetchByEmail(email),testName);
		}catch(DAOException e)
		{
			throw new ServiceException("",e);
		}
	}

	/** * * * * * * * * * * * * * * * * * * * * * *
	 * Function to add test to database by phone * 
	 * * * * * * * * * * * * * * * * * * * * * * */
	@Override
	public void addDiagnosticTestsByPhone(long number,String testName) throws ServiceException
	{
		try
		{
			DiagnosticDaoImplementations obj=new DiagnosticDaoImplementations();
			addTests(obj.fetchByPhone(number),testName);
		}catch(DAOException e)
		{
			throw new ServiceException("",e);
		}
	}
	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * Function to generate test report by email from database * 
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	@Override
	public ReportDto generateReportsByEmail(String email,String date) throws ServiceException 
	{
		ReportDto reportDtoObj=new ReportDto();
		try {
			DiagnosticDaoImplementations obj=new DiagnosticDaoImplementations();
			ArrayList<Report> r=new ArrayList<Report>();
			Patient p1=obj.fetchByEmail(email);
			r=getReports(p1,date);
			reportDtoObj.setPatient(p1);
			reportDtoObj.setTests(r);
			reportDtoObj.setDate(date);
		}catch(DAOException e)
		{
			throw new ServiceException("",e);
		}
		return reportDtoObj;
	}
	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * Function to generate test report by phone from database * 
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	@Override
	public ReportDto generateReportsByPhone(long phone,String date) throws ServiceException 
	{
		ReportDto reportDtoObj=new ReportDto();
		try {
			DiagnosticDaoImplementations obj=new DiagnosticDaoImplementations();
			ArrayList<Report> r=new ArrayList<Report>();
			Patient p1=obj.fetchByPhone(phone);
			r=getReports(p1,date);
			reportDtoObj.setPatient(p1);
			reportDtoObj.setTests(r);
			reportDtoObj.setDate(date);
		}catch(DAOException e)
		{
			throw new ServiceException("",e);
		}
		return reportDtoObj;
	}
	/** * * * * * * * * * * * *
	 * Function to add tests * 
	 * * * * * * * * * * * * */
	public void addTests(Patient p,String testName) throws ServiceException
	{	
		try {
			DiagnosticDaoImplementations obj=new DiagnosticDaoImplementations();
			Test t=obj.fetchByTest(testName);
			obj.putPatientTests(p.getpId(),t.gettId());
		}catch(DAOException e)
		{
			throw new ServiceException("",e);
		}
	}
	/** * * * * * * * * * * * * * * * * * *
	 * Function to fetch list of reports * 
	 * * * * * * * * * * * * * * * * * * */
	public ArrayList<Report> getReports(Patient p,String date) throws ServiceException
	{
		ArrayList<Report> report=new ArrayList<Report>();

		DiagnosticDaoImplementations obj=new DiagnosticDaoImplementations();
		try{
			report=obj.fetchCost(p.getpId(),date);
		}catch(DAOException e)
		{
			throw new ServiceException("",e);
		}
		return report;
	}
}


