package test;

import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.log4j.FileAppender;
import org.apache.log4j.SimpleLayout;

import GIMOperations.binary.BinaryOper;
import GIMOperations.binary.GimPSBinaryUtils;
import GIMOperations.binary.PrimeState;

public class BinaryOperTest {


//	private static final LogManager logManager = LogManager.getLogManager();

	public static void main(String[] args) throws Exception{
		
		byte num1[] = {1,0};
		byte num2[] = {1};
		
		BinaryOper oper = new BinaryOper();
		byte result[] = null;
		
		System.out.println("num1 : "+Arrays.toString(num1));
		result = oper.add(num1, num2);	
		System.out.println("Addition : "+ Arrays.toString(result));
		
		System.out.println("num1 : "+Arrays.toString(num1));
		
		result = oper.subtract(num1, num2);	
		System.out.println("\n Difference : "+Arrays.toString(result)); 
		
		System.out.println("Compare : "+GimPSBinaryUtils.compare(new byte[]{1,0,0,0,1}, new byte[]{1,0,0,1,0}));
		
		System.out.println("num1 : "+Arrays.toString(num1));
		System.out.println("num2 : "+Arrays.toString(num2));
		byte divResult[][] = oper.divide(num1, num2);
		System.out.println("\n Quotient : "+Arrays.toString(divResult[0]));
		System.out.println("\n remainder : "+Arrays.toString(divResult[1]));
		
		byte num[]={1,1,0,0,1,1,1};
		System.out.println("sqrt of "+Arrays.toString(num));
		long startTime = System.currentTimeMillis();
		System.out.println("STart time : "+startTime);
		System.out.println("Sqrt : "+ oper.approximateSqrt(1001).length);
		long endTime = System.currentTimeMillis();
		System.out.println("End time : "+endTime);
		double diffTime =((endTime - startTime)/1000d);
		System.out.println("Time taken : "+diffTime);

		int len = 74207281;
		byte arr[] = new byte[len];
		for (int i=0;i<len;i++)
		{
			arr[i] = (byte)1;
		}
		byte arr2[] = new byte[2];
		for (int i=0;i< arr2.length;i++)
		{
			arr2[i] = (i%2==0?(byte)1:(byte)1 );
		}
		startTime =System.currentTimeMillis();
		System.out.println("Start : "+startTime);		
		byte res1[][] = oper.divide(arr,arr2);
		
		byte quotient[] =res1[0];
		int quoLen = quotient.length/1000;
		/*
		for (int i=0;i<quoLen;i++)
		{
			Files.write(Paths.get("res.txt"),Arrays.copyOfRange(quotient, i*1000, (i*1000)+1000),StandardOpenOption.APPEND);
		}
		int remainder =quoLen %1000;
		if (remainder !=0)
		{
			Files.write(Paths.get("res.txt"),Arrays.copyOfRange(quotient, quoLen*1000, (quoLen*1000)+quoLen),StandardOpenOption.APPEND);
		}*/
		System.out.println("\n Quotient : "+res1[0].length);
		System.out.println("\n remainder : "+res1[1].length);				;
		System.out.println("Processing : "+(System.currentTimeMillis()-startTime)/1000d);
		
		System.out.println("Trimming zeroes from left : "+Arrays.toString( GimPSBinaryUtils.trimLeftZeroes(new byte[]{1,1,0,0,1})));
		PrimeState prim = new PrimeState();
		byte arr1[] = new byte[]{1,1,1,1,1,1,0,1,0,1,1,0,1,1,0,1};
		
		prim.saveDivisor(arr1);
		
		/*
	    Logger LOGGER = Logger.getLogger(BinaryOperTest.class.getName());
	    Appender  fh = new FileAppender(new SimpleLayout(), "MyLogFile.log");
		//FileHandler fh  = new FileHandler("./MyLogFile.log");
		LOGGER.addHandler(fh);
	    SimpleFormatter formatter = new SimpleFormatter();  
        fh.setFormatter(formatter);  
        LOGGER.setUseParentHandlers(false);
	        // the following statement is used to log any messages  
        LOGGER.info("My first log"); 
        LOGGER.info(Integer.toBinaryString(100));
        LOGGER.info(Integer.toBinaryString(20000000));
        */
		System.out.println(Integer.toBinaryString(100));
		System.out.println(Long.toBinaryString(20000000));
		
	}

}
