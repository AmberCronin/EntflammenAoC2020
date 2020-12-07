import java.io.*;
import java.io.FileNotFoundException;
import java.util.*;

public class Day7 {
	
	public static class Bag
	{
		public HashSet<Bag> innerBags;
		public String bagColour;
		
		public Bag(String colour)
		{
			innerBags = new HashSet<Day7.Bag>();
			bagColour = colour;
		}
		public void addInner(Bag b) {
			innerBags.add(b);
		}
	}
	
	public static void main(String[] args)
	{

		//part1();
		part2();
	}


	private static void part1() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day7.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}

		HashMap<String, HashSet<String>> bagParents = new HashMap<String, HashSet<String>>();
		
		
		while(s.hasNextLine())
		{
			String line = s.nextLine();
			
			String outerBag = line.split(" ")[0] + " " + line.split(" ")[1];
			boolean innerBags = line.contains("no other bags.");

			
			if(!innerBags)
			{
				String[] splt = line.split(" ");
				for(int i = 5; i < splt.length; i+=4)
				{
					String innerBag = splt[i] + " " + splt[i + 1];
					
					HashSet<String> parents = bagParents.get(innerBag);
					if(parents == null)
					{
						parents = new HashSet<String>();
						parents.add(outerBag);
						bagParents.put(innerBag, parents);
					}
					else
					{
						parents.add(outerBag);
					}
				}
			}
		}
		
		HashSet<String> shinyGoldParents = bagParents.get("shiny gold");
		HashSet<String> visited = new HashSet<String>();
		visited.add("shiny gold");
		System.out.println(countParents(shinyGoldParents, visited, bagParents, 0));
	}
	
	private static int countParents(HashSet<String> parents, HashSet<String> visited, HashMap<String, HashSet<String>> allBags, int count)
	{
		if(parents.isEmpty())
			return count;
		HashSet<String> grandparents = new HashSet<String>();
		for(String p : parents)
		{
			if(!visited.contains(p))
			{
				count++;
				try{grandparents.addAll(allBags.get(p));}
				catch(Exception e) {}
				visited.add(p);
			}
		}
		return countParents(grandparents, visited, allBags, count);
	}
	
	private static void part2() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day7.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}
		
		
		HashMap<String, HashMap<String, Integer>> allBags = new HashMap<String, HashMap<String,Integer>>();
		
		while(s.hasNextLine())
		{
			String line = s.nextLine();
			
			String outerBag = line.split(" ")[0] + " " + line.split(" ")[1];
			boolean innerBags = line.contains("no other bags.");

			HashMap<String, Integer> inners = new HashMap<String, Integer>();

			if(!innerBags)
			{
				String[] splt = line.split(" ");
				
				for(int i = 5; i < splt.length; i+=4)
				{
					Integer innerBagCount = Integer.parseInt(splt[i-1]);
					String innerBagColour = splt[i] + " " + splt[i + 1];
					
					inners.put(innerBagColour, innerBagCount);
				}
				
			}
			
			allBags.put(outerBag, inners);
		}
		
		int numNeeded = numInnerBagsNeeded("shiny gold", allBags);
		System.out.println(numNeeded - 1);
	}
	private static int numInnerBagsNeeded(String bag, HashMap<String, HashMap<String, Integer>> allBags)
	{
		int count = 1;
		
		HashMap<String, Integer> innerBags = allBags.get(bag);
		
		if(innerBags.isEmpty())
			return 1;
		
		for(String s : innerBags.keySet()) {
			count += innerBags.get(s) * numInnerBagsNeeded(s, allBags);
		}
		
		return count;
	}
}
