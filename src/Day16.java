import java.util.*;
import java.io.*;

public class Day16 {
	public static class Ticket {
		LinkedList<TicketValue> values;
		
		public Ticket(LinkedList<TicketValue> vals) {
			values = vals;
		}
	}
	public static class TicketValue {
		int low1, low2, high1, high2;
		String name;
		
		public TicketValue(String values, String n) { // in format 38-532 or 549-953
			String[] s1 = values.split(" or ");
			low1 = Integer.valueOf(s1[0].split("-")[0]);
			low2 = Integer.valueOf(s1[0].split("-")[1]);
			high1 = Integer.valueOf(s1[1].split("-")[0]);
			high2 = Integer.valueOf(s1[1].split("-")[1]);
			name = n;
		}
		// returns 0 if valid, val otherwise
		public int valid(int val) {
			if((val >= low1 && val <= low2) || (val >= high1 && val <= high2))
				return 0;
			return val;
		}
	}
	
	public static void main(String[] args) {
		//part1();
		part2();
	}
	
	static void part1() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day16.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}

		int readstate = 0;
		HashMap<String, TicketValue> values = new HashMap<String, TicketValue>();
		
		List<Ticket> surroundingTickets = new ArrayList<Ticket>();
		
		int errorsum = 0;
		
		while(s.hasNextLine()) {
			String line = s.nextLine();
			if(line.equals("")) {
				readstate++;
				continue;
			}
			
			switch(readstate) {
			case 0:
				values.put(line.split(": ")[0], new TicketValue(line.split(": ")[1], line.split(": ")[0]));
				break;
			case 1:
				if(line.contains("ticket"))
					continue;
				// Do something with my ticket
				break;
			case 2:
				if(line.contains("ticket"))
					continue;
				
				for(String sfield : line.split(",")) {
					int field = Integer.valueOf(sfield);
					if(!fieldValidForAny(field, values)) {
						errorsum+=field;
					}
				}
				
			}
			
		}
		
		System.out.println(errorsum);
	}
	
	static boolean fieldValidForAny(int field, HashMap<String, TicketValue> values) {
		for(TicketValue tv : values.values()) {
			if(tv.valid(field) == 0)
				return true;
		}
		return false;
	}
	
	
	static void part2() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day16.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}

		int readstate = 0;
		HashMap<String, TicketValue> values = new HashMap<String, TicketValue>();
		
		List<Ticket> surroundingTickets = new ArrayList<Ticket>();
		List<String> validTicketStrings = new ArrayList<String>();
		
		long[] mt = new long[20];
		
		while(s.hasNextLine()) {
			String line = s.nextLine();
			if(line.equals("")) {
				readstate++;
				continue;
			}
			
			switch(readstate) {
			case 0:
				values.put(line.split(": ")[0], new TicketValue(line.split(": ")[1], line.split(": ")[0]));
				break;
			case 1:
				if(line.contains("ticket"))
					continue;
				for(int i = 0; i < 20; i++) {
					mt[i] = Integer.valueOf(line.split(",")[i]);
				}
				break;
			case 2:
				if(line.contains("ticket"))
					continue;
				boolean ticketgood = true;
				for(String sfield : line.split(",")) {
					int field = Integer.valueOf(sfield);
					if(!fieldValidForAny(field, values)) {
						ticketgood = false;
						break;
					}
				}
				if(ticketgood) {
					validTicketStrings.add(line);
				}
			}
		}
		
		int[][] fields = new int[validTicketStrings.size()][validTicketStrings.get(0).split(",").length];
		
		for(int i = 0; i < validTicketStrings.size(); i++) {
			for(int j = 0; j < validTicketStrings.get(i).split(",").length; j++) {
				fields[i][j] = Integer.valueOf(validTicketStrings.get(i).split(",")[j]);
			}
		}
		for(TicketValue tv : values.values()) {
			System.out.print(tv.name + ": ");
			for(int i = 0; i < values.values().size(); i++) {
				boolean works = true;
				for(int j = 0; j < fields.length; j++) {
					if(tv.valid(fields[j][i]) != 0) {
						works = false;
						break;
					}
				}
				if(works) {
					System.out.print(i + " ");
				}
			}
			System.out.println();
		}
		
		long val = 1;
		val *= mt[0];
		val *= mt[3];
		val *= mt[4];
		val *= mt[5];
		val *= mt[10];
		val *= mt[12];
		
		System.out.println(val);
	}

	static LinkedList<TicketValue> possibleTicketValuesPerField(int field, HashMap<String, TicketValue> values) {
		LinkedList<TicketValue> ret = new LinkedList<Day16.TicketValue>();
		for(TicketValue tv : values.values()) {
			if(tv.valid(field) == 0) {
				ret.add(tv);
			}
		}
		
		return ret;
	}
	
}
