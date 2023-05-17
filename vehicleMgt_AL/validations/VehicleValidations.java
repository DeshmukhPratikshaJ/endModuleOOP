package validations;

import java.time.LocalDate;
import java.util.List;

import com.core.Color;
import com.core.Vehicle;

import exception.VehicleException;

public class VehicleValidations {
	
	public static List<Vehicle> validateAll(String chasis, String company, String color, double price, String mfgDate,List<Vehicle> showroom) throws VehicleException{
		checkDuplicate(chasis,showroom);
		LocalDate validMfgDate=parseAndValidateDate(mfgDate);
		Color validColor=parseAndValidate(color);
		//--since everything alright
		showroom.add(new Vehicle(chasis, company, validColor, price, validMfgDate));
		return showroom;
		
	}
	
	
	//no duplicates
	public static void checkDuplicate(String chasis,List<Vehicle> showroom) throws VehicleException {
		if(showroom.contains(new Vehicle(chasis)))
			throw new VehicleException("no duplicates allowed");
	}
	//date parsing----addition current year
	
	public static LocalDate parseAndValidateDate(String date) {
		LocalDate validMfgDate=LocalDate.parse(date);
		return validMfgDate;	
	}
	//color checking
	
	public static Color parseAndValidate(String color) {
		Color validColor=Color.valueOf(color.toUpperCase());
		return validColor;
		
	}
}
