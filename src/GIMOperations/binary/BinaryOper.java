package GIMOperations.binary;

import java.util.Arrays;

public class BinaryOper {

	/**
	 * Method to add two binary numbers. It's calling addSub again. Actual logic is in it
	 * @param firstNum
	 * @param secondNum
	 */
	public byte[] add(byte[] firstNum, byte[] secondNum)
	{
		int firstNumLen = firstNum.length;
		int secondNumLen = secondNum.length;
		
		if (firstNumLen>= secondNumLen)
		{
			return addSub(firstNum, secondNum, firstNumLen, secondNumLen);
		}else
		{
			return addSub(secondNum, firstNum, secondNumLen, firstNumLen);
		}
	}
	
	/**
	 * Method to add two binary numbers. Above method is calling this.
	 * @param firstNum
	 * @param secondNum
	 * @param len1
	 * @param len2
	 */
	public byte[] addSub(byte[] firstNum, byte[] secondNum, int len1,int len2)
	{
		byte remainder=0;
		byte remainderTemp=0;
		byte tempRes[]={};
		int resPos=len1+1;
		byte result[] = new byte[resPos];
		byte modFirstNum=0;
		
		for (int i=0;i<len1;i++)
		{
			if (remainder != 0)
			{
				tempRes = GimPSBinaryUtils.add(firstNum[len1-i-1],  remainder );	
				remainderTemp = tempRes[0];
				modFirstNum = tempRes[1];
				remainder=0;
			}else
			{
				modFirstNum =firstNum[len1-i-1];
			}
			
			if (len2<=i )
			{
				resPos--;
				result[resPos] = modFirstNum;	
			}else
			{
				tempRes = GimPSBinaryUtils.add(modFirstNum, secondNum[len2-i-1]  );
				resPos--;
				result[resPos] = tempRes[1];
			}

			if (remainderTemp!=0)
			{
				remainder = remainderTemp;
				remainderTemp=0;
			}else
			{
				remainder = tempRes[0];	
			}		
		}
		result[0] = remainder;

		return GimPSBinaryUtils.trimLeftZeroes(result) ;
	}
	
	/**
	 * Method to subtract
	 * @param firstNum
	 * @param secondNum
	 */
	public byte[] subtract(byte[] firstNum, byte[] secondNum) throws Exception
	{
		int len1 = firstNum.length;
		int len2 = secondNum.length;
		
		if (GimPSBinaryUtils.compare(firstNum, secondNum)== -1)
		{
			throw new Exception("First number is less than second number");
		}
		
		byte remainder=0;
		byte tempRes[];
		int resPos=len1;
		byte result[] = new byte[resPos];
			
		for (int i=0;i<len1;i++)
		{	
			if (remainder != 0 || i<len2)
			{
				tempRes = GimPSBinaryUtils.subtract((byte) (remainder+firstNum[len1-i-1]), (i<len2?secondNum[len2-i-1]:0) );
				resPos--;
				result[resPos] = tempRes[1];
				remainder = tempRes[0];				
			}
			else
			{
				resPos--;
				result[resPos] = firstNum[len1-i-1];			
			}
		}
		
		return GimPSBinaryUtils.trimLeftZeroes(result) ;
	}
	
