import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;



public class Display {



	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) {
	
	//	StringBuffer str = new StringBuffer("000000920101010000");
		//str.trimToSize();
		String str ="000000920101010000";
		str=str.replaceFirst("0*", "");
		System.out.println(str);
		
	}	
	
	public void writeToFile() throws IOException 
	{
		Files.write(Paths.get("out1.txt"), "something\n".getBytes(),StandardOpenOption.APPEND);
		List<String> lines =Files.readAllLines(Paths.get("out1.txt"));
		lines.forEach(str->System.out.println(str));
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main1(String[] args) throws IOException {
		long biggest = 100;
		
		File outFile = new File("out1.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));		
			for (long i =1 ; i<=biggest;i++)
			{
				writer.write("something");
				writer.newLine();
				//writer.write("1");
			}
				writer.close();
				writer = null;
				outFile=null;

		System.out.println("File created");
		System.out.println("File started reading");
		outFile = new File("out1.txt");
		BufferedReader reader = new BufferedReader(new FileReader(outFile));
		String value = reader.readLine();
		System.out.println("Value length : "+value.length());
		reader.close();
		reader=null;
		outFile=null;
		System.out.println("Reading done");
	}
	
	
}
