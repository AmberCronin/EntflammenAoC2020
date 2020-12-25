import java.util.*;


import java.io.*;

public class Day24 {
	public static void main(String[] args) {
		//part1();
		part2();
	}
		
	static void part1() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day24.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}
		
		boolean[][][] tiles = new boolean[1001][1001][1001];
		int cx = 500, cy = 500, cz = 500;
		
		while(s.hasNextLine()) {
			String line = s.nextLine();
			
			int x = cx, y = cy, z = cz;
			
			for(int i = 0; i < line.length(); i++) {

				char inst1 = line.charAt(i);
				char inst2 = 'a';

				if(inst1 == 'n' || inst1 == 's') {
					i++;
					inst2 = line.charAt(i);
					if(inst1 == 'n' && inst2 == 'e') {
						x++;
						z--;
					}
					else if(inst1 == 'n' && inst2 == 'w') {
						z--;
						y++;
					}
					else if(inst1 == 's' && inst2 == 'e') {
						z++;
						y--;
					}
					else { // sw
						x--;
						z++;
					}
				}
				else if(inst1 == 'e') {
					x++;
					y--;
				}
				else {
					x--;
					y++;
				}
			}
			tiles[x][y][z] = !tiles[x][y][z];
		}
		int sum = 0;
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				for(int k = 0; k < tiles[i][j].length; k++) {
					if(tiles[i][j][k])
						sum++;
				}
			}
		}
		System.out.println(sum);
	}
	static void part2() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day24.small.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}
		
		boolean[][][] tiles = new boolean[101][101][101];
		int cx = 50, cy = 50, cz = 50;
		
		while(s.hasNextLine()) {
			String line = s.nextLine();
			
			int x = cx, y = cy, z = cz;
			
			for(int i = 0; i < line.length(); i++) {

				char inst1 = line.charAt(i);
				char inst2 = 'a';

				if(inst1 == 'n' || inst1 == 's') {
					i++;
					inst2 = line.charAt(i);
					if(inst1 == 'n' && inst2 == 'e') {
						x++;
						z--;
					}
					else if(inst1 == 'n' && inst2 == 'w') {
						z--;
						y++;
					}
					else if(inst1 == 's' && inst2 == 'e') {
						z++;
						y--;
					}
					else { // sw
						x--;
						z++;
					}
				}
				else if(inst1 == 'e') {
					x++;
					y--;
				}
				else {
					x--;
					y++;
				}
			}
			tiles[x][y][z] = !tiles[x][y][z];
		}
		
		//days 1-100
		for(int i = 0; i < 100; i++) {
			System.out.println(i);
			boolean[][][] after = deepcopy(tiles);
			
			for(int x = 0; x < after.length; x++) {
				for(int y = 0; y < after[0].length; y++) {
					for(int z = 0; z < after[0][0].length; z++) {
						if(x+y+z != 0)
							continue;
						int blkAdjCnt = 0;
						for(int dx = -1; dx <= 1; dx++) {
							for(int dy = -1; dy <= 1; dy++) {
								for(int dz = -1; dz <= 1; dz++) {
									if(dx == 0 && dy == 0 && dz == 0)
										continue;
									if(dx + x < 0 || dx + x >= after.length ||
											dy + y < 0 || dy + y >= after[0].length ||
											dz + z < 0 || dz + z >= after[0][0].length)
										continue;
									if((dx + x) + (dy + y) + (dz + z) != 0)
										continue;
									
									if(tiles[dx + x][dy + y][dz + z])
										blkAdjCnt++;
								}
							}
						}
						
						if(tiles[x][y][z]) {
							if(blkAdjCnt == 0 || blkAdjCnt > 2)
								after[x][y][z] = false;
						}
						else {
							if(blkAdjCnt == 2)
								after[x][y][z] = true;
						}
					}
				}
			}
			tiles = after;
		}
		
		int sum = 0;
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				for(int k = 0; k < tiles[i][j].length; k++) {
					if(tiles[i][j][k])
						sum++;
				}
			}
		}
		System.out.println(sum);

		
		
	}
	private static boolean[][][] deepcopy(boolean[][][] after) {
		boolean[][][] seats = new boolean[after.length][after[0].length][after[0][0].length];
		for(int i = 0; i < after.length; i++) {
			for(int j = 0; j < after[0].length; j++) {
				for(int k = 0; k < after[0][0].length; k++) {
					seats[i][j][k] = Boolean.valueOf(after[i][j][k]);
				}
			}
		}
		return seats;
	}

}
