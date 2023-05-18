package validations;

import java.time.LocalDate;
import java.util.Map;

import com.app.core.Color;
import com.app.core.Vehicle;

import exceptions.VehicleException;

public class VehicleValidations {
	
	public static Map<String,Vehicle> checkAllDetails(String chasis, String company, String color, double price, String mfgDate,Map<String,Vehicle> showroom) throws VehicleException
	{
		checkDuplicate(chasis,showroom);
		LocalDate validMfgDate=parseAndValidateDate(mfgDate);
		Color validColor=parseAndValidate(color);
		showroom.put(chasis, new Vehicle(chasis, company, validColor, price, validMfgDate));
		return showroom;
		
	}
	//duplication
	public static void checkDuplicate(String chasis,Map<String,Vehicle> showroom) throws VehicleException {
		if(showroom.containsKey(chasis))
			throw new VehicleException("no duplicates....");
		
	}
	//date parsing----later addition:check current year
	
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
