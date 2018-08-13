import java.io.Serializable;
import java.util.HashMap;

public class Day implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -559003490087114429L;
	private double income;
	private int noOfCostumers;
	private HashMap<String, Integer> flavors;

	public Day(double income, int noOfCostumers, HashMap<String, Integer> flavors){
		this.income = income;
		this.noOfCostumers = noOfCostumers;
		this.flavors = flavors;
	}

	public double getIncome(){
		return this.income;
	}

	public int getNoOfCostumers(){
		return this.noOfCostumers;
	}

	public HashMap<String, Integer> getFlavors(){
		return this.flavors;
	}
}
/**
 * Day class:
 * 	-Day report
 * 	-contains: income, number of costumers, flavors of pizza and how many were sold
 *  	
 **/
