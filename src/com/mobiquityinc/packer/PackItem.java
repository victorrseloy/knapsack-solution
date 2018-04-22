package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;

/**
 * Represents one item that can be used in the knapsack problem solution
 * 
 * @author victor
 *
 */
public class PackItem implements Comparable<PackItem>{
	
	private static final int KILOGRAMS_TO_DECIGRAMS = 100;
	
	private int Id;
	private int weight;
	private double value;
	
	/**
	 * constructs a knapsack product from a string
	 * @param item a string representing a knapsack item ex: (5,30.18,€9) the 1st number is a thing's index number,
	 * the 2nd is its weight and the 3rd is its cost.
	 */
	public PackItem(String item) {
		try {

			String[] values = item.split(",");
			Id = Integer.parseInt(values[0]);
			// converts the item weight to DECIGRAMS as our solution does not work with
			// float numbers
			weight = (int) (Float.parseFloat(values[1]) * KILOGRAMS_TO_DECIGRAMS);
			// removes the "€" symbol and converts the item cost to double
			value = Double.parseDouble(values[2].replace("€", ""));
		} catch (RuntimeException e) {
			//we thrown our own exception because even if APIException inherits from 
			//RuntimeException we don't want to expose our implementation details
			throw new APIException("please check your PackItem input " + item);
		}
	}

	/**
	 * gets the item id
	 * @return the item id
	 */
	public int getId() {
		return Id;
	}
	
	/**
	 * sets the item id
	 * @param id the item id
	 */
	public void setId(int id) {
		Id = id;
	}
	
	/**
	 * gets the item weight in decigrams 
	 * @return the item weight in decigrams
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * sets the item weight in decigrams
	 * @param weight the item weight in decigrams
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	/**
	 * gets the item value 
	 * @return the item value 
	 */
	public double getValue() {
		return value;
	}
	
	/**
	 * sets the item value 
	 * @param value the item value 
	 */
	public void setValue(double value) {
		this.value = value;
	}
	
	/**
	 * the compare to method is very important is the capacity to sort our items
	 * by value and weight is what allows us to respect this constraint
	 * "You would prefer to send a package which weights less in case there is more than one package with the
	 *same price. "
	 */
	@Override
	public int compareTo(PackItem anotherPackItem) {
		if(this.getValue()-anotherPackItem.getValue()!=0) {
			return (int)((this.getValue()-anotherPackItem.getValue())*100);
		}
		return this.getWeight()-anotherPackItem.getWeight();
	}
	
	
	
	
}
