package GIMOperations.decimal;

import java.util.ArrayList;


public class DivideOper {
	
//	/**
//	 * Method to divide 
//	 * @param operand1
//	 * @param operand2
//	 * @return
//	 */
//	public StringBuffer  divideOld(StringBuffer dividend,StringBuffer divisor ) throws Exception
//	{
//		ArrayList<StringBuffer> values = getNumbers();
//		StringBuffer quotient  = new StringBuffer();
//		StringBuffer remainder = new StringBuffer();
//		StringBuffer tempPreVal;
//		StringBuffer tempVal;		
//		StringBuffer tempQuotient;
//		StringBuffer divPart ;
//		
//		int divisorLen = divisor.length();
//		int dividendLen = dividend.length();
//		int dividendStartPos=0;
//		int rep;
//		int digitstoAdd=0;
//
//		MultiplyOper multiplyOper = new MultiplyOper();
//		SubtractOper subtractOper = new SubtractOper();
//				
//		if (divisorLen> dividendLen)
//		{
//			return new StringBuffer("0").append(",").append(dividend);
//		}
//		else
//		{
//			while (dividendStartPos<dividendLen)
//			{
//				digitstoAdd = divisorLen - 
//						(remainder.toString().equalsIgnoreCase("0") ||	remainder.length()==0?0:remainder.length());
//				System.out.println("Digits : "+digitstoAdd);
//				if (dividendStartPos+	digitstoAdd > dividendLen)
//				{
//					for (rep=1;rep<=dividendLen-dividendStartPos;rep++)
//					{
//						quotient.append("0");
//					}
//					return quotient.append(",").append(dividend.substring(dividendStartPos));
//				}
//				if ( dividendStartPos !=0 && digitstoAdd ==0 )
//				{
//					divPart = new StringBuffer(remainder);
//				}else
//				{
//					divPart = new StringBuffer(dividend.substring(dividendStartPos, dividendStartPos+	digitstoAdd));					
//				}
//
//				System.out.println(divPart.toString()+"-"+digitstoAdd);
//				dividendStartPos +=	digitstoAdd;
//				
//				if (quotient.length()!=0 && !quotient.toString().equalsIgnoreCase("0") && digitstoAdd>1)
//				{
//					for (rep=1;rep<digitstoAdd;rep++)
//					{
//						quotient.append("0");
//					}
//				}
//				
//				if (GIMPSUtils.compare(divPart, divisor)<0)
//				{
//					if (quotient.length()!=0 && !quotient.toString().equalsIgnoreCase("0"))
//					{
//						quotient.append("0");
//					}
//					if (dividendStartPos<dividendLen)
//					{
//						divPart.append(dividend.charAt(dividendStartPos));
//						dividendStartPos++;
//					}
//					else
//					{
//						return (quotient.length()==0?new StringBuffer("0"):quotient).append(",").
//								append(GIMPSUtils.trim(divPart));
//					}
//				}
//				
//				tempPreVal = new StringBuffer(divisor);
//				tempQuotient = new StringBuffer("1");
//				for (rep=0;rep<8;rep++)
//				{
//					tempVal = multiplyOper.multiply(divisor, values.get(rep) );
//					//System.out.println(divPart+" "+tempVal.toString()+" Tempval "+GIMPSUtils.compare(divPart,tempVal));
//					if (GIMPSUtils.compare(divPart,tempVal)>=0)
//					{
//						tempPreVal = tempVal;
//						tempQuotient = values.get(rep);
//					}else
//					{
//						break;
//					}
//				}
//
//				remainder = GIMPSUtils.trim(subtractOper.diff(divPart, tempPreVal));
//				System.out.println(divPart+" Preval : "+tempQuotient+" "+remainder.toString()+" "+remainder.charAt(0));				
//				if (remainder!=null && remainder.charAt(0) !='-')
//				{
//					quotient.append(tempQuotient);
//				}
//				else
//				{
//					throw new Exception("Error while dividing : "+dividend+ " , "+divisor);
//				}				
//				System.out.println("***"+quotient.toString()+ " "+remainder.toString());
//			}
//
//			
//			
//		}
//		return quotient.append(",").append(remainder);
//	}
	
	/**
	 * Method to return arraylist of divisors
	 * @return ArrayList
	 */
	public ArrayList<StringBuffer> getNumbers()
	{
		ArrayList<StringBuffer> values = new ArrayList<StringBuffer>();
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
	
	/**
	 * Method to divide 
	 * @param operand1
	 * @param operand2
	 * @return
	 */
	public StringBuffer  divide(StringBuffer dividend,StringBuffer divisor ) throws Exception
	{
		dividend = GIMPSUtils.trim(dividend);
		divisor = GIMPSUtils.trim(divisor);
		ArrayList<StringBuffer> values = getNumbers();
		StringBuffer quotient  = new StringBuffer();
		StringBuffer remainder = new StringBuffer();
		StringBuffer tempPreVal;
		StringBuffer tempVal;		
		StringBuffer tempQuotient;
		StringBuffer divPart = new StringBuffer();
		
		int dividendLen = dividend.length();
		int dividendStartPos=0;
		int rep;

		MultiplyOper multiplyOper = new MultiplyOper();
		SubtractOper subtractOper = new SubtractOper();
		
		while (dividendStartPos<dividendLen)
		{
			divPart.append(dividend.charAt(dividendStartPos));
			//System.out.println("DivPart : "+divPart.toString()+" Divisor : "+divisor+ " "+GIMPSUtils.compare(divPart, divisor));
			if (GIMPSUtils.compare(divPart, divisor)< 0)
			{
				if (quotient.length()!=0 || !quotient.toString().equalsIgnoreCase("0"))
				{
					quotient.append("0");
				}
			}
			else
			{
				//System.out.println("Inelse part");
				tempPreVal = new StringBuffer(divisor);
				tempQuotient = new StringBuffer("1");
				for (rep=0;rep<8;rep++)
				{
					tempVal = multiplyOper.multiply(divisor, values.get(rep) );
					//System.out.println(divPart+" "+tempVal.toString()+" Tempval "+GIMPSUtils.compare(divPart,tempVal));
					if (GIMPSUtils.compare(divPart,tempVal)>=0)
					{
						tempPreVal = tempVal;
						tempQuotient = values.get(rep);
					}else
					{
						break;
					}
				}
				remainder = GIMPSUtils.trim(subtractOper.diff(divPart, tempPreVal));
				
				if (remainder!=null && remainder.charAt(0) !='-')
				{
					quotient.append(tempQuotient);
				}
				else
				{
					throw new Exception("Error while dividing : "+dividend+ " , "+divisor);
				}				
				divPart = remainder;
			}
			divPart = GIMPSUtils.trim(divPart);
			//System.out.println("Dividend : "+divPart+" Quotient : "+quotient+" Remainder : "+remainder);
			dividendStartPos++;
		}
		//System.out.println(quotient+" --- "+remainder);
		divPart= GIMPSUtils.trim(divPart);
		if (!divPart.toString().equalsIgnoreCase("0"))
		{
			remainder = divPart; 
		}
		return GIMPSUtils.trim(quotient).append(",").append(GIMPSUtils.trim(remainder));
	}	
}
