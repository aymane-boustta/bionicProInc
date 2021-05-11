package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import bionicProInc.db.jdbc.JDBCManager;
import bionicProInc.db.pojos.Characteristic;
import bionicProInc.db.pojos.Engineer;
import bionicProInc.db.pojos.Product;

public class Main {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public static void main(String[] args) throws ParseException, IOException {
		JDBCManager j = new JDBCManager();
		j.connect();

		System.out.println("Introduce contract start : ");
		LocalDate startDate = LocalDate.parse(reader.readLine(), formatter);
		System.out.println("Introduce contract end : ");
		LocalDate endDate = LocalDate.parse(reader.readLine(), formatter);
		System.out.println("Introduce birthdate : ");
		LocalDate birthtDate = LocalDate.parse(reader.readLine(), formatter);
		
		
		Engineer e = new Engineer("Carlotta Zerbelotti ",Date.valueOf(startDate),Date.valueOf(endDate),"Developpement Service",6000.00f,5000.00f,10,12,Date.valueOf(birthtDate));
		j.addEngineer(e); 
		
		/*Characteristic ch = new Characteristic(110,60,130,0,2);
		j.addCharacteristics(ch);
		Characteristic ch1 = new Characteristic(110,60,170,1,3);
		j.addCharacteristics(ch1);
		Characteristic ch2 = new Characteristic(110,60,120,1,3);
		j.addCharacteristics(ch2);
		Characteristic ch3 = new Characteristic(120,70,120,0,2);
		j.addCharacteristics(ch3);
		Characteristic ch4 = new Characteristic(120,60,170,1,3);
		j.addCharacteristics(ch4);
		Characteristic ch5 = new Characteristic(110,60,110,2,5);
		j.addCharacteristics(ch5);
		Characteristic ch6 = new Characteristic(120,70,130,2,5);
		j.addCharacteristics(ch6);
		
		Characteristic ch7 = new Characteristic(80,40,60,2,2);
		j.addCharacteristics(ch7);
		Characteristic ch8 = new Characteristic(70,25,60,3,4);
		j.addCharacteristics(ch8);
		Characteristic ch9 = new Characteristic(70,25,130,3,4);
		j.addCharacteristics(ch9);
		Characteristic ch10 = new Characteristic(75,25,100,3,5);
		j.addCharacteristics(ch10); */


		j.disconnect();
	}

}
