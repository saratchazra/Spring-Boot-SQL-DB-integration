package com.example.databaseIntegrationCRUD.demoCRUD_DB_Example;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

	@Autowired
	PatientRepos patientRepos;
	
	public void createPatient(Patient patient) throws SQLException {
		
		if(patient.getAge() > 0) {
			patientRepos.insertPatient(patient);
		}
	}

	public List<Patient> getAllPatientDetail() throws SQLException {
		
		return patientRepos.getAllPatient();
		
	}

	public Patient getPatient(int id) throws SQLException {
		
		return patientRepos.getPatientById(id);
	}

	public boolean deletePatientById(int id)  throws SQLException{
		
		return patientRepos.deletePatById(id);
	}

	public boolean updatePatient(int id, Patient patient) throws SQLException {
		
		return patientRepos.updatePatientById(id, patient);
	}
}
