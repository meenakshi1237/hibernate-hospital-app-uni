package com.hospital_app.cantroller;

import java.util.Scanner;

import com.hospital_app.dao.BranchCrud;
import com.hospital_app.dao.EncounterCrud;
import com.hospital_app.dao.HospitalCrud;
import com.hospital_app.dao.MedOrdersCrud;
import com.hospital_app.dao.PersonCrud;
import com.hospital_app.dto.Branch;
import com.hospital_app.dto.Encounter;
import com.hospital_app.dto.Hospital;
import com.hospital_app.dto.MedOrders;
import com.hospital_app.dto.Person;

public class HospitalApp {
		static Scanner sc=new Scanner(System.in);
	public static  int selectChoise() {
		System.out.println("select the option given below");
		System.out.println("1.To save");
		System.out.println("2.To find detais");
		System.out.println("3.To update");
		System.out.println("4.To Remove");
		System.out.println("0.To exit from app");
		int  choise=sc.nextInt();
		return choise;
	}
	public static void main(String[] args) {
		HospitalCrud hospitalcrud=new HospitalCrud();
		BranchCrud branchcrud=new BranchCrud();
		PersonCrud personcrud=new PersonCrud();
		EncounterCrud encountercrud=new EncounterCrud();
		MedOrdersCrud medorderscrud=new MedOrdersCrud();
		while(true) {
			int choise=selectChoise();
			switch(choise) {
			//outer case 1
			case 1:{
				while(true) {
					System.out.println("select from below which you wantg to save");
					System.out.println("1.To save hospital");
					System.out.println("2.To save Branch");
					System.out.println("3.To save person");
					System.out.println("4.to save encounter");
					System.out.println("5.To save medorders");
					System.out.println("0.To go to main menu");
					int saveoption=sc.nextInt();
					switch(saveoption) {
					//outer case 1 inner case 1
					case 1:{
						Hospital hospital=hospitalcrud.saveHospital();
						break;
					}
					//outer case 1 inner case 2
					case 2:{
						Branch branch=branchcrud.saveBranch();
						break;
					}
					//outer case 1 inner case 3
					case 3:{
						Person person=personcrud.savePerson();
						break;
					}
					//outer case 1 inner case 4
					case 4:{
						Encounter encounter=encountercrud.saveEncounter();
						break;
					}
					//outer case 1 inner case 5
					case 5:{
						MedOrders medorders=medorderscrud.saveMedOrders();
						break;
					}
					
					}
					if(saveoption==0) {
						break;
					}
				}
				break;
			}
			//outer case 2
			case 2:{
				while(true) {
					System.out.println("select below options to get details");
					System.out.println("1.To find Hospital by id");
					System.out.println("2.To find branch by id");
					System.out.println("3.To find person by id");
					System.out.println("4.To find encounter by id");
					System.out.println("5.To find medorders by id");
					System.out.println("0.To go to main menu");
					int findchoise=sc.nextInt();
					switch(findchoise) {
					//outer case 2 inner case 1
					case 1:{
						Hospital hospital=hospitalcrud.findHospitalById();
						break;
					}
					//outer case 2 inner case 2
					case 2:{
						Branch branch=branchcrud.findBranchById();
						break;
					}
					//outer case 2 inner case 3
					case 3:{
						Person  person=personcrud.findPerson();
						break;
					}
					//outer case 2 inner case 4
					case 4:{
						Encounter encounter=encountercrud.findEncounter();
						break;
					}
					//outer case 2 inner case 5
					case 5:{
						MedOrders medorder=medorderscrud.findMedorder();
						break;
					}
					
					}
					if(findchoise==0) {
						break;
					}
				}
				break;
			}
			//outer case 4
			case 4:{
				while(true) {
					System.out.println("select to remove");
					System.out.println("1.To remove hospital");
					System.out.println("2.to remove branch");
					System.out.println("0.To go to main menu");
					int choiseremove=sc.nextInt();
					switch(choiseremove) {
//					outer case 4 inner case 1
					case 1:{
						Hospital hospital=hospitalcrud.removeHospital();
						break;
					}
					//outer case 4 inner case 2
					case 2:{
						Branch branch=branchcrud.removeBranch();
						break;
					}
					}
					if(choiseremove==0) {
						break;
					}
					
				}
			}
			
			case 0:{
				System.out.println("thankyou visit again");
				System.exit(0);
			}
			
			}
		}
		
	}
}
