import java.io.*;
import java.math.*;
import java.util.*;

public class Day9 {

	public static void main(String[] args) {
		part1();
		part2();
	}

	private static void part1() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day9.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}

		BigInteger[] nums = new BigInteger[1000];
		
		int cnt = 0;
		while(s.hasNextLine()) {
			String line = s.nextLine();
			try{nums[cnt++] = new BigInteger(line);}
			catch(Exception e) {System.out.println(e.toString());}
		}
		
		for(int i = 25; i < 1000; i++) {
			boolean found = false;
			for(int j = i - 25; j < i-1; j++) {
				for(int k = j+1; k < i; k++) {
					if(nums[j].add(nums[k]).equals(nums[i])) {
						found = true;
						break;
					}
				}
				if(found)
					break;
			}
			if(!found) {
				System.out.println(nums[i]);
				return;
			}
		}
	}
	

	private static void part2() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day9.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}

		int listSize = 1000;
		int jmpSize = 25;
		
		BigInteger[] nums = new BigInteger[listSize];
		
		int cnt = 0;
		while(s.hasNextLine()) {
			String line = s.nextLine();
			try{nums[cnt++] = new BigInteger(line);}
			catch(Exception e) {System.out.println(e.toString());}
		}
		
		for(int i = jmpSize; i < listSize; i++) {
			boolean found = false;
			for(int j = i - jmpSize; j < i-1; j++) {
				for(int k = j+1; k < i; k++) {
					if(nums[j].add(nums[k]).equals(nums[i])) {
						found = true;
						break;
					}
				}
				if(found)
					break;
			}
			if(!found) {
				BigInteger numToSumTo = nums[i];
				boolean foundRange = false;
				int rangelow = 0, rangehigh = 0;
				
				for(int j = 0; j < i-1; j++) {
					BigInteger potSum = nums[j];
					for(int k = j+1; k < i; k++) {
						potSum = potSum.add(nums[k]);
						if(potSum.equals(numToSumTo)) {
							rangelow = j;
							rangehigh = k;
							foundRange = true;
							break;
						}
					}
					if(foundRange)
						break;
				}
				
				BigInteger min = nums[i];
				BigInteger max = new BigInteger("0");
				for(int j = rangelow; j < rangehigh+1; j++) {
					min = min.min(nums[j]);
					max = max.max(nums[j]);
				}
				
				System.out.println(min.add(max));
				
				return;
			}
		}
		
	}
}
