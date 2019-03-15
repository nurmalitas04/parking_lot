package com.gojek.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import com.gojek.model.Registration;
import com.gojek.model.SortBySlotNo;

public class ParkingServiceImpl implements ParkingService{
	
	private String input;
	Scanner scan = new Scanner(System.in);
	private int max;
	
	public void begin() throws IOException, URISyntaxException {
		
		do {
			//get input from user
			System.out.println("------ PARKING LOT -------");
			System.out.println("1. Input Manual");
			System.out.println("2. Read From TXT");
			System.out.println("3. Exit");
			System.out.print("Please choose: ");
			input = scan.nextLine();
			
			if(input.equals("1")) {
				//manual
				System.out.println("Type the command same as in pdf. (Example command: create_parking_lot 6 etc)");
				process();
			}else if(input.equals("2")) {
				//read from input text
				processReadFromTxt();
			}else if(input.equals("3")){
				System.out.println("Thank you");
				System.exit(0);
			}else {
				System.out.println("Please type a command correctly");
			}
			
		}while(input!="exit");
	}

	public void process() {
		ArrayList<Registration> registrationList = new ArrayList<Registration>();
		
		do {
			//get input from user
			System.out.println("");
			input = scan.nextLine();
			
			//read the command
			if(input.contains("create_parking_lot")) {
				//create parking lot
				createParkingLot(input,registrationList);
			}else if(input.contains("park")) {
				//parking
				parkingProcess(input,registrationList);
			}else if(input.contains("leave")) {
				//leaving
				leavingProcess(input,registrationList);
			}else if(input.contains("status")) {
				//status
				showStatus(registrationList);
			}else if(input.contains("registration_numbers_for_cars_with_colour")) {
				//find registration number by colour
				findRegNumberByColour(input,registrationList);
			}else if(input.contains("slot_numbers_for_cars_with_colour")) {
				//find slot number by colour
				findSlotNumberByColour(input,registrationList);
			}else if(input.contains("slot_number_for_registration_number")) {
				//find slot number by registration number
				findSlotNumberByRegNumber(input,registrationList);
			}else if(input.contains("exit") || input.equals("3")){
				System.out.println("Thank you");
				System.exit(0);
			}else {
				System.out.println("Please type a command correctly");
			}
		}while(input!="exit");
	}
	
	public void createParkingLot(String input, ArrayList<Registration> registrationList) {
		//read the input command
		String val[] = input.split(" ");
		registrationList.clear();
		max = Integer.parseInt(val[1]);
		for(int x=0; x<max; x++) {
			Registration reg = new Registration();
			reg.setSlotNo(x+1);
			registrationList.add(reg);
		}
		System.out.println("Created a parking lot with "+max+" slots");
	}
	
	public void parkingProcess(String input, ArrayList<Registration> registrationList) {
		//read the input command
		String val[] = input.split(" ");
		boolean full = true;
		
		//check the parking lot, is full?
		if(registrationList.size() <= max) {
			//set registration
			for(int x=0; x<max; x++) {
				Registration reg = registrationList.get(x);
				
				if(reg.getRegistrationNo() == null) {
					int slotNoTamp = reg.getSlotNo();
					registrationList.remove(x);
					
					reg.setSlotNo(slotNoTamp);
					reg.setRegistrationNo(val[1]);
					reg.setColour(val[2]);
					
					//add to list
					registrationList.add(reg);
					//show response
					System.out.println("Allocated slot number: "+reg.getSlotNo());
					full = false;
					break;
				}
			}
			if(full) {
				System.out.println("Sorry, parking lot is full");
			}
		}
	}
	
	public void leavingProcess(String input, ArrayList<Registration> registrationList) {
		//read the input command
		String val[] = input.split(" ");
		
		//remove with slot
		if(registrationList!=null) {
			
			for(int x=0; x<max; x++) {
				Registration reg = registrationList.get(x);
				
				if(reg.getSlotNo() == Integer.parseInt(val[1])) {
					int slotNoTamp = reg.getSlotNo();

					//remove
					registrationList.remove(x);
					
					reg = new Registration();
					reg.setSlotNo(slotNoTamp);
					
					//add to list
					registrationList.add(reg);
					break;
				}
			}
			
			System.out.println("Slot number "+val[1]+" is free");
		}else {
			System.out.println("Not found");
		}
	}
	
