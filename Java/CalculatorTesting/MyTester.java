import org.junit.Test;

import static org.junit.Assert.*;

public class MyTester {
	@Test
	public void testNfactorial1() {
		MyCalculator calc = new MyCalculator();
		float actual = calc.nfactorial(0);
		assertEquals(actual, 1, 0.0);
	}
    
    @Test
	public void testNfactorial2() {
		MyCalculator calc = new MyCalculator();
		float actual = calc.nfactorial(1);
		assertEquals(actual, 1, 0.0);
	}
    
    @Test
	public void testNfactorial3() {
		MyCalculator calc = new MyCalculator();
		float actual = calc.nfactorial(2);
		assertEquals(actual, 2, 0.0);
	}
    
    
    @Test
	public void testNfactorial4() {
		MyCalculator calc = new MyCalculator();
		float actual = calc.nfactorial(5);
		assertEquals(actual, 120, 0.0);
	}
    
    @Test
	public void testBinarySearch1() {
		MyCalculator calc = new MyCalculator();
		int[] a = {1, 2, 3, 4, 5};  
		int actual = calc.binarySearch(a, 3);
		assertEquals(actual, 2, 0.0);
	}
    
	@Test
	public void testBinarySearch2() {
		MyCalculator calc = new MyCalculator();
		int[] a = {1, 2, 3, 4, 5};  
		int actual = calc.binarySearch(a, 2);
		assertEquals(actual, 1, 0.0);
	}
    
    @Test
	public void testBinarySearch3() {
		MyCalculator calc = new MyCalculator();
		int[] a = {1, 2, 3, 4, 5};  
		int actual = calc.binarySearch(a, 4);
		assertEquals(actual, 3, 0.0);
	}
    
    @Test
	public void testBinarySearch4() {
		MyCalculator calc = new MyCalculator();
		int[] a = {1, 2, 3, 4, 5};  
		int actual = calc.binarySearch(a, 0);
		assertEquals(actual, 1, 0.0);
	}
    
    @Test
	public void testBinarySearch5() {
		MyCalculator calc = new MyCalculator();
		int[] a = {1, 2, 3, 4, 5};  
		int actual = calc.binarySearch(a, 6);
		assertEquals(actual, 1, 0.0);
	}
}

// note: according to the original code, binarySearch returns 1 when input x is not found.