import java.io.*;
import java.util.*;

public class Day2 {
	
	private static class Counter
	{
		public int val;
		public Counter()
		{
			val = 1;
		}
		public void addone()
		{
			val++;
		}
	}
	
	public static void main(String[] args)
	{

		part2();
	}
	
	static void part1() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day2.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}
		
		int validPasses = 0;
		
		while(s.hasNextLine())
		{
			String line = s.nextLine();
			
						
			String[] spl = line.split(" ");
			int low = Integer.parseInt(spl[0].split("-")[0]);
			int high = Integer.parseInt(spl[0].split("-")[1]);
			
			String cSearch = spl[1].substring(0, 1);
			
			int charcnt = 0;
			
			char[] car = line.toCharArray();
			for(char c : car)
			{
				if(new Character(c).toString().equals(cSearch))
					charcnt++;
			}
			charcnt--;
			if(charcnt >= low && charcnt <= high)
				validPasses++;
			
		}
		System.out.println(validPasses);

	}
	
	static void part2() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/drew.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}
		
		int validPasses = 0;
		
		while(s.hasNextLine())
		{
			String line = s.nextLine();
			
						
			String[] spl = line.split(" ");
			int first = Integer.parseInt(spl[0].split("-")[0]);
			int second = Integer.parseInt(spl[0].split("-")[1]);
			
			String cSearch = spl[1].substring(0, 1);

			String pass = spl[2];
			
			char[] passarr = pass.toCharArray();
			
			boolean firstContains = new Character(pass.toCharArray()[first - 1]).toString().equals(cSearch);
			boolean secondContains = new Character(pass.toCharArray()[second - 1]).toString().equals(cSearch);
			
			if((firstContains && !secondContains) || (!firstContains && secondContains))
				validPasses++;
			
		}

		System.out.println(validPasses);
	}
}
