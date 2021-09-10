import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap; 

public class LZW3{
	HashMap dictionary = new HashMap(); 
	ArrayList<String> output; 
	ArrayList<Integer> output1; 
	String binaryString = ""; 
	int currentNum = 256; 
	
	public LZW3(String fileName) throws IOException
	{
		output = new ArrayList<String>(); 
		output1 = new ArrayList<Integer>(); 
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
			output.add(current);
			dictionary.put(currentPlusNext,currentNum);
			this.NextNum(); 
			current = next;
		}

	}


		if (!current.equals(""))
		{
			output.add(current);
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
		for(int i = 0; i < output.size(); i ++)
		{
			Integer okay = (Integer) dictionary.get(output.get(i));
			
			output1.add(okay);
			
		}
		
		for(int i = 0; i < output1.size(); i++)
		{
		
			String binary1 = Integer.toBinaryString(output1.get(i)); 
			output.set(i,binary1); 
			
			
		}
		
		
		for(int i = 0; i < output1.size(); i++)
		{
		
			binaryString += output.get(i); 
			
			
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


