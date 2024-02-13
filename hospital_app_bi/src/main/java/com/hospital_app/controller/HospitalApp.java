package com.hospital_app.controller;

import java.util.Scanner;

import com.hospital_app.dao.AddressDao;
import com.hospital_app.dao.BranchDao;
import com.hospital_app.dao.EncounterDao;
import com.hospital_app.dao.HospitalDao;
import com.hospital_app.dao.ItemDao;
import com.hospital_app.dao.MedOrderDao;
import com.hospital_app.dao.PersonDao;
import com.hospital_app.entity.Address;
import com.hospital_app.entity.Branch;
import com.hospital_app.entity.Encounter;
import com.hospital_app.entity.Hospital;
import com.hospital_app.entity.Item;
import com.hospital_app.entity.MedOrder;
import com.hospital_app.entity.Person;

public class HospitalApp {
	static Scanner sc = new Scanner(System.in);

	public static int selectChoise() {
		System.out.println("select the option given below");
		System.out.println("1.To save");
		System.out.println("2.To find detais");
		System.out.println("3.To update");
		System.out.println("4.To Remove");
		System.out.println("0.To exit from app");
		int choise = sc.nextInt();
		return choise;
	}

	public static int selectSaveOption() {
		System.out.println("select from below which you wantg to save");
		System.out.println("1.To save hospital");
		System.out.println("2.To save Branch");
		System.out.println("3.To save person");
		System.out.println("4.to save encounter");
		System.out.println("5.To save medorders");
		System.out.println("0.To go to main menu");
		int saveOption = sc.nextInt();
		return saveOption;
	}

	public static int seleftFindOption() {
		System.out.println("select below options to get details");
		System.out.println("1.To find Hospital by id");
		System.out.println("2.To find branch by id");
		System.out.println("3.To find person by id");
		System.out.println("4.To find encounter by id");
		System.out.println("5.To find medorders by id");
		System.out.println("6.To find item by item id");
		System.out.println("0.To go to main menu");
		int findOption = sc.nextInt();
		return findOption;
	}
	
	public static int selectUpdateOption() {
		System.out.println("select below option to update");
		System.out.println("1.To update hospital");
		System.out.println("2.To update branch");
		System.out.println("3.To update address");
		System.out.println("4.To update encounters");
		System.out.println("5.To update person");
		System.out.println("6>To update medorders");
		System.out.println("0.to go to main menu");
		int updateOption=sc.nextInt();
		return updateOption;
	}
	
	public static int selectRemoveOption() {
		System.out.println("select to remove");
		System.out.println("1.To remove hospital");
		System.out.println("2.To remove branch");
		System.out.println("3.To remove encounter");
		System.out.println("4.To remove medorders");
		System.out.println("5.To remove Person");
		System.out.println("6.To remove item");
		System.out.println("0.To go to main menu");
		int removeOption = sc.nextInt();
		return removeOption;
	}

	public static void main(String[] args) {
		HospitalDao hospitaldao=HospitalDao.createHospoitalDao();
		BranchDao branchdao=BranchDao.createBranchDao();
		PersonDao persondao=PersonDao.createPersonDao();
		EncounterDao encounterdao=EncounterDao.createEncounterDao();
		MedOrderDao medorderdao=MedOrderDao.createMedOrderDao();
		ItemDao itemdao=ItemDao.createItemDao();
		AddressDao addressdao=AddressDao.createAddressDao();
		while(true) {
		int choise=selectChoise();
			switch(choise) {
			//outer case 1
			case 1:{
				while(true) {
					int saveoption=selectSaveOption();
					switch(saveoption) {
					//outer case 1 inner case 1
					case 1:{
						Hospital hospital=hospitaldao.saveHospital();
						break;
					}
					//outer case 1 inner case 2
					case 2:{
						Branch branch=branchdao.saveBranch();
						break;
					}
					//outer case 1 inner case 3
					case 3:{
						Person person=persondao.savePerson();
						break;
					}
					//outer case 1 inner case 4
					case 4:{
						Encounter encounter=encounterdao.saveEncounter();
						break;
					}
					//outer case 1 inner case 5
					case 5:{
						MedOrder medOrder=medorderdao.saveMedOrders();
						break;
					}
					default:{
						System.out.println("please select valid option");
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
					int findchoise=seleftFindOption();
					switch(findchoise) {
					//outer case 2 inner case 1
					case 1:{
						Hospital hospital=hospitaldao.findHospitalById();
						break;
					}
					//outer case 2 inner case 2
					case 2:{
						Branch branch=branchdao.findBranchById();
						break;
					}
					//outer case 2 inner case 3
					case 3:{
						Person person=persondao.findPerson();
						break;
					}
					//outer case 2 inner case 4
					case 4:{
						Encounter encounter=encounterdao.findEncounter();
						break;
					}
					//outer case 2 inner case 5
					case 5:{
						MedOrder medorder=medorderdao.findMedorder();
						break;
					}
					case 6:{
						Item item=itemdao.findItem();
						break;
					}
					default:{
						System.out.println("please select valid option");
						break;
					}
					
					}
				
					if(findchoise==0) {
						break;
					}
				}
				break;
			}
			//outer case 3
			case 3:{
				while(true) {
					int updateOption=selectUpdateOption();
					switch(updateOption) {
					case 1:{
						Hospital hospital=hospitaldao.updateHospital();
						break;
					}
					case 2:{
						Branch branch=branchdao.updateBranch();
						break;
					}
					case 3:{
						Address address=addressdao.updateAddress();
						break;
					}
					case 4:{
						Encounter encounter=encounterdao.updateEncounter();
						break;
					}
					case 5:{
						Person person=persondao.updatePerson();
						break;
					}
					case 6:{
						
					}
					}
					
					if(updateOption==0) {
						break;
					}
				}
				break;
			}
			//outer case 4
			case 4:{
				while(true) {
					
					int removeOption=selectRemoveOption();
					switch(removeOption) {
//					outer case 4 inner case 1
					case 1:{
						boolean res=hospitaldao.removeHospital();
						break;
					}
					//outer case 4 inner case 2
					case 2:{
						boolean res=branchdao.removeBranch();
						break;
					}
					//outer case 4 inner case 3
					case 3:{
						boolean res=encounterdao.removeEncounters();
						break;
					}
					case 4:{
						boolean res=medorderdao.removeMedOrders();
						break;
					}
					case 5:{
						boolean res=persondao.removePerson();
						break;
					}
					case 6:{
						boolean res=itemdao.remoVeItem();
						break;
					}
					default:{
						System.out.println("please select valid option");
						break;
					}
					}
					if(removeOption==0) {
						break;
					}
					
				}
			}
			default:{
				System.out.println("please select valid option");
				break;
			}
			case 0:{
				System.out.println("thankyou visit again");
				System.exit(0);
			}
			
			}
		}
		
	}
}
