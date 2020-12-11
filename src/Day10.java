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
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day10.small.dat"), "ISO-8859-1");
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

		long cnt = 1;
		
		for(int i = 0; i < adaptors.size(); i++) {
			if(i < adaptors.size() - 3) {
				if(adaptors.get(i+3) - adaptors.get(i) == 3)
					cnt*=4;
			}
			else if(i < adaptors.size() - 2) {
				if(adaptors.get(i+2) - adaptors.get(i) <= 3)
					cnt*=2;
			}
		}
		System.out.println(cnt );
	}
	
	static boolean isValid(List<Integer> adaptors) {
		for(int i = 1; i < adaptors.size(); i++) {
			int delta = adaptors.get(i) - adaptors.get(i - 1);
			if(delta > 3)
				return false;
		}
		return true;
	}
	
	static Integer calcValidPossibilities(LinkedList<Integer> buildList, LinkedList<Integer> posadd) {
		
		if(posadd.size() == 1)
			return 1;
		
		int possibilities = 0;
		
		for(int i = 0; i < posadd.size()-1; i++) {
			LinkedList<Integer> posbuild = new LinkedList<Integer>(buildList);
			posbuild.add(posadd.get(i));
			LinkedList<Integer> newposadd = new LinkedList<Integer>(posadd.subList(i+1, posadd.size()));
			newposadd.remove(i);
			
			posbuild.add(posadd.getLast());
			
			if(isValid(posbuild))
				possibilities++;
			
			posbuild.removeLast();
			possibilities+=calcValidPossibilities(posbuild, newposadd);
		}
		return possibilities;
	}
	
	/*
	private static class Tuple<T, U> {
		public T key;
		public U val;
		public Tuple (T key, U val) {
			this.key = key;
			this.val = val;
		}
	}*/

}




