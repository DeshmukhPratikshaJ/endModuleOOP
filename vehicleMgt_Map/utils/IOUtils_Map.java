package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.app.core.Vehicle;

public class IOUtils_Map {

	//-------java--->OOS---->FOS----->bin file
	public static void store(String filename,Map<String,Vehicle> showroom) {
		try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(filename))){
			out.writeObject(showroom);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//------bin file----->FIS---->OIS---->java
	@SuppressWarnings("unchecked")
	public static Map<String,Vehicle> reStore(String filename) {
		try(ObjectInputStream in=new ObjectInputStream(new FileInputStream(filename))){
			return (Map<String,Vehicle>)in.readObject();
			
		}catch (Exception e) {
			e.printStackTrace();
			return new HashMap<String,Vehicle>();    //return empty map on exception
		}
	}
   //----storing in char file--------------java--->PW--->FW--->file
		public static void storeInCharFile(String filename,Map<String,Vehicle> showroom)
		{
			try(PrintWriter pw=new PrintWriter(new FileWriter(filename))){
				
				for(Vehicle v:showroom.values())
				pw.print(v);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	
}
