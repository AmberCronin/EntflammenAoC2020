import java.io.*;
import java.util.*;

public class Day1 {
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<Integer> entries = new ArrayList<Integer>();
		final Scanner s = new Scanner(new File("day1/day1.data"), "ISO-8859-1");
		while(s.hasNextLine())
		{
			entries.add(s.nextInt());
		}
		for(int i = 0; i < entries.size() - 1; i++)
		{
			for(int j = i+1; j < entries.size(); j++)
			{
				if(entries.get(i) + entries.get(j) == 2020)
				{
					System.out.println(entries.get(i) * entries.get(j));
				}
			}
		}
	}
}