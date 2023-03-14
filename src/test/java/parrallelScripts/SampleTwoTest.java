package parrallelScripts;

import org.testng.annotations.Test;

public class SampleTwoTest {
  @Test
  public void testOne() {
	  long id = Thread.currentThread().getId();
	  System.out.println("TestOne from SampleTwo "+id);
  }
  
  @Test
  public void testTwo() {
	  long id = Thread.currentThread().getId();
	  System.out.println("TestTwo from SampleTwo "+id);
  }
  
  @Test
  public void testThree() {
	  long id = Thread.currentThread().getId();
	  System.out.println("TestThree from SampleTwo "+id);
  }
  
  @Test
  public void testFour() {
	  long id = Thread.currentThread().getId();
	  System.out.println("TestFour from SampleTwo "+id);
  }
}
