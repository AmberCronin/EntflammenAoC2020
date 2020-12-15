import java.util.*;
import java.io.*;

public class Day14 {
	public static void main(String[] args) {
		part1();
		part2();
	}
	static void part1() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day14.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}

		HashMap<Integer, Long> data = new HashMap<Integer, Long>();
		long andMask = 0;
		long orMask = 0;
		
		while(s.hasNextLine()) {
			String line = s.nextLine();
			if(line.substring(0, 2).equals("ma")) {
				andMask = Long.valueOf(line.split(" = ")[1].replace('X', '1'), 2);
				orMask = Long.valueOf(line.split(" = ")[1].replace('X', '0'), 2);
			}
			else {
				int memLoc = Integer.valueOf(line.split("] = ")[0].substring(4));
				long oldVal = Long.valueOf(line.split(" = ")[1]);
				long newVal = (oldVal | orMask);
				newVal = (newVal & andMask);
				data.put(memLoc, newVal);
			}
		}
		long sum = 0;
		for(Long l : data.values()) {
			sum+=l;
		}
		System.out.println(sum);
	}
	
	static void part2() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day14.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}

		HashMap<Long, Long> data = new HashMap<Long, Long>();
		String smask = "";
		int smasklen = 0;
		while(s.hasNextLine()) {
			String line = s.nextLine();
			
			if(line.substring(0, 2).equals("ma")) {
				smask = line.split(" = ")[1];
				smasklen = smask.length();
			}
			else {

				String smemloc = fullString(Integer.toBinaryString(Integer.valueOf(line.split("] = ")[0].substring(4))), smasklen);
				long val = Long.valueOf(line.split(" = ")[1]);
				
				ArrayList<String> memAddresses = getMemAddresses(bitmask(smemloc, smask));
				
				for(String sadr : memAddresses) {
					long adr = Long.valueOf(sadr, 2);
					data.put(adr, val);
				}
			}

			
		}
		
		
		
		
		long sum = 0;
		for(Long l : data.values()) {
			sum+=l;
		}
		System.out.println(sum);
		
		
	}
	
	static String bitmask(String adr, String mask) {
		String str = "";
		for(int i = mask.length() - 1; i >= 0; i--) {
			switch(mask.charAt(i)) {
			case '0':
				str = str + adr.charAt(i);
				break;
			case '1':
				str = str + "1";
				break;
			case 'X':
				str = str + "X";
				break;
			}
		}
		return str;
	}
	
	static String fullString(String s, int len) {
		while(s.length() != len) {
			s = "0" + s;
		}
		return s;
	}
	
	static ArrayList<String> getMemAddresses (String memloc) {
		int index = memloc.indexOf('X');
		
		ArrayList<String> newAddresses = new ArrayList<String>();

		if(index == -1) {
			newAddresses.add(memloc);
			return newAddresses;
		}
		
		
		String ch1 = memloc.replaceFirst("X", "0");
		String ch2 = memloc.replaceFirst("X", "1");
		
		newAddresses.addAll(getMemAddresses(ch1));
		newAddresses.addAll(getMemAddresses(ch2));
		
		return newAddresses;
	}
}
