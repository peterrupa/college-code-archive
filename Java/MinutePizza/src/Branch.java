import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Branch implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1565646253554432532L;
	String branchName;
	private double income;
	private int noOfCostumers;
	private HashMap<String, Integer> flavors=new HashMap<String, Integer>();
	private HashMap<String, ArrayList<Day>> data=new HashMap<String, ArrayList<Day>>();
	private CentralManagement cm;
	
	public Branch(String branchName, CentralManagement cm){
		this.branchName=branchName;
		this.cm=cm;
	}
	
	protected void saveIncome(int income){
		this.income=income;
	}
	
	protected void saveNoOfCostumers(int noOfCostumers){
		this.noOfCostumers=noOfCostumers;
	}
	
	protected void saveFlavors(String flavorName, int soldPizza){
		//this.flavors.put(flavorName, soldPizza);
		cm.addFlavor(flavorName, soldPizza);
	}
	@SuppressWarnings("unchecked")
	private void readData(){
		readFlavors();
		try{
			ObjectInputStream reader =new ObjectInputStream(new FileInputStream("Data.csv"));
			data=(HashMap<String, ArrayList<Day>>)reader.readObject();
			reader.close();
		}catch(Exception e){
		}
	}
	
	@SuppressWarnings("unchecked")
	private void readFlavors(){
		try{
			ObjectInputStream reader =new ObjectInputStream(new FileInputStream("Flavors.csv"));
			flavors=(HashMap<String, Integer>)reader.readObject();
			reader.close();
		}catch(Exception e){
		}
	}
	
	private void addData(){
		this.readData();
		data.get(this.branchName).add(new Day(this.income,this.noOfCostumers,this.flavors));
	}

	protected void saveData(){
		this.addData();
		try{
			ObjectOutputStream writer =new ObjectOutputStream(new FileOutputStream("Data.csv"));
			writer.writeObject(flavors);
			writer.close();
		}catch(Exception e){
		}
	}
	
	protected void saveflavors(){
		this.addData();
		try{
			ObjectOutputStream writer =new ObjectOutputStream(new FileOutputStream("Flavors.csv"));
			writer.writeObject(data);
			writer.close();
		}catch(Exception e){
		}
	}
}
/**
 * Brach class:
 * 	-can add an income record, customer record, and flavor record
 * 	-sends record to file
 * 
 * Accessible Methods:
 * 	saveIncome(int income)	-adds to income record
 * 	saveNoOfCostumers(int noOfCostumers)	-adds to customer record
 * 	saveFlavors(String flavorName, int soldPizza)	-adds to flavor record
 * 	saveData()	-saves to file
 **/