	public void showStatus(ArrayList<Registration> registrationList) {
		
		System.out.println("Slot No.	Registration No		Colour");
		Collections.sort(registrationList, new SortBySlotNo());
		for(Registration reg : registrationList) {
			if(reg.getRegistrationNo()!=null) 
				System.out.println(reg.getSlotNo()+".		"+reg.getRegistrationNo()+"		"+reg.getColour());
		}
	}
	
	public void findRegNumberByColour(String input, ArrayList<Registration> registrationList) {
		//read the input command
		String val[] = input.split(" ");
		String show="";
		
		Collections.sort(registrationList, new SortBySlotNo());
		for(Registration reg : registrationList) {
			if(reg.getColour()!=null && reg.getColour().equalsIgnoreCase(val[1])) {
				show += reg.getRegistrationNo()+",";
			}
		}
		
		if(!show.equals("")) {
			show = show.substring(0, show.length()-1);
			System.out.print(show);
		}else {
			System.out.println("Not found");
		}
		System.out.println("");
	}
	
	public void findSlotNumberByColour(String input, ArrayList<Registration> registrationList) {
		//read the input command
		String val[] = input.split(" ");
		String show="";
		
		Collections.sort(registrationList, new SortBySlotNo());
		for(Registration reg : registrationList) {
			if(reg.getColour()!=null && reg.getColour().equalsIgnoreCase(val[1])) {
				show += reg.getSlotNo()+",";
			}
		}
		
		if(!show.equals("")) {
			show = show.substring(0, show.length()-1);
			System.out.print(show);
		}else {
			System.out.println("Not found");
		}
		System.out.println("");
	}

	@Override
	public void findSlotNumberByRegNumber(String input, ArrayList<Registration> registrationList) {
		//read the input command
		String val[] = input.split(" ");
		String show="";
		
		Collections.sort(registrationList, new SortBySlotNo());
		for(Registration reg : registrationList) {
			if(reg.getRegistrationNo()!=null && reg.getRegistrationNo().equalsIgnoreCase(val[1])) {
				show += reg.getSlotNo()+",";
			}
		}
		
		if(!show.equals("")) {
			show = show.substring(0, show.length()-1);
			System.out.print(show);
		}else {
			System.out.println("Not found");
		}
		System.out.println("");
	}

	@Override
	public void processReadFromTxt() throws IOException, URISyntaxException {
		ArrayList<Registration> registrationList = new ArrayList<Registration>();

		ClassLoader classLoader = getClass().getClassLoader();
		InputStream is = classLoader.getResourceAsStream("file_input.txt");
		Scanner scanner = new Scanner(is);
		
		//read line by line
		while(scanner.hasNextLine()){
		    //process each line
		    String line = scanner.nextLine();
		    if(line.contains("create_parking_lot")) {
				//create parking lot
				createParkingLot(line,registrationList);
			}else if(line.contains("park")) {
				//parking
				parkingProcess(line,registrationList);
			}else if(line.contains("leave")) {
				//leaving
				leavingProcess(line,registrationList);
			}else if(line.contains("status")) {
				//status
				showStatus(registrationList);
			}else if(line.contains("registration_numbers_for_cars_with_colour")) {
				//find registration number by colour
				findRegNumberByColour(line,registrationList);
			}else if(line.contains("slot_numbers_for_cars_with_colour")) {
				//find slot number by colour
				findSlotNumberByColour(line,registrationList);
			}else if(line.contains("slot_number_for_registration_number")) {
				//find slot number by registration number
				findSlotNumberByRegNumber(line,registrationList);
			}else {
				System.out.println("Please type a command correctly");
			}
		}
		scanner.close();
	}
}
