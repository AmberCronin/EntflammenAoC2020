import java.util.*;
import java.io.*;

public class Day17 {
	public static void main(String[] args) {
		part1();
	}
	
	static boolean[][][] deepcopy(boolean[][][] input) {
		boolean[][][] ret = new boolean[input.length][input[0].length][input[0][0].length];
		for(int i = 0; i < input.length; i++) {
			for(int j = 0; j < input[0].length; j++) {
				for(int k = 0; k < input[0][0].length; k++) {
					ret[i][j][k] = Boolean.valueOf(input[i][j][k]);
				}
			}
		}
		return ret;
	}
	
	static int activeSurrounding(boolean[][][] input, int xloc, int yloc, int zloc) {
		int cnt = 0;
		
		for(int x = -1; x <= 1; x++) {
			for(int y = -1; y <= 1; y++) {
				for(int z = -1; z <= 1; z++) {
					if(xloc + x < 0 || xloc + x >= input.length || 
							yloc + y < 0 || yloc + y >= input[0].length || 
							zloc + z < 0 || zloc + z >= input[0][0].length ||
							xloc == 0 && yloc == 0 && zloc == 0)
						continue;
					
					if(input[xloc + x][yloc + y][zloc + z])
						cnt++;
				}
			}
		}
		
		return cnt;
	}
	
	static int countActive(boolean[][][] input) {
		int cnt = 0;
		for(int x = 0; x < input.length; x++) {
			for(int y = 0; y < input[0].length; y++) {
				for(int z = 0; z < input[0][0].length; z++) {
					if(input[x][y][z])
						cnt++;
				}
			}
		}
		return cnt;
	}
	
	static void printLevel(boolean[][][] input, int level) {
		System.out.println("z = " + level);
		for(int i = 0; i < input.length; i++) {
			for(int j = 0; j < input[0].length; j++) {
				System.out.print(input[i][j][level] ? '#' : '.');
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static void part1() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day17.small.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}

		boolean[][][] initial = new boolean[7][7][5]; 
		
		int dim = 3;
		
		for(int i = 2; i < 5; i++) {
			String line = s.nextLine();
			for(int j = 2; j < 5; j++) {
				initial[i][j][2] = (line.charAt(j - 2) == '#' ? true : false);
			}
		}
		for(int c = 0; c < 3; c++) {
			boolean[][][] working = deepcopy(initial);
			for(int i = 0; i < 5; i++) {
				printLevel(initial, i);
			}
			for(int x = 0; x < 7; x++) {
				for(int y = 0; y < 7; y++) {
					for(int z = 0; z < 5; z++) {
						int as = activeSurrounding(initial, x, y, z);
						if(initial[x][y][z]) {
							working[x][y][z] = ((as == 2 || as == 3) ? true : false);
						}
						else {
							working[x][y][z] = (as == 3 ? true : false);
						}
					}
				}
			}
			initial = working;
		}
		
		for(int i = 0; i < 5; i++) {
			printLevel(initial, i);
		}

		System.out.println(countActive(initial));
	}
}
