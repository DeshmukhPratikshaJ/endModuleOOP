package com.core;

import java.io.Serializable;
import java.time.LocalDate;

public class Vehicle implements Serializable,Comparable<Vehicle>{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 8176371664681908156L;
	
	private String chasis ;
	 private String company;
	 private Color color;
	 private double price;
	 private  boolean isAvailable;
	 private  LocalDate mfgDate;
	 
	 //-------CONSTRUCTOR-------------------
	 
	public Vehicle(String chasis, String company, Color color, double price, LocalDate mfgDate) {
		super();
		this.chasis = chasis;
		this.company = company;
		this.color = color;
		this.price = price;
		this.mfgDate = mfgDate;
		this.isAvailable=true;
	}

	
	
	public Vehicle(String chasis) {
		this.chasis = chasis;
	}

//-----------------compareTo----------------
	@Override
	public int compareTo(Vehicle o) {
		return this.chasis.compareTo(o.getChasis());
	}
	

	//------------------toString--------------------
	@Override
	public String toString() {
		return "Vehicle [chasis=" + chasis + ", company=" + company + ", color=" + color + ", price=" + price
				+ ", isAvailable=" + (isAvailable? "yes":"no") + ", mfgDate=" + mfgDate + "]";
	}

	
	public String getCompany() {
		return company;
	}


	public Color getColor() {
		return color;
	}


	public double getPrice() {
		return price;
	}


	public LocalDate getMfgDate() {
		return mfgDate;
	}


	public String getChasis() {
		return chasis;
	}
	

	public boolean isAvailable() {
		return isAvailable;
	}



	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}



	//-----------equals---------------
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vehicle)
		return chasis.equals(((Vehicle) obj).getChasis());
		else
			return false;
	}

	
	
}
