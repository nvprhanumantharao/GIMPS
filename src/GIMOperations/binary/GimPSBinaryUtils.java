package GIMOperations.binary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class GimPSBinaryUtils {
	
	public static String CURRENT_NUMBER_FILE_NAME="currentPrime.txt";
	public static String PRIME_NOS_FILE_NAME ="primes.txt";
	public static String CURRENT_DIVISOR="divisor.txt";
	public static String PREVIOUS_QUOTIENT="quotient.txt";
	public static String PROCESSED_PRIMES="processedPrimes.txt";
	public static String GIMPS_FILE="GIMPS.txt";
	
	/**
	 * Method to do addition for two binary numbers
	 * @param num1
	 * @param num2
	 * @return byteArray 
	 */
	public static byte[] add(byte num1, byte num2)
	{
		byte[] result =null;
		switch (num1)
		{
				case  0 :   switch(num2)
							{
								case 0 :  result = new byte[]{0,0}; break;
								case 1 :  result = new byte[]{0,1}; break;	
							}
					        break;
					        
				case 1 :    switch(num2)
							{
							case 0 :  result = new byte[]{0,1}; break;
							case 1 :  result = new byte[]{1,0}; break;	
							}
					        break; 
		
		}
		//System.out.println(result[0]+"="+result[1]);
		return result;
		
	}

	/**
	 * Method to do subtraction for two binary numbers
	 * @param num1
	 * @param num2
	 * @return results 
	 */
	public static byte[] subtract(byte num1, byte num2)
	{
		byte[] result =null;
		
		if (num1 >= num2)
		{
			result = new byte[]{0,(byte)(num1 - num2) };
		}
		else if (num1 == 0 && num2 ==1)
		{
			result = new byte[]{-1,1};
		}
		else if (num1 == -1)
		{
			if (num2 == 1)
			{
				result = new byte[]{-1,0};	
			}
			else if (num2 == 0)
			{
				result = new byte[]{-1,1};
			}
		}		
	
		return result;	
	}
	
	/**
	 * Compare two binary numbers
	 * @param num1
	 * @param num2
	 * @return status
	 */
	public static int compare(byte[]num1, byte[]num2)
	{
		int num1Len = num1.length;
		int num2Len = num2.length;
		
		if (num1Len > num2Len)
		{
			return 1;
		}
		else if (num1Len< num2Len)
		{
			return -1;
		}
		else if (num1Len == num2Len)
		{
			for (int i=0;i<num1Len;)
			{
				if (num1[i] == num2[i])
				{
					i++;
				}
				else if (num1[i] > num2[i])
				{
					return 1;
				}
				else
				{
					return -1;
				}
			}
			return 0;
		}	
		return -1;
	}
	
	/**
	 * Metod to trim zeroes from left
	 * @param num1
	 * @return
	 */
	public static byte[] trimLeftZeroes(byte[] num1)
	{
		int len= num1.length;
		int count=0;
		for (int i=0;i<len;i++)
		{
			if (num1[i] == 0)
			{
				count++;
			}
			else
			{
				break;
			}
		}
		return ( count==len? (new byte[]{}) : Arrays.copyOfRange(num1, count, len));
	}
	
	/**
	 * Method to find position from right starting with zeroes
	 * @param num1
	 * @return zeroes starting position
	 */
	public static int ZeroesStartPosFromRight(byte[] num1)
	{
		int len= num1.length;
		int pos=-1;
		for (int i=len-1;i>0;i--)
		{
			if (num1[i] == 0)
			{
				pos =i;
			}
			else
			{
				break;
			}
		}
		return pos;
	}
	
	/**
	 * Method merge two arrays
	 * @param num1
	 * @param num2
	 * @return result
	 */
	public static byte[] merge(byte num1[],byte num2[])
	{
		byte[] result = new byte[num1.length+num2.length];
		

		System.arraycopy(num1, 0, result, 0, num1.length);
		System.arraycopy(num2, 0, result, num1.length, num2.length);
		
		return result;
	}
	
	/**
	 * Method to find whether number is divisible by 2
	 * @param num1
	 * @return
	 */
	public static boolean isDivisibleByTwo(byte[] num1)
	{
		return (num1[num1.length-1] == 0?true:false);
	}
	
	/**
	 * Method to find whether number is divisible by 2
	 * @param num1
	 * @return
	 */
	public static boolean isDivisibleByThree(byte[] num1)
	{
		int oddSum=0,evenSum=0;
		
		for (int i=0;i<num1.length;i++)
		{
			if (i%2==0)
			{
				evenSum+=num1[i];
			}
			else
			{
				oddSum+=num1[i];
			}
		}
		
		return ( (oddSum - evenSum)%3 ==0 ? true : false);
	}
	
	/**
	 * Method to find whether number is divisible by 2
	 * @param num1
	 * @return
	 */
	public static boolean isDivisibleByFive(byte[] num1)
	{
		byte seq[] = {1,2,-1,-2};
		long total=0;
		int numLen = num1.length -1;
		
		for (int i=0;i<num1.length;i++)
		{
			total+= (num1[numLen - i] * seq[i%4]);
		}
		
		return ( total%5 ==0 ? true : false);
	}
	
	/**
	 * Method to find the three factor of the dividend
	 * @param dividend
	 * @param divisor
	 * @return
	 * @throws Exception
	 */
	public static int factorByDivisor(byte[] dividend, byte[] divisor) throws Exception
	{

		BinaryOper oper = new BinaryOper();
		byte[][] divRes = oper.divide(dividend, divisor);
		
		return convertToDecimal(divRes[1]);
	}
	
	public static byte[] binaryNoFromExp(int exp)
	{
		
		byte res[] = new byte[exp];
		for (int i=0;i<exp;i++)
		{
			res[i] = 1;
		}
		
		return res;
	}
	
	/**
	 * Method to convert binary number to decmial
	 * @param binaryNum
	 * @return decmialNumber
	 */
	public static int convertToDecimal(byte[] binaryNum)
	{
		int result = 0;
		int posFact=1;
		int numLen = binaryNum.length;
		if ( numLen != 0)
		{		
			for (int i=numLen-1;i>=0;i--)
			{
				result+= binaryNum[i]*posFact;
				posFact*=2;
			}
		}
		return result;
	}
	public static void saveExponentToFile(byte[] num) throws IOException
	{
		Files.write(Paths.get("primes1.txt"), num,StandardOpenOption.CREATE );
	}
}
