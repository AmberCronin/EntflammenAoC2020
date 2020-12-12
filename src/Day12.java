import java.io.*;
import java.util.*;


public class Day12 {
	public static void main(String[] args) {
		//part1();
		part2();
	}

	private static void part1() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day12.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}

		int ns = 0; // positive is north
		int ew = 0; // positive is east
		int dir = 0; // 0 is east, 90 is south, 180 is west, 270 is north
		
		
		while(s.hasNextLine()) {
			String line = s.nextLine();
			String inst = line.substring(0, 1);
			int val = Integer.valueOf(line.substring(1));
			
			switch(inst) {
			case "N":
				ns += val;
				break;
			case "S":
				ns -= val;
				break;
			case "E":
				ew += val;
				break;
			case "W":
				ew -= val;
				break;
			case "L":
					dir -= (val - 360);
					dir = dir % 360;
				break;
			case "R":
				dir += val;
				dir = dir % 360;
				break;
			case "F":
				switch(dir) {
				case 0:
					ew += val;
					break;
				case 90:
					ns -= val;
					break;
				case 180:
					ew -= val;
					break;
				case 270:
					ns += val;
					break;
				}
				break;
			}
			System.out.print("");
		}
		System.out.println(Math.abs(ns) + Math.abs(ew));
	}
	
	private static void part2() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day12.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}

		int wpns = 1;
		int wpew = 10;
		int ns = 0;
		int ew = 0;
		
		while(s.hasNextLine()) {
			String line = s.nextLine();
			String inst = line.substring(0, 1);
			int val = Integer.valueOf(line.substring(1));
			
			switch(inst) {
			case "N":
				wpns += val;
				break;
			case "S":
				wpns -= val;
				break;
			case "E":
				wpew += val;
				break;
			case "W":
				wpew -= val;
				break;
			case "L":
				if(val == 90) {
					int tmp = wpew;
					wpew = wpns;
					wpns = tmp;
					wpew *= -1;
				}
				if(val == 180) {
					wpew *= -1;
					wpns *= -1;
				}
				if(val == 270) {
					int tmp = wpew;
					wpew = wpns;
					wpns = tmp;
					wpns *= -1;
				}
				break;
			case "R":
				if(val == 90) {
					int tmp = wpew;
					wpew = wpns;
					wpns = tmp;
					wpns *= -1;
				}
				if(val == 180) {
					wpew *= -1;
					wpns *= -1;
				}
				if(val == 270) {
					int tmp = wpew;
					wpew = wpns;
					wpns = tmp;
					wpew *= -1;
				}

				break;
			case "F":
				ns += val*wpns;
				ew += val*wpew;
				break;
				
			}
			System.out.print("");
		}
		System.out.println(Math.abs(ns) + Math.abs(ew));

		
	}


}
