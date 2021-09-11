import java.io.FileNotFoundException;
import java.io.IOException;

public class LZWtest {
	public static void main (String [] args) throws IOException
	{
	LZW3 hello = new LZW3("lzw-file3.txt");
//	System.out.println(hello.full()); 
//	System.out.println(hello.output1); 

	System.out.println(hello.outputString.toString()); 
	System.out.println(hello.outputInts.toString()); 
//	System.out.println(hello.output); 
	System.out.println(hello.binaryString); 
//	
//	System.out.println(hello.dictionary());

	
	
	//224 //then 228
	}
}
