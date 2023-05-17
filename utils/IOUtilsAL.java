package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.core.Vehicle;

public class IOUtilsAL {
	
	//java app---->OOS--->FOS---->bin file
	public static void store(List<Vehicle> showroom,String filename) {
		
		try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(filename))){
			
			out.writeObject(showroom);   //Vehicle must implement serializable
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//bin file------>FIS---->OIS---->java app
	@SuppressWarnings("unchecked")
	public static List<Vehicle> restore(String filename) {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
			return (List<Vehicle>)in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Vehicle>();    //returning empty collection if exception
		}
}
}