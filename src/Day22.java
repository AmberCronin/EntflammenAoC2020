import java.util.*;
import java.io.*;

public class Day22 {
	public static void main(String[] args) {
		//part1();
		part2();
	}
	static void part1() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day22.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}
		
		LinkedList<Integer> p1 = new LinkedList<Integer>();
		LinkedList<Integer> p2 = new LinkedList<Integer>();
		boolean insertp1 = false;
		while(s.hasNextLine()) {
			String line = s.nextLine();
			if(line.equals("")) {
				continue;
			}
			if(line.contains("Player")) {
				insertp1 = !insertp1;
				continue;
			}
			if(insertp1) {
				p1.addLast(Integer.valueOf(line));
			}
			else {
				p2.addLast(Integer.valueOf(line));
			}
		}
		
		while(p1.size() != 0 && p2.size() != 0) {
			int card1, card2;
			card1 = p1.removeFirst();
			card2 = p2.removeFirst();
			if(card1 > card2) {
				p1.addLast(card1);
				p1.addLast(card2);
			}
			else {
				p2.addLast(card2);
				p2.addLast(card1);
			}
		}

		LinkedList<Integer> winner = (p1.size() == 0) ? p2 : p1;
		int mult = winner.size();
		long sum = 0;
		for(Integer i : winner) {
			sum += (i * mult--);
		}
		System.out.println(sum);
	}
	
	static class GameState {
		LinkedList<Integer> p1, p2;

		public GameState(LinkedList player1, LinkedList player2) {
			p1 = player1;
			p2 = player2;
		}
		@Override
		public boolean equals(Object o) {
			if(!(o instanceof GameState))
				return false;
			GameState g = (GameState) o;
			if(g.p1.equals(this.p1) && g.p2.equals(this.p2))
				return true;
			return false;
		}
	}
	
	static class Game {
		HashSet<GameState> prevGameStates = new HashSet<GameState>();
		
		LinkedList<Integer> p1, p2;
		
		public Game(LinkedList player1, LinkedList player2) {
			p1 = player1;
			p2 = player2;
		}
		
		public LinkedList<Integer> simWinner() {
			return (runGame() == 1) ? p1 : p2;
		}
		
		@Override
		public int hashCode() {
			return p1.hashCode() + p2.hashCode();
		}
		
		@Override
		public boolean equals(Object o) {
			if(!(o instanceof Game))
				return false;
			Game g = (Game) o;
			if(g.p1.equals(this.p1) && g.p2.equals(this.p2))
				return true;
			return false;
		}
		
		public int runGame() {
			System.out.println("Running game");

			for(GameState gs : prevGameStates) {
				if(gs.p1.equals(p1) && gs.p2.equals(p2)) {
					return 1;
				}
			}
			
			while(p1.size() != 0 && p2.size() != 0) {
				if(prevGameStates.size() % 100000 == 0)
					System.out.println(prevGameStates.size());
				
				int card1, card2;
				card1 = p1.removeFirst();
				card2 = p2.removeFirst();
				
				// Check if recursive game is needed
				if(p1.size() >= card1 && p2.size() >= card2) {
					// Recursive game needed...
					LinkedList<Integer> sub1 = new LinkedList<Integer>();
					LinkedList<Integer> sub2 = new LinkedList<Integer>();
					for(int i = 0; i < card1; i++) {
						sub1.addLast(Integer.valueOf(p1.get(i)));
					}
					for(int i = 0; i < card2; i++) {
						sub2.addLast(Integer.valueOf(p2.get(i)));
					}
					Game subGame = new Game(sub1, sub2);
					int winner = subGame.runGame();
					if(winner == 1) {
						p1.addLast(card1);
						p1.addLast(card2);
					}
					else {
						p2.addLast(card2);
						p2.addLast(card1);
					}
				}
				else {
					if(card1 > card2) {
						p1.addLast(card1);
						p1.addLast(card2);
					}
					else {
						p2.addLast(card2);
						p2.addLast(card1);
					}
				}
				prevGameStates.add(new GameState(deepCopy(p1), deepCopy(p2)));
			}
			System.out.println("Finished game");

			return (p1.size() == 0) ? 2 : 1;
			
		}
		static LinkedList<Integer> deepCopy(LinkedList<Integer> list) {
			LinkedList<Integer> newList = new LinkedList<Integer>();
			for(Integer i : list) {
				newList.add(Integer.valueOf(i));
			}
			return newList;
		}
	}
	
	static void part2() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day22.inf.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}
		
		LinkedList<Integer> p1 = new LinkedList<Integer>();
		LinkedList<Integer> p2 = new LinkedList<Integer>();
		boolean insertp1 = false;
		while(s.hasNextLine()) {
			String line = s.nextLine();
			if(line.equals("")) {
				continue;
			}
			if(line.contains("Player")) {
				insertp1 = !insertp1;
				continue;
			}
			if(insertp1) {
				p1.addLast(Integer.valueOf(line));
			}
			else {
				p2.addLast(Integer.valueOf(line));
			}
		}
		
		Game game = new Game(p1, p2);
		
		System.out.println(score(game.simWinner()));
		
	}
	static long score(LinkedList<Integer> winner) {
		int mult = winner.size();
		long sum = 0;
		for(Integer i : winner) {
			sum += (i * mult--);
		}
		return sum;
	}
}
