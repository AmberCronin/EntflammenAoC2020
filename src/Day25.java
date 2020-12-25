import java.util.*;
import java.io.*;

public class Day25 {
	public static void main(String[] args) {
		part1();
	}
	static void part1() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day25.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}
		
		long modval = 20201227;
		long cardpubkey = 6930903, doorpubkey = 19716708, cardsubj = 7, doorsubj = 7, val = 1;
		int cardloop = 0, doorloop = 0;
		long encyptionkey;
		
		for( ; val != cardpubkey; cardloop++) {
			val *= cardsubj;
			val = val % modval;
		}
		
		System.out.println(cardloop);
		
		val = 1;
		
		for( ; val != doorpubkey; doorloop++) {
			val *= doorsubj;
			val = val % modval;
		}
		
		System.out.println(doorloop);

		val = 1;
		
		for(long i = 0; i < cardloop; i++) {
			val *= doorpubkey;
			val = val % modval;
		}
		System.out.println(val);
	}
}
