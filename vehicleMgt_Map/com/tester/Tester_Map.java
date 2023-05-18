package com.tester;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.Scanner;

import com.app.core.Color;
import com.app.core.Vehicle;

import exceptions.VehicleException;
import utils.IOUtils_Map;
import validations.VehicleValidations;
public class Tester_Map {

	public static void main(String[] args) {
		
		try(Scanner sc= new Scanner(System.in)){
			Map<String,Vehicle> showroom=new HashMap<>();
			System.out.println("enter filename");
			String filename=sc.next();
			showroom=IOUtils_Map.reStore(filename);
			
			//-------------storing some records------------
			List<Vehicle> showroomAL=new ArrayList<>();
			showroomAL.add(new Vehicle("MH-20", "FORD", Color.BLACK, 85000, LocalDate.now()));
			showroomAL.add(new Vehicle("MH-25", "BMW", Color.RED, 85000, LocalDate.parse("2022-02-02")));
			showroomAL.add(new Vehicle("MH-27", "FERRARI", Color.GREY, 85000, LocalDate.parse("2021-02-02")));
			showroomAL.add(new Vehicle("MH-23", "TOYOTA", Color.WHITE, 85000, LocalDate.parse("2024-02-02")));
			
			for(Vehicle v:showroomAL)
				showroom.putIfAbsent(v.getChasis(), v);
			
			System.out.println("colors available for vehicle:");
			Arrays.asList(Color.values()).forEach(System.out::println);
			
			 System.out.println("MENU: 1.add vehicle 2.display vehicle \n"
				 		+ "3.search vehicle details based on chasis no\n"
				 		+ " 4.sort based on chasis 5.sort based on company and color\n"
				 		+ "6.update status to not Available based on color"
				 		+ " 7.delete vehicle based on chasis no 0.exit");
			 
			boolean exit=false; 
			while(!exit)	
			try{
				System.out.println("enter choice");
			switch(sc.nextInt()) {
			
			case 1:   //add a vehicle
				System.out.println("enter vehicle details:chasis, company, Color, price, MfgDate(year-month-date)");
		         showroom=VehicleValidations.checkAllDetails(sc.next(), sc.next(), sc.next(),sc.nextDouble(), sc.next(), showroom);
		         System.out.println("vehicle added");
				break;
				
			case 2:   //display all vehicles
				System.out.println("---all vehicles-----");
				showroom.values()
				        .stream()
				        .forEach(System.out::println);
				
				break;
				
			case 3:  //search vehicle------chasis--PK
				System.out.println("enter chasis number");
				String toFind=sc.next();
				Vehicle found=showroom.get(toFind);
				if(found==null)
					throw new VehicleException("no such vehicle in the showroom");
				else
					System.out.println(found);
				break;
				
			case 4:  //sort based on chasis---key
				System.out.println("----sorting on chasis no----");
				showroom.values()
				        .stream()
				        .sorted()
				        .forEach(System.out::println);
				break;
				
			case 5:  //sort based on company and color
				System.out.println("----sorting based on company name and color");
				showroom.values()
				        .stream()
				        .sorted(new Comparator<Vehicle>() {

							@Override
							public int compare(Vehicle o1, Vehicle o2) {
								int isCompanySame=o1.getCompany().compareToIgnoreCase(o2.getCompany());
								if(isCompanySame==0)
									return o1.getColor().name().compareTo(o2.getColor().name());
								else
									return isCompanySame;
							}
						}).forEach(System.out::println);
				break;
				
			case 6:    //update-------mark vehicle unvailable----no PK based
				System.out.println("enter color to mark unavailable");
				Color chosencolor=Color.valueOf(sc.next().toUpperCase());
				showroom.values()
				        .stream()
				        .filter(v->v.getColor().equals(chosencolor))
				        .forEach(v->v.setAvailable(false));
				System.out.println("status of all vehicles");
				showroom.forEach((k,v)->System.out.println(v.getChasis()+" "
				                                          +v.getColor()+" "
						                                  +((v.isAvailable())? "available" :"unavailable")));
			break;
				
			case 7:    //deletion based on chasis no--key
				System.out.println("enter chasis number");
				String toDelete=sc.next();
				if(showroom.containsKey(toDelete)) {
				showroom.remove(toDelete);
				System.out.println("successfully deleted");
				}
				else
					throw new VehicleException("no such vehicle exists in the showroom");
				break;
				
			case 8:    //deletion based on company--not key
				System.out.println("color");
				String color=sc.next();
				Iterator<Vehicle> itr=showroom.values().iterator();
				while(itr.hasNext())
				{
					if(itr.next().getColor().name().equalsIgnoreCase(color))
						itr.remove();
				}
				System.out.println("after removing, we have following left:----");
					showroom.forEach((k,v)->System.out.println(v));
				break;
				
			case 0:   //exit
				exit=true;
				IOUtils_Map.store(filename, showroom);
				IOUtils_Map.storeInCharFile("show", showroom);
				System.out.println("storing all the data in file before closing application");
				break;
			
			}
			}catch (Exception e) {
				sc.nextLine();
				e.printStackTrace();
			}
			
		}

	}

}
