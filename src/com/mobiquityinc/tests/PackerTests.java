package com.mobiquityinc.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.Packer;
import com.mobiquityinc.packer.PackerInstance;

public class PackerTests {
	
	@Test
	public void noItemsFit() {
		String solution = Packer.solvePackerInstance(new PackerInstance("8 : (1,15.3,€34)"));
		assertEquals("-", solution );
	}
	
	@Test
	public void multipleItemsCouldBeUsedButShallUseTheLighterOne() {
		String solution = Packer.solvePackerInstance(new PackerInstance("56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)"));
		assertEquals("8,9", solution );
	}
	
	@Test
	public void singleItemInResponse() {
		String solution = Packer.solvePackerInstance((new PackerInstance("81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)")));
		assertEquals("4", solution );
	}
	
	@Test
	public void multipleItemsInResponse() {
		String solution = Packer.solvePackerInstance(new PackerInstance("75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)"));
		assertEquals("2,7", solution );
	}
	
	@Test(expected = APIException.class)  
	public void shallThrowAPIException() {
		Packer.solvePackerInstance(new PackerInstance("75 : (1.85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)"));
	}
	
	@Test
	public void testFromFileWithValidContent() {
		String result = Packer.pack("./test.txt");
		assertEquals("4\n-\n2,7\n8,9", result);
	}
	
	@Test(expected = APIException.class) 
	public void testFromFileWithInValidContent() {
		Packer.pack("./testInvalid.txt");
	}
	
	@Test(expected = APIException.class) 
	public void testFromInexistentFile() {
		Packer.pack("./inexistent.txt");
	}
	
}
