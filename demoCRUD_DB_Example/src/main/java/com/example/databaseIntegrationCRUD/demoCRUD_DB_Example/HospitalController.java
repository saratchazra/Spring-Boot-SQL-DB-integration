package com.example.databaseIntegrationCRUD.demoCRUD_DB_Example;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HospitalController {

	@Autowired 
	PatientService patientService;
	
	@PostMapping("/createPatient")
	public void createPatient(@RequestBody Patient patient) throws SQLException {
		
		if(patient != null) {
			patientService.createPatient(patient);
		}
	}
	
	@GetMapping("/getAllPatientDetails")
	public List<Patient> getPatientDetail() throws SQLException {
		
		return patientService.getAllPatientDetail();
	}
	
	@GetMapping("/getPatientById/{id}")
	public Patient getPatientDetail(@PathVariable("id") int id) throws SQLException {
		
		return patientService.getPatient(id);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public boolean deletePatient(@PathVariable("id") int id) throws SQLException {
		
		return patientService.deletePatientById(id);
	}
	
	@PatchMapping("/updateById")
	public boolean updatePatientById(@RequestParam("id") int id, @RequestBody Patient patient) throws SQLException {
		
		return patientService.updatePatient(id, patient);
	}
}
