package com.sdet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.sdet.entities.Patient;
import com.sdet.entities.Report;
import com.sdet.entities.Test;
import com.sdet.exceptions.DAOException;
import com.sdet.util.DBUtil;
/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 * ***********************DAO LAYER ********************** * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
public class DiagnosticDaoImplementations implements DiagnosticDao 
{
	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * Function to fetch test report by email from database    * 
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	@Override
	public Patient fetchByEmail(String email) throws DAOException
	{
		Connection con = DBUtil.getConnection();
		Patient patientObject = null;
		try {

			String query = "select * from patient where email=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, email);
			ResultSet rs = preparedStmt.executeQuery();						
			if(rs.next())
			{			
				patientObject = new Patient(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
		} catch (SQLException e) 
		{
			throw new DAOException("Sorry cannot reach now.",e);
		}
		finally 
		{
			DBUtil.releaseResource(con);
		}
		return patientObject;
	}

	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * Function to fetch test report by phone from database    * 
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	@Override
	public Patient fetchByPhone(long pNumber) throws DAOException
	{
		Connection con = DBUtil.getConnection();
		Patient patientObject = null;
		try {

			String query = "select * from patient where phone=?;";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setLong(1, pNumber);
			ResultSet rs = preparedStmt.executeQuery();
			if(rs.next())
			{
				patientObject = new Patient(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
		} catch (SQLException e) 
		{
			throw new DAOException("Sorry cannot reach now.",e);
		}
		finally 
		{
			DBUtil.releaseResource(con);
		}
		return patientObject;
	}

	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * Function to fetch tests by test name from database       * 
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	@Override
	public Test fetchByTest(String tName) throws DAOException
	{
		Connection con = DBUtil.getConnection();
		Test testObject = null;
		try {

			String query = "select * from test where name=?;";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, tName);
			ResultSet rs = preparedStmt.executeQuery();
			if(rs.next())
			{
				testObject = new Test(rs.getInt(1), rs.getString(2), rs.getInt(3));
			}
		} catch (SQLException e) 
		{
			throw new DAOException("Sorry cannot reach now.",e);
		}
		finally 
		{
			DBUtil.releaseResource(con);
		}
		return testObject;
	}

	/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 *    Function to put tests report by email to database    * 
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	@Override
	public void putPatientTests(int pId, int tId) throws DAOException
	{
		Connection con = DBUtil.getConnection();
		try {
			String query = " insert into patient_test(P_ID,T_ID,DATE)" + " values (?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, pId);
			preparedStmt.setInt(2, tId);
			preparedStmt.setString(3, LocalDate.now().toString().trim());

			preparedStmt.execute();

			con.close();
		} catch (SQLException e) 
		{
			throw new DAOException("Sorry cannot reach now.",e);
		}
		finally 
		{
			DBUtil.releaseResource(con);
		}
	}

	/** * * * * * * * * * * * * * * * * * * * * * * * * 
	 *    Function to fetch test cost from database   * 
	 * * * * * * * * * * * * * * * * * * * * * * * * */
	@Override
	public ArrayList<Report> fetchCost(int pId, String date) throws DAOException
	{
		Connection con = DBUtil.getConnection();
		ArrayList<Report> r = new ArrayList<Report>();
		Report report = null;
		try {

			String query = "select test.name,test.cost from test inner join patient_test on test.t_id = patient_test.t_id where patient_test.date='"
					+ date + "' and patient_test.p_id=" + pId;

			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) 
			{
				report = new Report(rs.getString(1), rs.getInt(2));
				r.add(report);
			}
			con.close();
		} catch (SQLException e) 
		{
			throw new DAOException("Sorry cannot reach now.",e);
		}
		finally 
		{
			DBUtil.releaseResource(con);
		}
		return r;
	}
}
