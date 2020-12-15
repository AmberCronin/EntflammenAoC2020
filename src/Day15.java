import java.util.*;
import java.io.*;


public class Day15 {
	public static class LastTwo {
		Integer alpha = -1;
		Integer beta = -1;
		
		public LastTwo() {
			// Do nothing
		}
		
		public LastTwo(int a) {
			alpha = a;
		}
		public void add(int a) {
			beta = alpha;
			alpha = a;
		}
		public int diff() {
			if(beta == -1) {
				return 0;
			}
			return alpha - beta;
		}
	}
	public static void main(String[] args) {
		part1();
		part2();
	}
	
	static void part1() {
		HashMap<Integer, LastTwo> lastUsed = new HashMap<Integer, LastTwo>();

		int lastSaid = 0;
		
		int[] starts = new int[] {9,3,1,0,8,4};
		for(int i = 1; i <= starts.length; i++) {
			lastUsed.put(starts[i-1], new LastTwo(i));
			lastSaid = starts[i-1];
		}
		
		for(int i = lastUsed.size()+1; i <= 2020; i++) {
			
			LastTwo lt = lastUsed.get(lastSaid);
			int diff = lt.diff();
			if(diff == 0) { // was said only once
				if(lastUsed.get(0) == null) {
					lastUsed.put(0, new LastTwo());
				}
				lastUsed.get(0).add(i);
				lastSaid = 0;
			}
			else {
				LastTwo nlt = lastUsed.get(diff);
				if(nlt == null) {
					lastUsed.put(diff, new LastTwo());
					nlt = lastUsed.get(diff);
				}
				
				nlt.add(i);
				
				lastSaid = diff;
			}
			
		}
		
		System.out.println(lastSaid);
	}
	static void part2() {
		HashMap<Integer, LastTwo> lastUsed = new HashMap<Integer, LastTwo>();

		int lastSaid = 0;
		
		int[] starts = new int[] {9,3,1,0,8,4};
		for(int i = 1; i <= starts.length; i++) {
			lastUsed.put(starts[i-1], new LastTwo(i));
			lastSaid = starts[i-1];
		}
		
		for(int i = lastUsed.size()+1; i <= 30000000; i++) {
			
			LastTwo lt = lastUsed.get(lastSaid);
			int diff = lt.diff();
			if(diff == 0) { // was said only once
				if(lastUsed.get(0) == null) {
					lastUsed.put(0, new LastTwo());
				}
				lastUsed.get(0).add(i);
				lastSaid = 0;
			}
			else {
				LastTwo nlt = lastUsed.get(diff);
				if(nlt == null) {
					lastUsed.put(diff, new LastTwo());
					nlt = lastUsed.get(diff);
				}
				
				nlt.add(i);
				
				lastSaid = diff;
			}
		}
		
		System.out.println(lastSaid);

	}
}
