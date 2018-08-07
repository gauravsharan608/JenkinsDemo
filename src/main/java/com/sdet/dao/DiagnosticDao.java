package com.sdet.dao;

import java.util.ArrayList;

import com.sdet.entities.Patient;
import com.sdet.entities.Report;
import com.sdet.entities.Test;
import com.sdet.exceptions.DAOException;
public interface DiagnosticDao 
{
	Patient fetchByEmail(String email) throws DAOException;
	Patient fetchByPhone(long pNumber) throws DAOException;
	Test fetchByTest(String tName) throws DAOException;
	void putPatientTests(int pId,int tId) throws DAOException;
	ArrayList<Report> fetchCost(int pId,String date) throws DAOException;
}
