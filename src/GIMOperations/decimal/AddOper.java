package GIMOperations.decimal;

public class AddOper {
	/**
	 * Method to sum of two numbers
	 * @param operand1
	 * @param operand2
	 * @return sum
	 */
	public StringBuffer  add(String operand1,String operand2 ) throws Exception
	{
		if (operand1== null || operand2 == null)
		{
			throw new Exception("One of the operands is null");
		}
		
		StringBuffer sum = new StringBuffer();
		int oper1Size = operand1.length();
		int oper2Size = operand2.length();
		int minSize = Math.min(oper1Size, oper2Size);
		int total =0;
		int remainder =0;
		for (int i=minSize;i>=1;i--)
		{
			total+=(operand1.charAt(oper1Size-1)-48)+(operand2.charAt(oper2Size-1)-48);
			remainder = total%10;
			total = total/10;
			sum.append(remainder);
			oper1Size--;
			oper2Size--;
		}
		if (oper1Size>0)
		{
			for (int i=oper1Size;i>=1;i--)
			{
				total+=(operand1.charAt(oper1Size-1)-48);
				remainder = total%10;
				total = total/10;
				sum.append(remainder);
				oper1Size--;
			}	
		}
		else if (oper2Size>0)
		{
			for (int i=oper2Size;i>=1;i--)
			{
				total+=(operand2.charAt(oper2Size-1)-48);
				remainder = total%10;
				total = total/10;
				sum.append(remainder);
				oper2Size--;
			}	
			
	    }
	    if (total>0)
	    {
	    	sum.append(total);
	    }
	 	return	sum.reverse();
	}

	/**
	 * Method to sum of two numbers
	 * @param operand1
	 * @param operand2
	 * @return sum
	 */
	public StringBuffer  add1(String operand1,String operand2 ) throws Exception
	{
		if (operand1== null || operand2 == null)
		{
			throw new Exception("One of the operands is null");
		}
		
		StringBuffer sum = new StringBuffer();
		int oper1Size = operand1.length();
		int oper2Size = operand2.length();
		int minSize = Math.min(oper1Size, oper2Size);
		int total =0;
		int remainder =0;
		for (int i=minSize;i>=1;i--)
		{
			total+=(operand1.charAt(oper1Size-1)-48)+(operand2.charAt(oper2Size-1)-48);
			remainder = total%10;
			total = total/10;
			sum.append(remainder);
			oper1Size--;
			oper2Size--;
		}
		if (oper1Size>0)
		{
			for (int i=oper1Size;i>=1;i--)
			{
				total+=(operand1.charAt(oper1Size-1)-48);
				remainder = total%10;
				total = total/10;
				sum.append(remainder);
				oper1Size--;
			}	
		}
		else if (oper2Size>0)
		{
			for (int i=oper2Size;i>=1;i--)
			{
				total+=(operand2.charAt(oper2Size-1)-48);
				remainder = total%10;
				total = total/10;
				sum.append(remainder);
				oper2Size--;
			}	
			
	    }
	    if (total>0)
	    {
	    	sum.append(total);
	    }
	 	return	sum.reverse();
	}
}
