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
import bionicProInc.db.pojos.Customer;
import bionicProInc.db.pojos.Engineer;
import bionicProInc.db.pojos.Product;

public class Main {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public static void main(String[] args) throws ParseException, IOException {
		JDBCManager j = new JDBCManager();
		j.connect();
		
		/*
		System.out.println("Introduce contract start : ");
		LocalDate startDate = LocalDate.parse(reader.readLine(), formatter);
		System.out.println("Introduce contract end : ");
		LocalDate endDate = LocalDate.parse(reader.readLine(), formatter);
		System.out.println("Introduce birthdate : ");
		LocalDate birthtDate = LocalDate.parse(reader.readLine(), formatter);
		*/
		
		//ADDING ENGINEERS
		/*
		Engineer e = new Engineer("Ricardo Oriol ","ricardooriol@engineer.com", Date.valueOf("2020-03-13"),Date.valueOf("2030-03-13"),"CFO",10000.0f,8000.0f,0,0,Date.valueOf("1997-05-25"));
		j.addEngineer(e); 
		*/
		
		//ADDING CHARACTERISTICS
		/*
		Characteristic ch0 = new Characteristic(1,110,60,20,130,0,2,null);
		j.addCharacteristic(ch0);
		Characteristic ch1 = new Characteristic(2,110,60,15,170,1,3,null);
		j.addCharacteristic(ch1);
		Characteristic ch2 = new Characteristic(3,110,60,10,120,1,3,null);
		j.addCharacteristic(ch2);
		Characteristic ch3 = new Characteristic(4,120,70,25,120,0,2,null);
		j.addCharacteristic(ch3);
		Characteristic ch4 = new Characteristic(5,120,60,25,170,1,3,null);
		j.addCharacteristic(ch4);
		Characteristic ch5 = new Characteristic(6,110,60,23,110,2,5,null);
		j.addCharacteristic(ch5);
		Characteristic ch6 = new Characteristic(7,120,70,35,130,2,5,null);
		j.addCharacteristic(ch6);
		Characteristic ch7 = new Characteristic(8,80,40,12,60,2,2,null);
		j.addCharacteristic(ch7);
		Characteristic ch8 = new Characteristic(9,70,25,8,60,3,4,null);
		j.addCharacteristic(ch8);
		Characteristic ch9 = new Characteristic(10,70,25,15,130,3,4,null);
		j.addCharacteristic(ch9);
		Characteristic ch10 = new Characteristic(11,75,25,20,100,3,5,null);
		j.addCharacteristic(ch10);
		*/
		
		/*
		//ADDING CUSTOMERS
		Customer c0 = new Customer("Charles Clark", 48, "MALE", 628374912,"charlesclark@gmail.com","Calle de la Concordia 44", "Madrid", 28003);
		j.addCustomer(c0);
		Customer c1 = new Customer("Melinda Gates", 66, "FEMALE", 725384909,"melindagates@gmail.com","Calle de la Esperanza 55", "Madrid", 28203);
		j.addCustomer(c1);
		Customer c2 = new Customer("Clara Clark", 49, "FEMALE", 813474722,"claraclark@gmail.com","Calle de la Concordia 44", "Madrid", 28003);
		j.addCustomer(c2);
		Customer c3 = new Customer("Andres Galante", 23, "MALE", 627834992,"andresgalante@gmail.com","Calle de la Misericordia 91", "Madrid", 28008);
		j.addCustomer(c3);
		Customer c4 = new Customer("Maria Dario", 48, "FEMALE", 627491033,"mariadario@gmail.com","Calle Jamoncito 28", "Sevilla", 28043);
		j.addCustomer(c4);
		*/
		
		//ADDING PRODUCTS
		/*
		Product p0 = new Product(1, "Rigth hand", "Hand",399.0f,Date.valueOf("2021-01-15"), new byte[10],null,null,null);
		j.addProduct(p0);
		Product p1 = new Product(2, "Left hand", "Hand",399.0f,Date.valueOf("2021-01-15"), new byte[10],null,null,null);
		j.addProduct(p1);
		Product p2 = new Product(3, "Rigth leg", "Leg",799.0f,Date.valueOf("2021-01-15"), new byte[10],null,null,null);
		j.addProduct(p2);
		Product p3 = new Product(4, "Left leg", "Leg",799.0f,Date.valueOf("2021-01-15"), new byte[10],null,null,null);
		j.addProduct(p3);
		Product p4 = new Product(5, "Hip joint", "Hip",999.0f,Date.valueOf("2021-01-15"), new byte[10],null,null,null);
		j.addProduct(p4);
		Product p5 = new Product(6, "Cochlear implant", "Ear",1099.0f,Date.valueOf("2021-01-15"), new byte[10],null,null,null);
		j.addProduct(p5);
		Product p6 = new Product(7, "Ocular prosthesis", "Eye",199.0f,Date.valueOf("2021-01-15"), new byte[10],null,null,null);
		j.addProduct(p6);
		Product p7 = new Product(8, "Nose prosthesis", "Nose",499.0f,Date.valueOf("2021-01-15"), new byte[10],null,null,null);
		j.addProduct(p7);
		Product p8 = new Product(9, "Rigth foot", "Foot",399.0f,Date.valueOf("2021-01-15"), new byte[10],null,null,null);
		j.addProduct(p8);
		Product p9 = new Product(10, "Rigth Foot", "Foot",399.0f,Date.valueOf("2021-01-15"), new byte[10],null,null,null);
		j.addProduct(p9);
		*/
		

		//System.out.println(j.viewBonus(4));
		System.out.println(j.viewProjectAchieved(4));
		//System.out.println(j.searchProductByBody("Ear"));
		//j.addToProdCh(p2, ch3);

		//TESTING
		/*
		System.out.println(j.viewBonus(4));
		System.out.println(j.viewProjectAchieved(4));
		System.out.println(j.searchProductByBody("Ear"));
		j.addProdIntoCh(p2, ch3);
		*/

		
		j.disconnect();
		
	}

}
