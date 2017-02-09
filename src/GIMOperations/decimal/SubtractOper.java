package GIMOperations.decimal;


public class SubtractOper {

	/**
	 * Method to calculate the difference of two numbers
	 * @param operand1
	 * @param operand2
	 * @return difference
	 */
	private StringBuffer  diffOrg(StringBuffer operand1,StringBuffer operand2 ) throws Exception
	{

		if (operand1== null || operand2 == null)
		{
			throw new Exception("One of the operands is null");
		}
		
		StringBuffer diff = new StringBuffer();
		int oper1Size = operand1.length();
		int oper2Size = operand2.length();
		int diffEle =0;
		int ele1;
		int ele2;
		boolean isTakenFromPrevious=false;
		for (int i=oper1Size;i>=1;i--)
		{
			ele1 = operand1.charAt(oper1Size-1)-48;
			ele2 = operand2.charAt(oper2Size-1)-48;
			if (isTakenFromPrevious)
			{
				if (ele1-1>=ele2)
				{
					diffEle = ele1 -1 - ele2;
					diff.append(diffEle);
					isTakenFromPrevious=false;
				}
				else
				{
					if (ele1-1>=0)
					{
						diffEle = 10+ele1-1 - ele2;	
					}
					else
					{
						diffEle = 9 - ele2;
					}
					diff.append(diffEle);				
					isTakenFromPrevious = true;					
				}
			}
			else
			{
				if (ele1>=ele2)
				{
					diffEle = ele1 - ele2;
					diff.append(diffEle);				
				}
				else
				{
					diffEle = 10+ele1 - ele2;
					diff.append(diffEle);				
					isTakenFromPrevious = true;	
				}				
			}
			oper1Size--;
			oper2Size--;
		}
		//return diff.reverse();
		return GIMPSUtils.trim(diff.reverse());
	}
	
	/**
	 * Method to calculate the difference
	 * @param operand1
	 * @param operand2
	 * @return
	 * @throws Exception
	 */
	public StringBuffer  diff(StringBuffer operand1,StringBuffer operand2 ) throws Exception
	{
		operand1 = GIMPSUtils.trim(operand1);
		operand2 = GIMPSUtils.trim(operand2);
		long lengDiff = GIMPSUtils.lengthDiff(operand1, operand2);
		if (lengDiff ==0)
		{
			for (int i =0;i<operand1.length();)
			{
				if (operand1.charAt(i)== operand2.charAt(i))
				{
					i++;
				}
				else if (operand1.charAt(i)> operand2.charAt(i))
				{
					return diffOrg(operand1, operand2);
				}
				else
				{
					return new StringBuffer("-").append(diffOrg(operand2, operand1));
				}
			}			
			return diffOrg(operand1,operand2);
		}
		else if (lengDiff<0)
		{
			return new StringBuffer("-").append(diffOrg(operand2,GIMPSUtils.prepareZeroes(-1*lengDiff).append(operand1) ));
		}
		else	
		{
			return diffOrg(operand1,GIMPSUtils.prepareZeroes(lengDiff).append(operand2));
		}
	}
}