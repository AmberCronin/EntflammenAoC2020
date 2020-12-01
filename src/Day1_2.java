import java.io.*;
import java.util.*;

public class Day1_2 {
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<Integer> entries = new ArrayList<Integer>();
		final Scanner s = new Scanner(new File("day1/day1.data"), "ISO-8859-1");
		while(s.hasNextLine())
		{
			entries.add(s.nextInt());
		}
		for(int i = 0; i < entries.size() - 2; i++)
		{
			for(int j = i+1; j < entries.size() - 1; j++)
			{
				for(int k = j+1; k < entries.size(); k++)
				{
					if(entries.get(i) + entries.get(j) + entries.get(k) == 2020)
					{
						System.out.println(entries.get(i) * entries.get(j) * entries.get(k));
					}
				}
			}
		}
	}
}
