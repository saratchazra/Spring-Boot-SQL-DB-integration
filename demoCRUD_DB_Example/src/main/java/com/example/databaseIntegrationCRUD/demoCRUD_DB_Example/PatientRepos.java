package com.example.databaseIntegrationCRUD.demoCRUD_DB_Example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class PatientRepos {
	
	Logger logger = LoggerFactory.getLogger(PatientRepos.class);
	
	private static Connection con;
	
	public PatientRepos() throws SQLException {
		
		logger.info("Inside PatientRepos constructor");
		
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_sys", "root", "");
		
		createTable();
	}
	
	public void createTable() throws SQLException {
		
		logger.info("Inside createTable method");
		String sqlQuery = "create table if not exists patient_jdbl61(id int primary key auto_increment, name varchar(50),age int,doctorName varchar(50))";
				
		Statement stmnt = con.createStatement();
		boolean val = stmnt.execute(sqlQuery);
		
		logger.info("Table create query val " + val);
	}

	public void insertPatient(Patient patient) throws SQLException {
		
		logger.info("Inside insertPatient ");
		
		String sql = "insert into patient_jdbl61(name,age,doctorName) values(?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, patient.getName());
		ps.setInt(2, patient.getAge());
		ps.setString(3, patient.getDoctorName());
		
		ps.executeUpdate();
	}

	public List<Patient> getAllPatient() throws SQLException {
		
		String sql = "select * from patient_jdbl61";
		
		List<Patient> list = new ArrayList<>();
		Statement stmnt = con.createStatement();
		ResultSet rs = stmnt.executeQuery(sql);
		
		while(rs.next()) {
			long pId = rs.getLong("id");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			String doctorName = rs.getString("doctorName");
			
			Patient patient = new Patient(pId, name, age, doctorName);
			
			list.add(patient);
		}
		
		return list;
	}

	public Patient getPatientById(int ptId) throws SQLException {
		
		String sql = "select * from patient_jdbl61 where id="+ptId;
		Statement stmnt = con.createStatement();
		ResultSet rs = stmnt.executeQuery(sql);
		
		if(rs.next()) {
			long pId = rs.getLong("id");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			String doctorName = rs.getString("doctorName");
		
			Patient patient = new Patient(pId, name, age, doctorName);
			
			return patient;
		}
		
		return null;
	}

	public boolean deletePatById(int ptId) throws SQLException {
		
		String sql = "DELETE FROM patient_jdbl61 WHERE id="+ptId;
		
		Statement stmnt = con.createStatement();
		int rs = stmnt.executeUpdate(sql);
		if(rs>0) {
			return true;
		}
		return false;
	}

	public boolean updatePatientById(int ptId, Patient patient) throws SQLException {
		
		//String sql = "UPDATE patient_jdbl61 SET name = "+ (String)(patient.getName())  +  ",doctorName = "+(String)(patient.getDoctorName()) + " WHERE id="+ptId;
		
		String sql = "UPDATE patient_jdbl61 SET name = 'TK',age = 30, doctorName = 'S D Hooda' WHERE id = "+ptId;
		
		Statement stmnt = con.createStatement();
		int rs = stmnt.executeUpdate(sql);
		if(rs>0) {
			return true;
		}
		return false;
	}
}

// DDL ----> Data Definition Language  -> create, alter, drop, show, use
// DML ----> Data Manipulation Language  -> update, select, insert, delete

// There are mainly three ways
// 1. execute  ---> returns true if result is set else false --> use with select queries
// 2. executeUpdate ----> returns number of affected rows, this can be used with non-select query
//  						e.g.- insert, update 
// 3. executeQuery ----> returns the actual result set. can be used with select queries.
