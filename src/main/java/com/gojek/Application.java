package com.gojek;

import java.io.IOException;
import java.net.URISyntaxException;

import com.gojek.service.ParkingServiceImpl;

public class Application {
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		
		ParkingServiceImpl service = new ParkingServiceImpl();
		
		//start process
		service.begin();
	}
}
