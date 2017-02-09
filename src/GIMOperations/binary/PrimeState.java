package GIMOperations.binary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class PrimeState {

	/**
	 * Method to find next prime
	 * @param currentPrimeExponent
	 * @return
	 * @throws IOException
	 */
	public  int findNextPrime(int currentPrimeExponent) throws IOException
	{
		int nextPrime=-1;
		int num;
		
		if (Files.exists(Paths.get(GimPSBinaryUtils.PRIME_NOS_FILE_NAME)) )
		{
			//System.out.println("File is exists");
			
			List<String> lines =Files.readAllLines(Paths.get(GimPSBinaryUtils.PRIME_NOS_FILE_NAME));
			
			for (int i=0;i<lines.size();i++)
			{
			  	num = Integer.parseInt( lines.get(i));
			  	if (num> currentPrimeExponent)
			  	{
			  		nextPrime = num;
			  		break;
			  	}		  	
			}		
		}
		return nextPrime;
	}
	
	/**
	 * Method to get current divisor
	 * @return
	 * @throws IOException
	 */
	public  byte[] getCurrentDivisor() throws IOException
	{
		if (!Files.exists(Paths.get(GimPSBinaryUtils.CURRENT_DIVISOR)) )
		{
			return new byte[]{1,0};
		}
		else
		{
			byte arr[] = Files.readAllBytes(Paths.get(GimPSBinaryUtils.CURRENT_DIVISOR));
			for (int i=0;i<arr.length;i++)
			{
				arr[i]-=48;
			}
			return arr;
		}
	}
	
	/**
	 * Method to verify if prime number has completed processed
	 * @param currentPrime
	 * @return
	 * @throws IOException
	 */
	public  boolean isCompletedProcessed(String currentPrime) throws IOException
	{
		
		if (!Files.exists(Paths.get(GimPSBinaryUtils.PROCESSED_PRIMES)) )
		{
			return false;
		}
		else
		{
			List<String> processedPrimes =  Files.readAllLines(Paths.get(GimPSBinaryUtils.PROCESSED_PRIMES));
			for (String num:processedPrimes)
			{
				if (num.equalsIgnoreCase(currentPrime))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public  void saveDivisor(byte[] divisor) throws IOException
	{
		byte ONE = 49;
		byte ZERO = 48;
		int len = divisor.length;
		byte modDivisor[] = new byte[len];
		
		for (int i=0;i<len;i++)
		{
			modDivisor[i] = (divisor[i]==1?ONE:ZERO);
		}
		
		Files.write(Paths.get(GimPSBinaryUtils.CURRENT_DIVISOR),modDivisor,StandardOpenOption.CREATE);	
	
	}
}