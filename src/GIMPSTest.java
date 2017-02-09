import java.util.Calendar;
import java.util.stream.Stream;

import GIMOperations.decimal.SqrtOper;



public class GIMPSTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	try {
		
		Stream<String> words = Stream.of("HANU","TANU","MINNU");
		int count=0;
		int lineLeng=100;
		new SqrtOper().absSqrt("outline.txt");
//		System.out.println("File length : "+Files.lines(Paths.get("outline.txt")).parallel().mapToInt(str->str.length()).sum());
//		System.out.println("Words count : "+words.mapToInt(i-> i.length()).sum());
//		try (Stream<String> lines = Files.lines(Paths.get("outline.txt"))) {
//		    System.out.println("LAST line : "+lines.skip(0).findFirst().get());
//		}
		//Files.lines(Paths.get("outline.txt")).forEach(count++);
		
//		
//		System.out.println("Started at : "+Calendar.getInstance().getTime());
//		List<String> fileContent=	 Files.readAllLines(Paths.get("out1.txt"));
//		String str =fileContent.get(0); 
//		int len = str.length();
//		System.out.println("Filecontent length  : "+len);
//		int subLen=0;
//		
//		
//		for (int i=0;i<len;i+=lineLeng)
//		{
//			if (i+lineLeng<len)
//			{
//				subLen = i+lineLeng;
//			}
//			else
//			{
//				subLen = len;
//			}
//			Files.write(Paths.get("outline.txt"),(str.substring(i, subLen)+"\n").getBytes(),StandardOpenOption.APPEND);	
//		}
//		
		
			
//			StringBuffer num1 = new StringBuffer(fileContent.get(0).substring(0, 30000));
//			StringBuffer num2 = new StringBuffer("5");
//		//	new SqrtOper().absSqrt(num1);
//			System.out.println(new AddOper().add(fileContent.get(0), fileContent.get(0)).length());
			System.out.println("Ended at : "+Calendar.getInstance().getTime());			
			//System.out.println(new DivideOper().divide(num1, num2));
			
//			Files.readAllLines(Paths.get("file.txt"));
//			String line32 = Files.readAllLines(Paths.get("file.txt")).get(32)
//					For large files:
//
//					try (Stream<String> lines = Files.lines(Paths.get("file.txt"))) {
//					    line32 = lines.skip(31).findFirst().get();
//					}
			
		//		for (int i=1;i<=9000000;i++)
//		{
//			num1.append("1010");
//			num2.append("0954");
//		}
		//SubtractOper oper = new SubtractOper();
	//	System.out.println("Diff : "+ oper.diff(num1, num2) );
//		long startTime = System.nanoTime();
//		Files.write(Paths.get("out1.txt"), oper.diff(num1, num2).toString().getBytes());
//		System.out.println(" Total time taken : "+ ((System.nanoTime() - startTime)/1000000000d));
		 
			
/*		MultiplyOper multiplyOper = new MultiplyOper();
		
		long xcount =16;
		StringBuffer x = new StringBuffer("65536");
		for (int i =0;i<25;i++)
		{
			x = multiplyOper.multiply(x, x);
			xcount*=2;
			System.out.println("2^"+xcount);
			Files.write(Paths.get("log.txt"),("2^"+xcount+"\n").getBytes(),StandardOpenOption.APPEND);			
			Files.write(Paths.get("out1.txt"), x.toString().getBytes());	
			//System.out.println(x);
		}
*/
	  //  System.out.println(multiplyOper.multiply("65536", "65536"));
	//	System.out.println("Add : "+new AddOper().add(num1.toString(), num2.toString()).toString());
	//	System.out.println("Diff : "+oper.diff(num1.toString(), num2.toString()).toString());
//		GeneralMath generalMath = new GeneralMath();
//		System.out.println(generalMath.isFirstGreater(num2.toString(), num1.toString()));
//		System.out.println("First number length : "+num1.length());
//		System.out.println("Second number length : "+num2.length());
//		MultiplyOper multiplyOper = new MultiplyOper();
//		Files.write(Paths.get("out1.txt"), multiplyOper.multiply(num1.toString(), num2.toString()).toString().getBytes());
//		Files.write(Paths.get("out1.txt"), oper.multiply(num1.toString(), num2.toString()).toString().getBytes(),
//				StandardOpenOption.APPEND);
	//	System.out.println(" Sum of "+num1+"\n , "+ num2+"\n are \n"+ oper.add(num1.toString(), num2.toString()));
		//System.out.println("Product of "+num1+"\n , "+ num2+"\n are \n"+ oper.multiply(num1.toString(), num2.toString()));
		
//		long biggest = 57885161L;
//		int i;
//		String x="18446744073709551616";
//		for ( i =64 ;i<=1280000;i=i*2)
//		{
//			x=oper.multiply(x, x).toString();
//			System.out.println("i : "+i);
//			System.out.println("Length : "+x.length());
//		}
//		System.out.println("i : "+i);
//		System.out.println(x);
//		System.out.println("Length : "+x.length());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