	/**
	 * Method to divide two binary numbers and return quotient, remainder
	 * @param dividend
	 * @param divisor
	 * @return result (First array is quotient, second array is remainder)
	 * @throws Exception
	 */
	public byte[][] divide(byte[] dividend, byte[] divisor) throws Exception
	{
		
		byte result[][] = new byte[2][];
		int dividendLen = dividend.length;
		int divisorLen = divisor.length;	
	
		int remainderLen = 0;
		byte[] remainder={};
		byte[] partNum;
		byte[] quotient = new byte[dividendLen];
		int quoPos=-1;
		int noOfPosToBeMoved=0;
		int deltaPos =0;
		boolean goAhead =false ;
		int zeroesPos = GimPSBinaryUtils.ZeroesStartPosFromRight(dividend);
		int currentPos=0;
		
		for(;currentPos<dividendLen;)
		{
			//No.of digits required to process further calculation
			deltaPos = divisorLen - remainderLen; 
			goAhead = false;
			
			// If positions are not exceeding dividend's length and not zeroes from now
			if (currentPos+deltaPos<= dividendLen && !(currentPos == zeroesPos && remainderLen==0) ) 
			{
				partNum =  GimPSBinaryUtils.merge(remainder,  Arrays.copyOfRange(dividend, currentPos, currentPos+deltaPos));
				
				if (GimPSBinaryUtils.compare(partNum, divisor)==-1)
				{
					if (currentPos+deltaPos+1<=dividendLen)
					{
						partNum = GimPSBinaryUtils.merge(remainder, Arrays.copyOfRange(dividend, currentPos, currentPos+deltaPos+1));
						noOfPosToBeMoved  =  deltaPos+1;	
						goAhead=true;
					}
				}
				else
				{
					noOfPosToBeMoved  =  deltaPos;
					goAhead=true;
				}
				if (goAhead)
				{
					if (currentPos!=0) //if It is not in the beginning
					{
						// Add zeros to quotient as we keep of taking the digits from Dividend
						for (int j=1;j<noOfPosToBeMoved;j++) 
						{
							quoPos++;
							quotient[quoPos] = 0;			
						}				
					}
					
					if (GimPSBinaryUtils.compare(partNum, divisor) >=0)
					{
						quoPos++;
						quotient[quoPos] = 1;
						remainder = subtract(partNum, divisor);
						remainderLen = remainder.length;				
					}
					
					currentPos+=noOfPosToBeMoved;								
				}
				else
				{
					break; // If available number is smaller than divisor
				}
			}
			else
			{
				break; //Breaking if no.of digits are not enough to divide or (zeroes from now on the right and remainder is zero)
			}
		}
		
		//After completing division, calculating the remainder or is there anything can be added to Quotient
		
		if (currentPos < dividendLen)
		{
			remainder = GimPSBinaryUtils.merge(remainder,  GimPSBinaryUtils.trimLeftZeroes( Arrays.copyOfRange(dividend, currentPos, dividendLen)) );
			noOfPosToBeMoved = dividendLen - currentPos;

			// Add zeros to quotient as we keep of taking the digits from Dividend
			for (int j=0;j<noOfPosToBeMoved;j++) 
			{
				quoPos++;
				quotient[quoPos] = 0;			
			}				
		}
		quotient = Arrays.copyOfRange(quotient, 0, quoPos+1); // Removing right side zeroes added at initialization
//		System.out.println("In divide : quotient "+ Arrays.toString(quotient));
//		System.out.println("In divide : remainder "+ Arrays.toString(remainder));
		result[0] = GimPSBinaryUtils.trimLeftZeroes(quotient);
		result[1] = GimPSBinaryUtils.trimLeftZeroes(remainder);

		return result;
	}
	
	/**
	 * Method to calculate square root
	 * @param num
	 * @return sqrt
	 */
	//public byte[] approximateSqrt(byte[] num,int len) throws Exception
	public byte[] approximateSqrt(int len) throws Exception
	{
		//num = GimPSBinaryUtils.trimLeftZeroes(num);
		
		int numLen = len;
		int partLen= (len %2 ==0?2:1);

		
	//	int numLen = num.length;
	//	int partLen= (numLen %2 ==0?2:1);
		int pos=0;
		byte result[] = new byte[(len/2)+1];
		//byte result[] = new byte[(num.length/2)+1];
		int resultPos =0;
		byte dividend[], quotient[]=null;
		byte divisor[]= new byte[]{1};
		byte remainder[] = new byte[]{};
		divisor = new byte[]{1};
		
		while (pos<numLen)
		{
			dividend = GimPSBinaryUtils.trimLeftZeroes(GimPSBinaryUtils.merge(remainder,  (partLen==1?new byte[]{1}:new byte[]{1,1})));

			//dividend = GimPSBinaryUtils.trimLeftZeroes(GimPSBinaryUtils.merge(remainder,  Arrays.copyOfRange(num, pos, pos+partLen)));
		//	System.out.println("DIVISOR : "+Arrays.toString(divisor));
		//	System.out.println("DIVIDEND : "+Arrays.toString(dividend));
			if (pos == 0)
			{
				remainder = subtract(dividend, divisor); //Subtracting, not Dividing
				quotient = new byte[]{1};
			}
			else
			{		
				if (GimPSBinaryUtils.compare(GimPSBinaryUtils.merge(divisor, new byte[]{1}), dividend) >0)
				{
					divisor = GimPSBinaryUtils.merge(divisor, new byte[]{0});	

					quotient = new byte[]{0};
					remainder = Arrays.copyOf(dividend, dividend.length);
				}
				else
				{
					divisor = GimPSBinaryUtils.merge(divisor, new byte[]{1});
					quotient = new byte[]{1};
					remainder = subtract(dividend, divisor); //Subtracting not Dividing
				}
			}

			if  (quotient.length ==0)
			{
				quotient = new byte[]{0};
			}
			
			System.arraycopy(quotient, 0, result, resultPos, quotient.length);
			resultPos+= quotient.length;
			pos += partLen;
			partLen = 2;
			divisor = GimPSBinaryUtils.merge( Arrays.copyOfRange(result, 0, resultPos),new byte[]{0});
		}
		
		result = Arrays.copyOfRange(result, 0, resultPos);
//		System.out.println("Quotient : "+ Arrays.toString(result));
//		System.out.println("Remainder length : "+Arrays.toString(GimPSBinaryUtils.trimLeftZeroes(remainder)));
		if ( GimPSBinaryUtils.trimLeftZeroes(remainder).length>0)
		{
			result = add(result, new byte[]{1});
		}
		return result;
	}
}
