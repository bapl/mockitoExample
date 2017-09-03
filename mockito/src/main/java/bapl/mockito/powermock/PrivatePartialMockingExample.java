package bapl.mockito.powermock;

public class PrivatePartialMockingExample {
	 public String methodToTest() { 
	       return privateToMock("input"); 
	   } 
	 
	   private String privateToMock(String input) { 
	       return "REAL VALUE = " + input; 
	   } 
}
