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
import bionicProInc.db.pojos.Material;
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
		
		//ADDING ENGINEERS (MATCH TO THE DATABASE DATA!)
		/*
		Engineer e0 = new Engineer(1, "Jack Browne","jackbrowne@engineer.com", Date.valueOf("2009-11-10"),Date.valueOf("2022-11-10"),"Production Control",4000.0f,3000.0f,3,8,Date.valueOf("1984-03-04"));
		//j.addEngineer(e0);
		Engineer e1 = new Engineer(2, "Alida Mckenney","ricardooriol@engineer.com", Date.valueOf("2020-03-13"),Date.valueOf("2030-03-13"),"CFO",10000.0f,8000.0f,0,0,Date.valueOf("1997-05-25"));
		//j.addEngineer(e1);
		Engineer e2 = new Engineer(3, "Carlos Martinez","ricardooriol@engineer.com", Date.valueOf("2020-03-13"),Date.valueOf("2030-03-13"),"CFO",10000.0f,8000.0f,0,0,Date.valueOf("1997-05-25"));
		//j.addEngineer(e2);
		Engineer e3 = new Engineer(4, "Mamadu Bongo","ricardooriol@engineer.com", Date.valueOf("2020-03-13"),Date.valueOf("2030-03-13"),"CFO",10000.0f,8000.0f,0,0,Date.valueOf("1997-05-25"));
		//j.addEngineer(e3);
		Engineer e4 = new Engineer(5, "Said Almaktum","ricardooriol@engineer.com", Date.valueOf("2020-03-13"),Date.valueOf("2030-03-13"),"CFO",10000.0f,8000.0f,0,0,Date.valueOf("1997-05-25"));
		//j.addEngineer(e4);
		Engineer e5 = new Engineer(6, "Carlotta Zerbelotti","ricardooriol@engineer.com", Date.valueOf("2020-03-13"),Date.valueOf("2030-03-13"),"CFO",10000.0f,8000.0f,0,0,Date.valueOf("1997-05-25"));
		//j.addEngineer(e5);
		Engineer e6 = new Engineer(7, "Aymane Boustta","ricardooriol@engineer.com", Date.valueOf("2020-03-13"),Date.valueOf("2030-03-13"),"CFO",10000.0f,8000.0f,0,0,Date.valueOf("1997-05-25"));
		//j.addEngineer(e6);
		Engineer e7 = new Engineer(8, "Ricardo Oriol","ricardooriol@engineer.com", Date.valueOf("2020-03-13"),Date.valueOf("2030-03-13"),"CFO",10000.0f,8000.0f,0,0,Date.valueOf("1997-05-25"));
		//j.addEngineer(e7);
		*/
		
		
		//ADDING CHARACTERISTICS
		/*
		Characteristic ch0 = new Characteristic(1,110,60,20,130,0,2);
		//j.addCharacteristic(ch0);
		Characteristic ch1 = new Characteristic(2,110,60,15,170,1,3);
		//j.addCharacteristic(ch1);
		Characteristic ch2 = new Characteristic(3,110,60,10,120,1,3);
		//j.addCharacteristic(ch2);
		Characteristic ch3 = new Characteristic(4,120,70,25,120,0,2);
		//j.addCharacteristic(ch3);
		Characteristic ch4 = new Characteristic(5,120,60,25,170,1,3);
		//j.addCharacteristic(ch4);
		Characteristic ch5 = new Characteristic(6,110,60,23,110,2,5);
		//j.addCharacteristic(ch5);
		Characteristic ch6 = new Characteristic(7,120,70,35,130,2,5);
		//j.addCharacteristic(ch6);
		Characteristic ch7 = new Characteristic(8,80,40,12,60,2,2);
		//j.addCharacteristic(ch7);
		Characteristic ch8 = new Characteristic(9,70,25,8,60,3,4);
		//j.addCharacteristic(ch8);
		Characteristic ch9 = new Characteristic(10,70,25,15,130,3,4);
		//j.addCharacteristic(ch9);
		Characteristic ch10 = new Characteristic(11,75,25,20,100,3,5);
		//j.addCharacteristic(ch10);
		*/
		
		
		//ADDING CUSTOMERS
		/*
		Customer c0 = new Customer(1, "Charles Clark", 48, "MALE", 628374912,"charlesclark@gmail.com","Calle de la Concordia 44", "Madrid", 28003);
		//j.addCustomer(c0);
		Customer c1 = new Customer(2, "Melinda Gates", 66, "FEMALE", 725384909,"melindagates@gmail.com","Calle de la Esperanza 55", "Madrid", 28203);
		//j.addCustomer(c1);
		Customer c2 = new Customer(3, "Clara Clark", 49, "FEMALE", 813474722,"claraclark@gmail.com","Calle de la Concordia 44", "Madrid", 28003);
		//j.addCustomer(c2);
		Customer c3 = new Customer(4, "Andres Galante", 23, "MALE", 627834992,"andresgalante@gmail.com","Calle de la Misericordia 91", "Madrid", 28008);
		//j.addCustomer(c3);
		Customer c4 = new Customer(5, "Maria Dario", 48, "FEMALE", 627491033,"mariadario@gmail.com","Calle Jamoncito 28", "Sevilla", 28043);
		//j.addCustomer(c4);
		*/
		 
		
		
		//ADDING PRODUCTS
		/*
		Product p0 = new Product(1, "Rigth hand", "Hand",399.0f,Date.valueOf("2021-01-15"), new byte[10]);
		//j.addProduct(p0); // 
		Product p1 = new Product(2, "Left hand", "Hand",399.0f,Date.valueOf("2021-01-15"), new byte[10]);
		//j.addProduct(p1);
		Product p2 = new Product(3, "Rigth leg", "Leg",799.0f,Date.valueOf("2021-01-15"), new byte[10]);
		//j.addProduct(p2);
		Product p3 = new Product(4, "Left leg", "Leg",799.0f,Date.valueOf("2021-01-15"), new byte[10]);
		//j.addProduct(p3);
		Product p4 = new Product(5, "Hip joint", "Hip",999.0f,Date.valueOf("2021-01-15"), new byte[10]);
		//j.addProduct(p4);
		Product p5 = new Product(6, "Cochlear implant", "Ear",1099.0f,Date.valueOf("2021-01-15"), new byte[10]);
		//j.addProduct(p5);
		Product p6 = new Product(7, "Ocular prosthesis", "Eye",199.0f,Date.valueOf("2021-01-15"), new byte[10]);
		//j.addProduct(p6);
		Product p7 = new Product(8, "Nose prosthesis", "Nose",499.0f,Date.valueOf("2021-01-15"), new byte[10]);
		//j.addProduct(p7);
		Product p8 = new Product(9, "Rigth foot", "Foot",399.0f,Date.valueOf("2021-01-15"), new byte[10]);
		//j.addProduct(p8);
		Product p9 = new Product(10, "Left Foot", "Foot",399.0f,Date.valueOf("2021-01-15"), new byte[10]);
		//j.addProduct(p9);
		*/
		
		//ADDING DUMMY PRODUCTS TO TEST DELETE METHOD FROM ENGINEER'S MENU
		/*
		Product p10 = new Product(10, "DUMMY", "Foot",399.0f,Date.valueOf("2021-01-15"), new byte[10]);
		j.addProduct(p10);
		Product p11 = new Product(10, "DUMMY2", "Foot",399.0f,Date.valueOf("2021-01-15"), new byte[10]);
		j.addProduct(p11);*/

		//TESTING
		/*
		System.out.println(j.viewBonus(4));
		System.out.println(j.viewProjectAchieved(4));
		System.out.println(j.searchProductByBody("Ear"));
		j.addProdIntoCh(p2, ch3);
		*/
		
		//ADDING MATERIALS
		/*
		Material m0 = new Material(1, "Polyethylene",6.75f,5000);
		//j.addMaterial(m0);
		Material m1 = new Material(2, "Polypropylene",9.75f,4000);
		//j.addMaterial(m1);
		Material m2 = new Material(3, "Acrylics",3.25f,1000);
		//j.addMaterial(m2);
		Material m3 = new Material(4, "Titanium",16.75f,10000);
		//j.addMaterial(m3);
		Material m4 = new Material(5, "Carbon fiber",20.75f,20000);
		//j.addMaterial(m4);
		Material m5 = new Material(6, "Aluminum",11.75f,20000);
		//j.addMaterial(m5);
		Material m6 = new Material(7, "Polyurethane",10.75f,8000);
		//j.addMaterial(m6);
		/*

		*/
		
		//FILLING PRODUCTS_CHARACTERISTICS
		/*
		j.addProd_Ch(p4, ch1);
		j.addProd_Ch(p3, ch6);
		j.addProd_Ch(p4, ch6);
		j.addProd_Ch(p1, ch10);
		j.addProd_Ch(p2, ch10);
		j.addProd_Ch(p1, ch9);
		j.addProd_Ch(p2, ch9);
		*/
		
		//FILLING PRODUCTS_MATERIALS
		/*
		j.addProd_Mat(p0,m3);
		j.addProd_Mat(p0,m4);
		j.addProd_Mat(p1,m3);
		j.addProd_Mat(p1,m4);
		j.addProd_Mat(p2,m3);
		j.addProd_Mat(p2,m4);
		j.addProd_Mat(p3,m3);
		j.addProd_Mat(p3,m4);
		j.addProd_Mat(p4,m4);
		j.addProd_Mat(p5,m5);
		j.addProd_Mat(p5,m6);
		j.addProd_Mat(p6,m2);
		j.addProd_Mat(p6,m1);
		j.addProd_Mat(p7,m0);
		j.addProd_Mat(p7,m2);
		j.addProd_Mat(p8,m3);
		j.addProd_Mat(p8,m4);
		j.addProd_Mat(p9,m3);
		j.addProd_Mat(p9,m4);
		*/
		
		//FILLING CUSTOMERS_PRODUCTS
		/*
		j.addCust_Prod(c0, p2);
		j.addCust_Prod(c1, p4);
		j.addCust_Prod(c2, p6);
		j.addCust_Prod(c3, p7);
		j.addCust_Prod(c4, p1);
		*/
		
		//FILLING ENGINEERS_PRODUCTS
		/*
		j.addEng_Prod(e0,p0);
		j.addEng_Prod(e2,p0);
		j.addEng_Prod(e0,p1);
		j.addEng_Prod(e2,p1);
		j.addEng_Prod(e1,p2);
		j.addEng_Prod(e4,p2);
		j.addEng_Prod(e5,p2);
		j.addEng_Prod(e1,p3);
		j.addEng_Prod(e4,p3);
		j.addEng_Prod(e5,p3);
		j.addEng_Prod(e4,p4);
		j.addEng_Prod(e5,p4);
		j.addEng_Prod(e4,p5);
		j.addEng_Prod(e5,p5);
		j.addEng_Prod(e0,p5);
		j.addEng_Prod(e4,p6);
		j.addEng_Prod(e5,p6);
		j.addEng_Prod(e5,p7);
		j.addEng_Prod(e4,p8);
		j.addEng_Prod(e5,p8);
		j.addEng_Prod(e4,p9);
		j.addEng_Prod(e5,p9);
		*/
		
		j.disconnect();
	}

}
