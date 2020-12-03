import java.io.*;
import java.util.*;

public class Day3 {

	public static void main(String[] args)
	{
		part1();
		part2();
	}
	
	static void part1() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day3.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}
		
		boolean[][] hasTree = new boolean[323][31];

		int lncnt = 0;
		while(s.hasNextLine())
		{
			String line = s.nextLine();
			for(int i = 0; i < 31; i++)
			{
				hasTree[lncnt][i] = (line.charAt(i) == '#');
			}
			lncnt++;
		}
		
		System.out.println(treesHit(3, 1, hasTree));
	}
	
	static int treesHit(int xslope, int yslope, boolean[][] treeLocs)
	{
		int currx = 0; // increment by 3 each line
		int treesHit = 0;
		for(int i = 0; i < 323; i+=yslope)
		{
			if(treeLocs[i][currx % 31])
				treesHit++;
			
			currx += xslope;
		}
		return treesHit;
	}
	
	static void part2() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day3.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}
		
		boolean[][] hasTree = new boolean[323][31];

		int lncnt = 0;
		while(s.hasNextLine())
		{
			String line = s.nextLine();
			for(int i = 0; i < 31; i++)
			{
				hasTree[lncnt][i] = (line.charAt(i) == '#');
			}
			lncnt++;
		}
		
		System.out.println(treesHit(1, 1, hasTree) * treesHit(3, 1, hasTree) * treesHit(5, 1, hasTree) * treesHit(7, 1, hasTree) * treesHit(1, 2, hasTree));
		
	}
}