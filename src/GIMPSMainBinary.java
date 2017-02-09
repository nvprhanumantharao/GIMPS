import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

import GIMOperations.binary.BinaryOper;
import GIMOperations.binary.GimPSBinaryUtils;
import GIMOperations.binary.PrimeState;

public class GIMPSMainBinary {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		for (int i=0;i<150;i++)
		{
			try
			{
				findState();			
			}catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("Errr : "+e.toString());
			}		
		}
	}

	public static void findState() throws Exception
	{
		
		PrimeState primeState = new PrimeState();
		int currentPrimeExponent=0;
		boolean isProcessed;
		byte[] divisor=null ;
		
		if (Files.exists(Paths.get(GimPSBinaryUtils.CURRENT_NUMBER_FILE_NAME)) )
		{
			//System.out.println("File is exists");
			
			List<String> lines =Files.readAllLines(Paths.get(GimPSBinaryUtils.CURRENT_NUMBER_FILE_NAME));
			for (String num:lines)
			{
				if (num!=null && num.length()>0)
				{
					currentPrimeExponent = Integer.parseInt(num);	
				}			
			}		
		}
		
		isProcessed = primeState.isCompletedProcessed(currentPrimeExponent+"");
		
		if (currentPrimeExponent ==0 || (currentPrimeExponent!=0 && isProcessed==true)) //If prime exponent is zero or it's processed completely
		{
			int nextPrimeExponent = primeState.findNextPrime(currentPrimeExponent);
			if (nextPrimeExponent== -1)
			{
				throw new Exception("Not able to find next prime and current prime is : "+currentPrimeExponent);
			}
			else
			{
				// Setting up data to start processing next prime
				Files.write(Paths.get(GimPSBinaryUtils.CURRENT_NUMBER_FILE_NAME), 
				new String(nextPrimeExponent+"").getBytes(),StandardOpenOption.CREATE );
				currentPrimeExponent = nextPrimeExponent;
				divisor= new byte[]{1,0};
			}							
		}
		else if (currentPrimeExponent !=0 && isProcessed==false ) // If the prime is still need to process
		{
				 divisor =primeState.getCurrentDivisor();
		}		
	
		if (currentPrimeExponent!=0 && divisor != null)
		{
			compute(currentPrimeExponent,divisor);
		}
		else
		{
			throw new Exception("Not able to continue : Prime exp :"+currentPrimeExponent+", divisor : "+divisor);
		}
		//process starts here
		//Files.write(Paths.get("primes1.txt"), "asdfasdf".getBytes(),StandardOpenOption.CREATE );
	}
	
	/**
	 * Method to compute whether a binary number is prime or not
	 * @param currentPrimeExponent
	 * @param divisor
	 * @throws Exception
	 */
	public static void compute(int currentPrimeExponent, byte[] divisor) throws Exception
	{
		byte divRes[][]=null;
		byte dividend[] =GimPSBinaryUtils.binaryNoFromExp(currentPrimeExponent);
		
		BinaryOper oper = new BinaryOper();
		PrimeState primeState = new PrimeState();
		
		int twoFactor=    GimPSBinaryUtils.factorByDivisor(divisor,new byte[]{1,0});
		int threeFactor = GimPSBinaryUtils.factorByDivisor(divisor,new byte[]{1,1});
		int fiveFactor =  GimPSBinaryUtils.factorByDivisor(divisor,new byte[]{1,0,1});
		int incr=0;
		
		primeState.saveDivisor(divisor);
		
		do
		{
			System.out.println(Arrays.toString(divisor));
			if (twoFactor!=0 && threeFactor!=0 && fiveFactor!=0 )
			{
					divRes = oper.divide(dividend, divisor);
					if (divRes[1].length==0)
					{
						break;
					}
			}
	
			if (twoFactor==0)
			{
				divisor = oper.add(divisor, new byte[]{1});
				incr=1;
			}
			else
			{
				divisor = oper.add(divisor, new byte[]{1,0});
				incr=2;						
 			}		
			primeState.saveDivisor(divisor);

			twoFactor = (twoFactor+incr)%2;
			threeFactor = (threeFactor+incr)%3;
			fiveFactor = (fiveFactor+incr)%5;
		
		}while (divRes==null || (divRes!=null && divRes.length==2 && GimPSBinaryUtils.compare(divisor, divRes[0])<1) );
		
		if (GimPSBinaryUtils.compare(divisor, divRes[0])>=1)
		{
			System.out.println(currentPrimeExponent+" is GIMPS");
			Files.write(Paths.get(GimPSBinaryUtils.GIMPS_FILE), new String(currentPrimeExponent+"\n").getBytes(), StandardOpenOption.APPEND);
		}
		else
		{
			System.out.println(currentPrimeExponent+" is not GIMPS");
		}
		Files.write(Paths.get(GimPSBinaryUtils.PROCESSED_PRIMES), new String(currentPrimeExponent+"\n").getBytes(), StandardOpenOption.APPEND);
		Files.deleteIfExists(Paths.get(GimPSBinaryUtils.CURRENT_DIVISOR));
	}
	
}
