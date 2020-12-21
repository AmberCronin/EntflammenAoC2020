import java.util.*;
import java.io.*;

public class Day21 {
	public static void main(String[] args) {
		//part1();
		part2();
	}
	static void part1() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day21.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}

		List<HashSet<String>> ingredients = new LinkedList<HashSet<String>>();
		List<HashSet<String>> allergens = new LinkedList<HashSet<String>>();
		while(s.hasNextLine()) {
			String line = s.nextLine();
			String ingredientList = line.substring(0, line.indexOf('(') - 1);
			ingredients.add(new HashSet(Arrays.asList(ingredientList.split(" "))));
			String allergenList = line.substring(line.indexOf('(') + 10, line.length() - 1);
			allergens.add(new HashSet(Arrays.asList(allergenList.split(", "))));
		}
		HashSet<String> allAllergens = new HashSet<String>();
		for(HashSet<String> a : allergens) {
			allAllergens.addAll(a);
		}
		
		HashSet<String> possIngAllergens = new HashSet<String>();
		
		for(String a : allAllergens) {
			HashSet<String> andList = new HashSet<String>();
			int numLines = 0;
			for(int i = 0; i < allergens.size(); i++) {
				if(allergens.get(i).contains(a)) {
					if(andList.size() == 0) {
						andList.addAll(ingredients.get(i));
					}
					else {
						List<String> remList = new ArrayList<>();
						for(String s1 : andList) {
							if(!ingredients.get(i).contains(s1)) {
								remList.add(s1);
							}
						}
						for(String s1 : remList) {
							andList.remove(s1);
						}
					}
				}
			}
			possIngAllergens.addAll(andList);
		}
		int sum = 0;
		for(var l : ingredients) {
			for(String s1 : l) {
				if(!possIngAllergens.contains(s1))
					sum++;
			}
		}
		System.out.println(sum);
	}
	static void part2() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day21.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}

		List<HashSet<String>> ingredients = new LinkedList<HashSet<String>>();
		List<HashSet<String>> allergens = new LinkedList<HashSet<String>>();
		while(s.hasNextLine()) {
			String line = s.nextLine();
			String ingredientList = line.substring(0, line.indexOf('(') - 1);
			ingredients.add(new HashSet(Arrays.asList(ingredientList.split(" "))));
			String allergenList = line.substring(line.indexOf('(') + 10, line.length() - 1);
			allergens.add(new HashSet(Arrays.asList(allergenList.split(", "))));
		}
		HashSet<String> allAllergens = new HashSet<String>();
		for(HashSet<String> a : allergens) {
			allAllergens.addAll(a);
		}
		
		HashMap<String, HashSet<String>> allergenMap = new HashMap<String, HashSet<String>>();
		
		for(String a : allAllergens) {
			HashSet<String> andList = new HashSet<String>();
			int numLines = 0;
			for(int i = 0; i < allergens.size(); i++) {
				if(allergens.get(i).contains(a)) {
					if(andList.size() == 0) {
						andList.addAll(ingredients.get(i));
					}
					else {
						List<String> remList = new ArrayList<>();
						for(String s1 : andList) {
							if(!ingredients.get(i).contains(s1)) {
								remList.add(s1);
							}
						}
						for(String s1 : remList) {
							andList.remove(s1);
						}
					}
				}
			}
			allergenMap.put(a, andList);
		}
				
		for(String s1 : allergenMap.keySet()) {
			System.out.print(s1 + ": ");
			for(String s2 : allergenMap.get(s1)) {
				System.out.print(s2 + " ");
			}
			System.out.println();
		}
	}
}
