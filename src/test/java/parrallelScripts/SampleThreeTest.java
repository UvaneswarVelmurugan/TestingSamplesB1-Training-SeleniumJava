package parrallelScripts;

import org.testng.annotations.Test;

public class SampleThreeTest {
	 @Test
	  public void testOne() {
		  long id = Thread.currentThread().getId();
		  System.out.println("TestOne from SampleThree "+id);
	  }
	  
	  @Test
	  public void testTwo() {
		  long id = Thread.currentThread().getId();
		  System.out.println("TestTwo from SampleThree "+id);
	  }
	  
	  @Test
	  public void testThree() {
		  long id = Thread.currentThread().getId();
		  System.out.println("TestThree from SampleThree "+id);
	  }
	  
	  @Test
	  public void testFour() {
		  long id = Thread.currentThread().getId();
		  System.out.println("TestFour from SampleThree "+id);
	  }
}
