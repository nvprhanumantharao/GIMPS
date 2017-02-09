package GIMOperations.decimal;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.stream.Stream;


public class SqrtOper {
	
	/**
	 * Method to calculate nearest square root and remainder
	 * @param number
	 * @return
	 * @throws Exception
	 */
	public StringBuffer absSqrt(String fileName ) throws Exception
	{

		int fileLineCount=1;
		int totalFileLen = Files.lines(Paths.get(fileName)).parallel().mapToInt(str->str.length()).sum();
		StringBuffer number = GIMPSUtils.trim(readLine(fileName, fileLineCount)); 
		int actualLineLeng=number.length();
		int start =0;
		int pos=0;
		int i=0;
	
		StringBuffer numPart;
		StringBuffer quotient = new StringBuffer();
		StringBuffer subQuotient = new StringBuffer();
		StringBuffer preVal = new StringBuffer();
		StringBuffer diff = new StringBuffer();
		StringBuffer product = new StringBuffer();
		StringBuffer preProduct = new StringBuffer();
		
		ArrayList<StringBuffer> values = getNumbers();

		MultiplyOper multiplyOper = new MultiplyOper();
		SubtractOper subtractOper = new SubtractOper();
		
		long startTime=0;
		double diffTime=0;
		double totalTimeTaken=0.0d;
		long prevPerc=0;
		long perc=0;
		
		if (startTime==0)
		{
			startTime = System.nanoTime();				
		}		
		if (totalFileLen % 2 ==0)
		{
			start=pos=2;
			numPart = new StringBuffer(number.substring(0, pos));
		}
		else
		{
			start=pos=1;			
			numPart = new StringBuffer(number.substring(0, pos));
		}
		
		for( i=0;i<10;i++)
		{
			diff = subtractOper.diff(numPart, multiplyOper.multiply(values.get(i), values.get(i)) );
			if (diff.charAt(0)!='-')
			{
				preVal = values.get(i);
			}
			else
			{
				break;
			}
		}
		
		quotient.append(preVal);
		numPart = GIMPSUtils.trim(subtractOper.diff(numPart, multiplyOper.multiply(preVal, preVal)));
		
		for (;start<totalFileLen;start+=2,pos+=2)
		{
			//Appending two numbers from actual number
			if (pos+2>actualLineLeng)
			{
				if (actualLineLeng - pos>0)
				{
					number = new StringBuffer(number.substring(pos, actualLineLeng));					
				}
				else
				{
					number = new StringBuffer();
				}
				fileLineCount++;
				number.append(readLine(fileName, fileLineCount));
				actualLineLeng = number.length();
				System.gc();
				System.out.println("Quotient length : "+quotient.length()+ " Number length : "+actualLineLeng);
				pos=0;
			}

			numPart = GIMPSUtils.trim(numPart.append(new StringBuffer(number.substring(pos, pos+2))));
			
		//	System.out.println(numPart+" Begin");
			// Multiply quotient with 2
			//System.out.println("BEFORE Quotient length : "+quotient.length());
			subQuotient = multiplyOper.multiply(quotient, values.get(1));
			//System.out.println("AFTER Quotient length : "+quotient.length());

			for (i=0;i<9;i++)
			{
			//	System.out.println("SubQuotient : "+subQuotient+" "+i);
				product= multiplyOper.multiply(new StringBuffer(subQuotient).append(values.get(i)), values.get(i));
			//	System.out.println("TempVal : "+product);
				if (GIMPSUtils.compare(numPart, product)<0)
				{
					if (i==0)
					{
						preVal = new StringBuffer("0");
					}
					break;
				}
				else
				{
					preVal = values.get(i);
					preProduct = product;
				}
			}
			
			quotient.append(preVal);
			if (preVal.charAt(0)!='0')
			{
				numPart = GIMPSUtils.trim(subtractOper.diff(numPart, preProduct));
			}
		//	System.out.println(numPart+" end");		
			 perc = Math.round((( start)*1d/totalFileLen)*100);
			 if (perc>prevPerc)
			 {
				 diffTime =((System.nanoTime() - startTime)/1000000000d);
				 totalTimeTaken+=diffTime;
				 System.out.println(" Completed percentage :"+perc+" Time (seconds) taken is " + diffTime+
				 "Total time as now : "+totalTimeTaken);
				 prevPerc = perc;
				 startTime=System.nanoTime();
			 }			
		}
		System.out.println("Remainder "+ numPart);
		System.out.println("Nearest square root : "+quotient);
		quotient.append(",").append(numPart);
		Files.write(Paths.get("sample.txt"),(quotient+"\n").getBytes(),StandardOpenOption.APPEND);
		return	quotient;
	}
	/**
	 * Method to return arraylist of divisors
	 * @return ArrayList
	 */
	public ArrayList<StringBuffer> getNumbers()
	{
		ArrayList<StringBuffer> values = new ArrayList<StringBuffer>();
		values.add(new StringBuffer("1"));		
		values.add(new StringBuffer("2"));
		values.add(new StringBuffer("3"));
		values.add(new StringBuffer("4"));
		values.add(new StringBuffer("5"));
		values.add(new StringBuffer("6"));
		values.add(new StringBuffer("7"));
		values.add(new StringBuffer("8"));
		values.add(new StringBuffer("9"));
		
		return values;
	}

	public StringBuffer readLine(String fileName, int lineNo) throws Exception
	{
		try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
			System.out.println("Line number : "+lineNo);
		    return new StringBuffer(lines.skip(lineNo-1).findFirst().get());
		}
	}
}
