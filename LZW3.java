import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap; 

public class LZW3{
	HashMap dictionary = new HashMap(); 
	ArrayList<String> outputString; 
	ArrayList<Integer> outputInts; 
	String binaryString = ""; 
	int currentNum = 256; 
	
	public LZW3(String fileName) throws IOException
	{
		outputString = new ArrayList<String>(); 
		outputInts = new ArrayList<Integer>(); 
		for(int letter= 0;letter<256;letter++)
		{
			dictionary.put(""+(char)letter,letter); 
			
		}
		

		int dictSize = 256;
		
		BufferedReader reader = new BufferedReader (new FileReader(fileName));


		String current = ""+(char)(reader.read());

		while (reader.ready()){
			String next = ""+(char)(reader.read());
			String currentPlusNext = current+next; 
		if (dictionary.containsKey(currentPlusNext))
		{
			current = currentPlusNext;
		}
		else
		{
			outputString.add(current);
			dictionary.put(currentPlusNext,currentNum);
			this.NextNum(); 
			current = next;
		}

	}


		if (!current.equals(""))
		{
			outputString.add(current);
		}
		reader.close();
		this.compress(); 

		}
	
	
	public void NextNum()
	{
		currentNum += 1; 
	}
	

	
	public void compress()
	{
		for(int i = 0; i < outputString.size(); i ++)
		{
			Integer asciiValue = (Integer) dictionary.get(outputString.get(i));
			
			outputInts.add(asciiValue);
			
		}
		
		for(int i = 0; i < outputInts.size(); i++)
		{
		
			String binaryInt = Integer.toBinaryString(outputInts.get(i)); 
			outputString.set(i,binaryInt); 
			
			
		}
		
		
		for(int i = 0; i < outputInts.size(); i++)
		{
		
			binaryString += outputString.get(i); 
			
			
		}
		
		BinaryOut out = new BinaryOut("output.dat");
        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) == '0') {
                out.write(false);
            } else {
                out.write(true);
            }
        }
        out.flush();
		
	}
}


