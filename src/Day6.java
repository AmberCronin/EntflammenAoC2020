import java.io.*;
import java.util.*;

public class Day6 {
	
	public static class Group1 {
		public int people;
		public HashSet<Character> questions;
		public Group1 (int ppl, HashSet<Character> qs) {
			people = ppl;
			questions = qs;
		}
	}
	
	public static class Group2 {
		public int people;
		public int[] cnt = new int[97+26+1];
		public Group2 (int ppl, int[] count) {
			people = ppl;
			cnt = count;
		}
		public void add1() {
			people++;
		}
		
	}
	
	
	public static void main(String[] args)
	{
		part1();
		part2();
	}


	private static void part1() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day6.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}

		
		ArrayList<Group1> groups = new ArrayList<Day6.Group1>();
		
		int ppl = 0;
		HashSet<Character> questions = new HashSet<Character>();

		while(s.hasNextLine())
		{
			
			String line = s.nextLine();
			
			if(line.equals(""))
			{
				groups.add(new Group1(ppl, questions));
				ppl = 0;
				questions = new HashSet<Character>();
				continue;
			}
			
			for(Character c : line.toCharArray())
			{
				questions.add(c);
			}
			ppl++;
		}
		
		int sum = 0;
		for(Group1 g : groups)
		{
			sum += g.questions.size();
		}
		System.out.println(sum);
	}
	
	private static void part2() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day6.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}

		
		ArrayList<Group2> groups = new ArrayList<Day6.Group2>();
		
		int ppl = 0;
		int[] count = new int[96+26+1];
		int sum = 0;
		
		
		// char a is worth 97
		System.out.println(new Character('a') + 0);
		
		while(s.hasNextLine())
		{
			
			String line = s.nextLine();
			
			if(line.equals(""))
			{
				groups.add(new Group2(ppl, count));
				ppl = 0;
				count = new int[96+26+1];
				continue;
			}
			
			
			
			for(Character c : line.toCharArray())
			{
				count[c+0]++;
			}
			
			ppl++;
		}
		
		for(Group2 g : groups)
		{
			for(int i =96; i < 96+26+1; i++)
			{
				if(g.cnt[i] == g.people)
				{
					sum++;
				}
			}
		}
		System.out.println(sum);
	}
}






