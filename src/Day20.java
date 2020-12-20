import java.util.*;
import java.io.*;

public class Day20 {
	
	static class Tile {
		String top, bottom, right, left;
		int id;
		public Tile(String[] tile, int tID) {
			id = tID;
			
			top = tile[0];
			bottom = tile[tile.length - 1];
			right = "";
			left = "";
			for(int i = 0; i < tile.length; i++) {
				left += tile[i].charAt(0);
				right += tile[i].charAt(tile.length - 1);
			}
		}
		public String[] edges() {
			return new String[] {top, bottom, right, left};
		}
		public int isMatch(String s) {
			int i = 0;
			String sr = rev(s);
			if(s.equals(top))
				i++;
			if(s.equals(bottom))
				i++;
			if(s.equals(right))
				i++;
			if(s.equals(left))
				i++;
			if(sr.equals(top))
				i++;
			if(sr.equals(bottom))
				i++;
			if(sr.equals(right))
				i++;
			if(sr.equals(left))
				i++;
			return i;
		}
		static String rev(String str) {
			return new StringBuilder(str).reverse().toString();
		}
	}
	public static void main(String[] args) {
		part1();
	}
	static void part1() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day20.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}

		List<Tile> tiles = new ArrayList<Tile>();
		int id = 0;
		String[] tileLines = null;
		while(s.hasNextLine()) {
			String line = s.nextLine();
			if(line.equals("")) {
				tiles.add(new Tile(tileLines, id));
				continue;
			}
			if(line.contains("Tile")) {
				tileLines = new String[10];
				id = Integer.valueOf(line.substring(5, line.length() - 1));
			}
			for(int i = 0; i < 10; i++) {
				tileLines[i] = s.nextLine();
			}
		}
		tiles.add(new Tile(tileLines, id));
		
		HashMap<Tile, List<Tile>> matches = new HashMap<Day20.Tile, List<Tile>>();
		
		for(Tile t : tiles) {
			List<Tile> possibleMatches = new ArrayList<Day20.Tile>();
			for(Tile t2 : tiles) {
				if(t == t2)
					continue;
				
				for(String edge : t.edges()) {
					if(t2.isMatch(edge) > 0)
						possibleMatches.add(t);
				}
			}
			matches.put(t, possibleMatches);
		}

		long output = 1;
		
		for(Tile t : matches.keySet()) {
			if(matches.get(t).size() == 2) {
				output *= t.id;
			}
		}
		System.out.println(output);
		
	}
}
