package com.gojek.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import com.gojek.model.Registration;

public interface ParkingService {
	
	public void begin()throws IOException, URISyntaxException;
	
	public void process();
	
	public void processReadFromTxt()throws IOException, URISyntaxException;
	
	public void createParkingLot(String input, ArrayList<Registration> registrationList);
	
	public void parkingProcess(String input, ArrayList<Registration> registrationList);
	
	public void leavingProcess(String input, ArrayList<Registration> registrationList);
	
	public void showStatus(ArrayList<Registration> registrationList);
	
	public void findRegNumberByColour(String input, ArrayList<Registration> registrationList);
	
	public void findSlotNumberByColour(String input, ArrayList<Registration> registrationList);
	
	public void findSlotNumberByRegNumber(String input, ArrayList<Registration> registrationList);
	
}
