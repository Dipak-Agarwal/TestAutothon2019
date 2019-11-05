package testcases;

public class JaCOCOTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String newString = "Hello";
		String newString1 = "mukesh";
		String newString2 = "new";
		containsOrNot(newString1);
		containsOrNot(newString);
		containsOrNot(newString2);
		new JaCOCOTest();
	}
	
	public static void containsOrNot(String value)
	{
		if(value.contains("H"))
		{
			System.out.println("Contains H");
		}
		else if(value.contains("m"))
		{
			System.out.println("Contains m");
		}
		else
		{
			System.out.println("Nothing found");
		}
	}

}
