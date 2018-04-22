package com.mobiquityinc.packer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mobiquityinc.exception.APIException;

/**
 * 
 * Represents one instance of the backpack(knapsack) problem, containing 
 * a list of items and a bag capacity
 * 
 * @author victor
 *
 */
public class PackerInstance {
	
	private static final int KILOGRAMS_TO_DECIGRAMS = 100;
	private static final String ELEMENT_REGEX = "\\((.*?)\\)";
	private List<PackItem> Items;
	private int bagLimit;
	
	/**
	 * 
	 * creates an instance
	 * 
	 * @param instance - a string representing one instance of the problem ex: "8 : (1,15.3,€34)"
	 */
	public PackerInstance(String instance) {

		try {

			String[] values = instance.split(":");
			bagLimit = Integer.parseInt(values[0].trim()) * KILOGRAMS_TO_DECIGRAMS;
			Items = new ArrayList<>();
			Pattern pattern = Pattern.compile(ELEMENT_REGEX);
			Matcher matcher = pattern.matcher(values[1]);

			while (matcher.find()) {
				Items.add(new PackItem(matcher.group(1).trim()));
			}

			Collections.sort(Items);
		} catch (RuntimeException e) {
			//we thrown our own exception because even if APIException inherits from 
			//RuntimeException we don't want to expose our implementation details
			throw new APIException("please check your instance line " + instance);
		}
	}
	
	/**
	 * get the possible items to be used on this problem solution
	 * @return  the possible items to be used on this problem solution
	 */
	public List<PackItem> getItems() {
		return Items;
	}
	/**
	 * sets the possible items to be used on this problem solution
	 * @param items the possible items to be used on this problem solution
	 */
	public void setItems(List<PackItem> items) {
		Items = items;
	}
	
	/**
	 * gets the bag limit
	 * @return the bag maximu capacity
	 */
	public int getBagLimit() {
		return bagLimit;
	
	}
	/**
	 * set the bag maximum capacity
	 * @param bagLimit the bag maximum capacity
	 */
	public void setBagLimit(int bagLimit) {
		this.bagLimit = bagLimit;
	}
	
	
}
