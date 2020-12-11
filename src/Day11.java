import java.io.*;
import java.util.*;

public class Day11 {
	
	public static class Seat {
		public boolean occupied = false;
		public Seat(boolean state) {
			occupied = state;
		}
		public void changeState() {
			occupied = !occupied;
		}
		

	}
	
	public static void main(String[] args) {
		//part1();
		part2();
	}
	
	//static int width = 98, height = 93;

	private static void part1() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day11.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}
		
		
		Seat[][] seats = new Seat[height][width];
		Seat[][] after = new Seat[height][width];

		int currheight = 0;
		
		while(s.hasNextLine()) {
			String line = s.nextLine();
			char[] cseats = line.toCharArray();
			for(int i = 0; i < width; i++) {
				after[currheight][i] = (cseats[i] == 'L' ? new Seat(false) : null);
			}
			currheight++;
		}

		printSeats(after);
		System.out.println();
		int hold = 0;
		do
		{
			seats = deepcopy(after);
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					if(seats[i][j] != null) {
						int filled = seatsFilledAround(seats, i, j);
						if(seats[i][j].occupied == false && filled == 0)
							after[i][j] = new Seat(true);
						else if(seats[i][j].occupied == false && filled != 0)
							after[i][j] = new Seat(false);
						else if(seats[i][j].occupied == true && filled >= 4)
							after[i][j] = new Seat(false);
						else
							after[i][j] = seats[i][j];
					}
					else
						after[i][j] = null;
				}
			}
			printSeats(after);
			System.out.println();
		} while(!seatsSame(after, seats));
		
		System.out.println(seatsFilled(after));
	}
	
	private static Day11.Seat[][] deepcopy(Day11.Seat[][] after) {
		Seat[][] seats = new Seat[after.length][after[0].length];
		for(int i = 0; i < after.length; i++) {
			for(int j = 0; j < after[0].length; j++) {
				if(after[i][j] == null)
					seats[i][j] = null;
				else
					seats[i][j] = new Seat(after[i][j].occupied);
			}
		}
		return seats;
	}

	static int seatsFilledAround(Seat[][] seats, int h, int w) {
		int cnt = 0;
		
		if(isCorner(h, w))
			return 0;
		
		for(int i = -1; i <= 1; i++) {
			if(h + i < 0 || h + i >= height)
				continue;
			for(int j = -1; j <= 1; j++) {
				if(i == 0 && j == 0)
					continue;
				if(w + j < 0 || w + j >= width)
					continue;
				if(seats[h + i][w + j] != null) {
					if(seats[h+i][w+j].occupied == true)
						cnt++;
				}
			}
		}
		return cnt;
	}
	static boolean isCorner(int h, int w) {
		if((h == 0 || h == height - 1) && (w == 0 || w == width - 1))
			return true;
		return false;
	}
	
	static boolean seatsSame(Seat[][] before, Seat[][] after) {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(before[i][j] != null)
				{
					if(before[i][j].occupied != after[i][j].occupied)
						return false;
				}
			}
		}
		return true;
	}
	static int seatsFilled(Seat[][] seats) {
		int cnt = 0;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(seats[i][j] != null && seats[i][j].occupied)
				{
					cnt++;
				}
			}
		}
		return cnt;

	}
	
	static void printSeats(Seat[][] seats) {
		for(int i = 0; i < seats.length; i++) {
			for(int j = 0; j < seats[i].length; j++) {
				if(seats[i][j] == null)
					System.out.print('.');
				else
					System.out.print(seats[i][j].occupied ? '#' : 'L');
			}
			System.out.println();
		}
	}
	
	//static int width = 10, height = 10;
	static int width = 98, height = 93;

	
	private static void part2() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day11.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}
		
		
		Seat[][] seats = new Seat[height][width];
		Seat[][] after = new Seat[height][width];

		int currheight = 0;
		
		while(s.hasNextLine()) {
			String line = s.nextLine();
			char[] cseats = line.toCharArray();
			for(int i = 0; i < width; i++) {
				after[currheight][i] = (cseats[i] == 'L' ? new Seat(false) : null);
			}
			currheight++;
		}

		printSeats(after);
		System.out.println();
		int hold = 0;
		do
		{
			seats = deepcopy(after);
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					if(seats[i][j] != null) {
						int filled = seatsFilledLOS(seats, i, j);
						if(seats[i][j].occupied == false && filled == 0)
							after[i][j] = new Seat(true);
						else if(seats[i][j].occupied == false && filled != 0)
							after[i][j] = new Seat(false);
						else if(seats[i][j].occupied == true && filled >= 5)
							after[i][j] = new Seat(false);
						else
							after[i][j] = seats[i][j];
					}
					else
						after[i][j] = null;
				}
			}
			printSeats(after);
			System.out.println();
		} while(!seatsSame(after, seats));
		
		System.out.println(seatsFilled(after));
	}
	
	static int seatsFilledLOS(Seat[][] seats, int h, int w) {
		int cnt = 0;
		
		if(isCorner(h, w))
			return 0;
		
		for(int i = -1; i <= 1; i++) {
			if(h + i < 0 || h + i >= height)
				continue;
			for(int j = -1; j <= 1; j++) {
				if(i == 0 && j == 0)
					continue;
				if(occupiedSeatInDir(seats, h, w, i, j))
					cnt++;
			}
		}
		return cnt;
	}
	static boolean occupiedSeatInDir(Seat[][] seats, int h, int w, int dh, int dw) {
		while(h + dh >= 0 && h + dh < height && w + dw >= 0 && w + dw < width) {
			h += dh;
			w += dw;
			if(seats[h][w] != null)
				if(seats[h][w].occupied)
					return true;
				else
					return false;
		}
		return false;
	}
}
