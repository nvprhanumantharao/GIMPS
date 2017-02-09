package GIMOperations.decimal;

public class GIMPSUtils {
	/**
	 * Method to prepare zeroes
	 * @param leng
	 * @return zeroes
	 */
	public static StringBuffer prepareZeroes(long leng)
	{
		StringBuffer str = new StringBuffer("");
		for(int i=0;i<leng;i++)
		{
			str.append("0");
		}
		return str;
	}

	/**
	 * Method to trim zeroes and spaces in a number
	 * @param oper1
	 * @return
	 */
	public static StringBuffer trim(StringBuffer oper1)
	{
		StringBuffer str = new StringBuffer(oper1.toString().trim().replaceFirst("0*", ""));
		return (str.length()==0? new StringBuffer("0"):str);
	}
	/**
	 * Method to trim zeroes and spaces in a number
	 * @param oper1
	 * @return
	 */
//	public static StringBuffer trim(StringBuffer oper1)
//	{
//		System.out.println(oper1.toString().trim().replaceFirst("0*", "").length());
//		return new StringBuffer(oper1.toString().trim().replaceFirst("0*", ""));
//	}
	
	/**
	 * Method to calculate the length difference
	 * @param oper1
	 * @param oper2
	 * @return lengthDiff
	 */
	public static long lengthDiff(StringBuffer oper1, StringBuffer oper2)
	{
		return trim(oper1).length() - trim(oper2).length();
	}
	
	/**
	 * Method to compare two numbers
	 * @param operand1
	 * @param operand2
	 * @return compareStatus
	 * @throws Exception
	 */
	public static int  compare(StringBuffer operand1,StringBuffer operand2 ) throws Exception
	{
		operand1 = trim(operand1);
		operand2 = trim(operand2);
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
					return 1;
				}
				else
				{
					return -1;
				}
			}			
			return 0;
		}
		else if (lengDiff<0)
		{
			return -1;
		}
		else	
		{
			return 1;
		}
	}
}
