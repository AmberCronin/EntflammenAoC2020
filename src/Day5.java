import java.io.*;
import java.util.*;

public class Day5 {
	public static void main(String[] args)
	{
		part1();
		part2();
	}


	private static void part1() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day5.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}
		
		List<String> planes = new ArrayList<String>();

		int highestSeatID = 0;
		
		while(s.hasNextLine())
		{
			int row, column;
			
			String plane = s.nextLine();
			int lower = 0, upper = 127;
			
			for(int i = 0; i < 7; i++)
			{
				char mv = plane.charAt(i);
				int cnt = (upper + lower) / 2;
				if(mv == 'F')
				{
					upper = cnt;
				}
				else // mv == 'B'
				{
					lower = cnt+1;
				}
			}
			
			row = lower;
			upper = 7;
			lower = 0;
			for(int i = 7; i < 10; i++)
			{
				char mv = plane.charAt(i);
				int cnt = (upper + lower) / 2;
				if(mv == 'L')
				{
					upper = cnt;
				}
				else // mv == 'B'
				{
					lower = cnt+1;
				}
			}
			column = lower;
			int seatID = row * 8 + column;
			if(seatID > highestSeatID)
				highestSeatID = seatID;
		}
		System.out.println(highestSeatID);
		
		
	}
		
		
		
	
	private static void part2() {
		HashSet<Integer> foundSeats = new HashSet<Integer>();
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day5.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}
		
		List<String> planes = new ArrayList<String>();
		while(s.hasNextLine())
		{
			int row, column;
			
			String plane = s.nextLine();
			int lower = 0, upper = 127;
			
			for(int i = 0; i < 7; i++)
			{
				char mv = plane.charAt(i);
				int cnt = (upper + lower) / 2;
				if(mv == 'F')
				{
					upper = cnt;
				}
				else // mv == 'B'
				{
					lower = cnt+1;
				}
			}
			
			row = lower;
			upper = 7;
			lower = 0;
			for(int i = 7; i < 10; i++)
			{
				char mv = plane.charAt(i);
				int cnt = (upper + lower) / 2;
				if(mv == 'L')
				{
					upper = cnt;
				}
				else // mv == 'B'
				{
					lower = cnt+1;
				}
			}
			column = lower;
			int seatID = row * 8 + column;
			foundSeats.add(seatID);
		}

		int pnt = 1*8;
		for(int i = 2; i < 126; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if(!foundSeats.contains(i*8+j))
					System.out.println(i*8+j);
			}
		}
	}

	
	
	
}
