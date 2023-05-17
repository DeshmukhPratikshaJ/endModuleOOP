package com.tester;

import static utils.IOUtilsAL.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.core.Color;
import com.core.Vehicle;

import exception.VehicleException;

import static validations.VehicleValidations.validateAll;

public class TestingVehicleAL {

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in)){
			List<Vehicle> showroom=new ArrayList<>();
			System.out.println("enter filname");
			String filename=sc.next();
			showroom.add(new Vehicle("MH-20", "FORD", Color.BLACK, 85000, LocalDate.now()));
			showroom.add(new Vehicle("MH-25", "BMW", Color.RED, 85000, LocalDate.parse("2022-02-02")));
			showroom.add(new Vehicle("MH-22", "FERRARI", Color.GREY, 85000, LocalDate.parse("2021-02-02")));
			showroom.add(new Vehicle("MH-23", "TOYOTA", Color.WHITE, 85000, LocalDate.parse("2024-02-02")));
			store(showroom, filename);
			//showroom=restore(filename);
			
			System.out.println("colors of vehicle can be:");
			Arrays.asList(Color.values()).forEach(System.out::println);
			
			boolean exit=false;
			 System.out.println("MENU: 1.add vehicle 2.display vehicle \n"
			 		+ "3. search vehicle details based on chasis no\n"
			 		+ " 4.sort based on chasis 5.sort based on company and color 0.exit");
			 while(!exit) {
				 System.out.println("enter choice");
				 try {
				 switch(sc.nextInt()) {
				 
				 
					 case 1:     //add vehicle
						 System.out.println("enter vehicle details:chasis, company, Color, price, MfgDate(year-month-date)");
				         showroom=validateAll(sc.next(), sc.next(), sc.next(),sc.nextDouble(), sc.next(), showroom);
				         System.out.println("vehicle added");
				         
						 break;
						 
					 case 2:    //display all
						 System.out.println("----all vehicles in showroom--------");
						 restore(filename).forEach(System.out::println);
						 break;
						 
					 case 3:    //searching
						 System.out.println("enter chasis number");
						 Vehicle toSearch=new Vehicle(sc.next());
						 if(showroom.contains(toSearch))
							 System.out.println(toSearch);
						 else
							 throw new VehicleException("no such vehicle exist in the showroom");
						 break;
						 
					 case 4://sorting by chasis
						 
						Collections.sort(showroom);            //Vehicle must implement comparable
					    System.out.println("----sorting by chasis number---");
						showroom.forEach(System.out::println);
						 break;
						 
					 case 5://-----------SORTING BY COLOR--------------
						 
						 Collections.sort(showroom,new Comparator<Vehicle>() {
							@Override
							public int compare(Vehicle o1, Vehicle o2) {
								
								return o1.getColor().name().compareTo(o2.getColor().name());
							}
							 
						});
						 System.out.println("----sorting by color number---");
						 showroom.forEach(System.out::println);
						 break;
						 
					 case 0:
						 exit=true;
						 store(showroom, filename);
						 System.out.println("-----STORING THE DETAILS IN FILE BEFORE CLOSING APPLICATION--------");
						 break;
				 
				 }
				 }catch (Exception e) {
					 sc.nextLine();
						e.printStackTrace();
					}
			 }
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
