import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class GIMPSSearch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long biggest = 57885161L;

		try {
			int value=0;
			int storeVal=0;
			int remainder=0;	
			ArrayList<String> orgVal = new ArrayList<String>();
			ArrayList<String> newVal = new ArrayList<String>();
			File inputFile = new File("D://x.txt");
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			orgVal.add(reader.readLine());
			reader.close();
			reader=null;
			
			for (int i =1 ; i<=biggest;i++)
			{
				value=storeVal=remainder=0;
				for (int j=0;j< orgVal.size();j++)
				{
						value = remainder+ (new Integer(orgVal.get(j))*2); 
						storeVal = value%10;
						remainder = value/10;
						newVal.add(storeVal+"");
				}
				if (remainder>0)
				{
					newVal.add(remainder+"");
				}
				System.out.println("Pos : "+i+" Digits : "+ newVal.size());
				orgVal = newVal;
				newVal = new ArrayList<String>();
			}
//			File outFile = new File("D://out.txt");				
//			BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
//			writer.close();
//			writer = null;
//			inputFile.delete();
//			outFile.renameTo(new File("D://x.txt"));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException ioe)
		{
			System.out.println("IOException : "+ioe.toString());
		}
		
		System.out.println("File created");
	}

}
