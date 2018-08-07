package com.sdet.dto;

import java.util.List;

import com.sdet.entities.*;
public class ReportDto
{
	Patient patient;
	List<Report> tests;
	String date;
	
	public ReportDto() 
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public ReportDto(Patient patient, List<Report> tests, String date) {
		super();
		this.patient = patient;
		this.tests = tests;
		this.date = date;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public List<Report> getTests() {
		return tests;
	}

	public void setTests(List<Report> tests) {
		this.tests = tests;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
