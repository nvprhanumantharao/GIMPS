import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class PrimeNumWithInRange {

	public static void main(String[] args) throws Exception
	{
		
		//Files.write(Paths.get("primes1.txt"), "asdfasdf".getBytes(),StandardOpenOption.CREATE );
		int fileCount=24;
	    final int FILE_SIZE=10000000; //10MB file size
	    int currentSize=0;
		String fileName;
		StringBuffer fileContent = new StringBuffer();
		StringBuffer contentDecmial = new StringBuffer();
		String binaryStr;
		long primeCount=0;
		long prevPrime=1073741789;
		long diff =0;
		long max = 5L*1024L*1024L*1024L; // 5 gb number
		for (long i=prevPrime+1;i<=max;i++)
		{
			if (isPrime(i))
			{
				diff = i-prevPrime;
				binaryStr = Long.toBinaryString(diff);
				fileContent.append(binaryStr+"\n");
				currentSize+= binaryStr.length();
				contentDecmial.append(diff+"\n");
				prevPrime = i;
				primeCount++;
				System.out.println("Prime : "+i + " Binary : "+binaryStr+" No.of primes until : "+primeCount);
			}
			
			if (currentSize> FILE_SIZE)
			{
				fileCount++;
				fileName=".//primes/primes"+fileCount+".txt";
				Files.write(Paths.get(fileName),fileContent.toString().getBytes(),StandardOpenOption.CREATE );
				Files.write(Paths.get(".//primesDecimal/primes"+fileCount+".txt"),contentDecmial.toString().getBytes(),StandardOpenOption.CREATE );

				Files.write(Paths.get(fileName),Long.toBinaryString(prevPrime).getBytes(),StandardOpenOption.APPEND );
				Files.write(Paths.get(".//primesDecimal/primes"+fileCount+".txt"),(prevPrime+"").getBytes(),StandardOpenOption.APPEND );
				
				fileContent= new StringBuffer();
				contentDecmial = new StringBuffer();
				currentSize=0;
			}
		}
		
		if (fileContent.length()>0)
		{
			fileCount++;
			fileName=".//primes/primes"+fileCount+".txt";
			Files.write(Paths.get(fileName),fileContent.toString().getBytes(),StandardOpenOption.CREATE );
			Files.write(Paths.get(".//primesDecimal/primes"+fileCount+".txt"),contentDecmial.toString().getBytes(),StandardOpenOption.CREATE );
			
			Files.write(Paths.get(fileName),Long.toBinaryString(prevPrime).getBytes(),StandardOpenOption.APPEND );
			Files.write(Paths.get(".//primesDecimal/primes"+fileCount+".txt"),(prevPrime+"").getBytes(),StandardOpenOption.APPEND );
			
			fileContent= new StringBuffer();
			contentDecmial = new StringBuffer();
			currentSize=0;
		}
	}

	/**
	 * Method to check whether given number is prime
	 * @param number
	 * @return
	 */
	public static boolean isPrime(long number)
	{
		int threeCount=1;
		long endNumber = Math.round(Math.sqrt(number*1.0d));
	
		if (number % 2 ==0) return false;
		if (number % 3 ==0) return false;
		if (number % 5 ==0) return false;
		
		for (long i=7;i<=endNumber;i+=2)
		{
			if (threeCount!=0 && isNotDivisibleBy5(number+"") )
			{
				if (number % i==0) return false;
			}
			threeCount = (threeCount+2) % 3;
		}
		return true;
	}
	
	/**
	 * Method to check whether given number is not divisible by 5
	 * @param number
	 * @return
	 */
	public static boolean isNotDivisibleBy5(String number)
	{
		if (number.charAt(number.length()-1) == '5' || number.charAt(number.length()-1) == '0' )
		{
			return false;
		}
		return true;
	}
}
