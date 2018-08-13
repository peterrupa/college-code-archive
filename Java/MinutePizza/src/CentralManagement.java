import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CentralManagement extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9190788995562945729L;
	private HashMap<String, Integer> currentFlavors = new HashMap<String, Integer>();
	private HashMap<String, Integer> allFlavors = new HashMap<String, Integer>();
	
	private HashMap<String, ArrayList<Day>> data = new HashMap<String, ArrayList<Day>>();

	public CentralManagement(){
		readData();
		//getFlavor();
	}
	@SuppressWarnings("unchecked")
	private void readData(){
		try{
			ObjectInputStream reader =new ObjectInputStream(new FileInputStream("Data.csv"));
			this.data=(HashMap<String, ArrayList<Day>>) reader.readObject();	
			reader.close();
		}catch(Exception e){
		}
		
		try{
			ObjectInputStream permanentWriter =new ObjectInputStream(new FileInputStream("history.csv"));
			this.allFlavors=(HashMap<String, Integer>) permanentWriter.readObject();
			permanentWriter.close();

		}catch(Exception e){
		}
	}
	
	protected void saveData(){
		try{
			ObjectOutputStream writer =new ObjectOutputStream(new FileOutputStream("Data.csv"));
			writer.writeObject(data);
			writer.close();
		}catch(Exception e){
		}
		try{
			
			ObjectOutputStream permanentWriter =new ObjectOutputStream(new FileOutputStream("history.csv"));
			permanentWriter.writeObject(allFlavors);
			permanentWriter.close();
		}catch(Exception e){
		}
	}
	
	protected void addBranches(String a){
		ArrayList<Day> b = new ArrayList<Day>();
		if(data.containsKey(a)){//branchName record exists
			JOptionPane.showMessageDialog(null, a+" Branch already Exists!");
		}else{
			data.put(a, b);
			JOptionPane.showMessageDialog(null, "Successfull Added Branch "+a);
		}
	}
	
	protected void deleteBranch(String a){
		// removes a branch object from branches arrayList
		data.remove(a);
	}

	protected void addFlavor(String name, int sold){
		int old;
		//adds a flavor to flavors HashMap with <name,sold> value pair
		if(currentFlavors.containsKey(name)){//flavor record exists
			old=currentFlavors.get(name);
			if(sold!=0){
				JOptionPane.showMessageDialog(null, name+" Flavor already Exists!");
			}
			currentFlavors.replace(name, old+sold);
		}else{
			currentFlavors.put(name,sold);
			JOptionPane.showMessageDialog(null, "Successfull Added "+name+ " Flavor!");
		}
		if(!allFlavors.containsKey(name)){
			allFlavors.put(name, sold);
		}else{
			old=allFlavors.get(name);
			allFlavors.replace(name, old+sold);
		}
	}

	protected void deleteFlavor(String a){
		//deletes a flavor from flavors HashMap
		//when you delete, you do not want to delete all the data(statistical data). Only what is seen in the GUI is deleted
		currentFlavors.remove(a);
	}
	
	//gets pizza flavors from data and adds to flavors HashMap
//	private void getFlavor(){
//		HashMap<String,Integer> tempFlavors = new HashMap<String,Integer>();
//		boolean found=false;
//		Iterator<Map.Entry<String, ArrayList<Day>>> i = data.entrySet().iterator();
//		while(i.hasNext()){
//			Map.Entry<String, ArrayList<Day>> entry = i.next();
//				for(Day temp : entry.getValue()){
//					tempFlavors=temp.getFlavors();
//
//					Iterator<Map.Entry<String, Integer>> k = tempFlavors.entrySet().iterator();
//					Iterator<Map.Entry<String, Integer>> j = this.currentFlavors.entrySet().iterator();
//
//					while(k.hasNext()){
//						found=false;
//						Map.Entry<String, Integer> entry1 = k.next();
//						while(j.hasNext()){
//							Map.Entry<String, Integer> entry2 = j.next();
//							if(entry1.getKey()==entry2.getKey()){
//								entry1.setValue(entry1.getValue()+entry2.getValue());
//								found=true;
//							}
//						}
//						if(found==false){
//							this.addFlavor(entry1.getKey(),entry1.getValue());
//						}
//					}
//				}
//			}
//	}

	//gets the mean of each pizza sold per branch
	//return Pizza name: mean pizza sold
	protected HashMap<String,Integer> meanOfPizzaSold(){
		HashMap<String, Integer> mean = new HashMap<String, Integer>();
		Iterator<Map.Entry<String, Integer>> i = this.currentFlavors.entrySet().iterator();//USES this.flavors HashMap not DATA
		while(i.hasNext()){
			Map.Entry<String, Integer> entry = i.next();
				mean.put(entry.getKey(), entry.getValue()/data.size());
		}
		return mean;
	}

	
	
	//sorts pizzaflavor according to pizza sold
	//return Pizza name: total pizza sold
	protected HashMap<String,Integer> mostNumberOfPizzaFlavor(){
		HashMap<String, Integer> mean = new HashMap<String, Integer>();
		Iterator<Map.Entry<String, Integer>> i = this.allFlavors.entrySet().iterator();//USES this.flavors HashMap not DATA
		while(i.hasNext()){
			Map.Entry<String, Integer> entry = i.next();
			mean.put(entry.getKey(), entry.getValue());
		}
		return mean;
	}
	
	//returns BranchName: total No. of customers
	protected HashMap<String,Integer> famousBranch(){
		int add;
		HashMap<String, Integer> branchfame = new HashMap<String, Integer>();
		Iterator<Map.Entry<String, ArrayList<Day>>> i = data.entrySet().iterator();
		while(i.hasNext()){
			add=0;
			Map.Entry<String, ArrayList<Day>> entry = i.next();
				for(Day temp : entry.getValue()){
					add+=temp.getNoOfCostumers();
				}
				branchfame.put(entry.getKey(), add);
		}
		return null;
	}
	  
	//returns BranchName: income
	protected HashMap<String,Integer> richBranch(){
		int add;
		HashMap<String, Integer> branchrich = new HashMap<String, Integer>();
		Iterator<Map.Entry<String, ArrayList<Day>>> i = data.entrySet().iterator();
		while(i.hasNext()){
			add=0;
			Map.Entry<String, ArrayList<Day>> entry = i.next();
				for(Day temp : entry.getValue()){
					add+=temp.getIncome();
				}
				branchrich.put(entry.getKey(), add);
		}
		return null;
	}
}

/**
 * Central Management class:
 * 	-stores ALL data
 * 	-saves data to file
 * 
 * Accessible methods:
 *  addFlavor(String name, int sold) 	- 	if adding a new flavor sold=0
 * 	addBranches(String a)	
 * 	deleteFlavor(String a)
 * 	deleteBranch(Branch a)
 * 
 * Statistical Data:
 * 	meanOfPizzaSold()			-returns hashmap<String flavorname, Integer meanPizzaSold>
 * 	mostNumberOfPizzaFlavor()	-returns hashmap<String flavorname, Integer totalPizzaSoldPerFlavor>
 * 	famousBranch()				-returns hashmap<String flavorname, Integer totalCustomersOfBranch>
 * 	richBranch()				-returns hashmap<String flavorname, Integer totalIncomeOfBranch>
 * 
 * 	saveData()	-saves data
 **/
