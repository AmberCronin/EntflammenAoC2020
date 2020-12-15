import java.io.*;
import java.math.*;
import java.util.*;

public class Day10 {
	public static void main(String[] args) {
		//part1();
		part2();
	}

	private static void part1() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day10.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}

		LinkedList<Integer> adaptors = new LinkedList<Integer>();
		
		int cnt = 0;
		while(s.hasNextLine()) {
			String line = s.nextLine();
			
			int adaptor = Integer.valueOf(line);
			
			adaptors.add(adaptor);
		}
		
		int ones = 0, threes = 0;
		
		Collections.sort(adaptors);
		
		adaptors.addFirst(0);
		adaptors.addLast(adaptors.getLast() + 3);
		
		for(int i = 1; i < adaptors.size(); i++) {
			int delta = adaptors.get(i) - adaptors.get(i - 1);
			if(delta == 1)
				ones++;
			else if(delta == 3)
				threes++;
			else
				System.out.println("non one or three delta");
		}
		System.out.println((ones) * (threes));
	}
	
	
	private static void part2() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day10.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}

		LinkedList<Integer> adaptors = new LinkedList<Integer>();
		
		
		while(s.hasNextLine()) {
			String line = s.nextLine();
			
			int adaptor = Integer.valueOf(line);
			
			adaptors.add(adaptor);
		}
		
		Collections.sort(adaptors);
		adaptors.addFirst(0);
		adaptors.addLast(adaptors.getLast()+3);

		long cnt = 1;
		int streak = 0;
		for(int i = 1; i < adaptors.size(); i++) {
			if(adaptors.get(i) - adaptors.get(i-1)  == 1) {
				streak++;
			}
			else
			{
				cnt *= trib(streak);
				streak = 0;
			}
		}
		System.out.println(cnt);
	}
	
	static int trib(int n) {
		if(n == 0)
			return 1;
		if(n == 1)
			return 1;
		if(n == 2)
			return 2;
		if(n == 3)
			return 4;
		
		return trib(n - 1) + trib(n - 2) + trib(n - 3);
	}
}




