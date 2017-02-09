package GIMOperations.decimal;

public class MultiplyOper {

	/**
	 * Method to multiply two numbers
	 * @param operand1
	 * @param operand2
	 * @return
	 * @throws Exception
	 */
	public StringBuffer  multiply(StringBuffer operand1,StringBuffer operand2 ) throws Exception
	{
		if (operand1== null || operand2 == null)
		{
			throw new Exception("One of the operands is null");
		}

		int oper1Size = operand1.length();
		int oper2Size = operand2.length();
		
		if (oper1Size>= oper2Size)
		{
			return multiplyOrg(operand1, operand2,oper1Size,oper2Size);
		}
		else
		{
			return multiplyOrg(operand2, operand1, oper2Size,oper1Size);
		}
	}

	/**
	 * Method to multiply the numbers in optimized way
	 * @param operand1
	 * @param operand2
	 * @param oper1Size
	 * @param oper2Size
	 * @return
	 * @throws Exception
	 */
	private StringBuffer  multiplyOrg(StringBuffer operand1,StringBuffer operand2, int oper1Size,int oper2Size ) throws Exception 
	{
		StringBuffer product = new StringBuffer();
		StringBuffer productTemp = new StringBuffer();
		int y=0;
		int total =0;
		int remainder =0;
		StringBuffer zeroes = new StringBuffer();
		long startTime=0;
		double diffTime=0;
		double totalTimeTaken=0.0d;
		long prevPerc=0;
		long perc=0;
		AddOper addOper = new AddOper();
		
		for (int i = oper2Size-1;i>=0;i--)
		{
			if (startTime==0)
			{
				startTime = System.nanoTime();				
			}

			total = remainder=0;
			 y = operand2.charAt(i)-48;
			 productTemp = new StringBuffer();
			 for (int j= oper1Size-1;j>=0;j--)
			 {
				 total+= ( operand1.charAt(j)-48) * y;
				 remainder = total%10;
				 total = total/10;
				 productTemp.append(remainder);
			 }
			 if (total>0)
			 {
				 productTemp.append(total);
			 }
			 productTemp.reverse();
			 if (zeroes.length()==0)
			 {
				 product.append(productTemp);
			 }
			 else
			 {
				 productTemp.append(zeroes);
				 product = addOper.add(product.toString(), productTemp.toString());
			 }
			 zeroes.append('0');
			 perc = Math.round((( oper2Size-i)*1d/oper2Size)*100);
			 if (perc>prevPerc)
			 {
				 diffTime =((System.nanoTime() - startTime)/1000000000d);
				 totalTimeTaken+=diffTime;
//				 System.out.println(" Completed percentage :"+perc+" Time (seconds) taken is " + diffTime+
//						 "Total time as now : "+totalTimeTaken);
//				 Files.write(Paths.get("log.txt"),( " Completed percentage :"+perc+" Time (seconds) taken is " + diffTime+
//						 "Total time as now : "+totalTimeTaken+"\n").getBytes(),StandardOpenOption.APPEND);
				 prevPerc = perc;
				 startTime=0;
			 }
		}
		
		return GIMPSUtils.trim(product);
	}	
}
