import java.util.*;
import java.io.*;

public class Day13 {
	public static void main(String[] args) {
		//part1();
		part2();
	}
	static void part1() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day13.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}
		
		int earliestDeparture = Integer.valueOf(s.nextLine());
		String[] departs = s.nextLine().split(",");
		List<Integer> departures = new ArrayList<Integer>();
		for(String sd : departs) {
			if(sd.equals("x"))
				continue;
			departures.add(Integer.valueOf(sd));
		}
		
		int departTime = Integer.valueOf(earliestDeparture);
		int busID = 0;
		boolean found = false;
		while(true) {
			for(Integer i : departures) {
				if(departTime % i == 0) {
					found = true;
					busID = i;
					break;
				}
			}
			if(found)
				break;
			departTime++;
		}
		System.out.println((departTime - earliestDeparture) * busID);
	}

	static void part2() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day13.micro.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}
		
		int earliestDeparture = Integer.valueOf(s.nextLine());
		String[] departs = s.nextLine().split(",");
		LinkedList<Integer> times = new LinkedList<Integer>();
		for(String sd : departs) {
			if(sd.equals("x"))
				times.add(1);
			else
				times.add(Integer.valueOf(sd));
		}
		
		for(int i = 1; i < Integer.MAX_VALUE; i++) {
			if(timeGood(times, i)) {
				System.out.println(i);
				return;
			}
		}
		
		System.out.println("didn't find");
	}
	
	static boolean timeGood(LinkedList<Integer> busses, int time) {
		for(int i = 0; i < busses.size(); i++) {
			if((time + i) % busses.get(i) != 0)
				return false;
		}
		return true;
	}
}
