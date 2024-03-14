package com.example.databaseIntegrationCRUD.demoCRUD_DB_Example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

	long patientId;
	String name;
	int age;
	String doctorName;
	
}
