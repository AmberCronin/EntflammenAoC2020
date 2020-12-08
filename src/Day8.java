import java.io.*;
import java.util.*;

public class Day8 {
	
	public enum inst {
		nop,
		acc,
		jmp
	}
	
	public static class Instruction {
		public inst op;
		public int val;
		public boolean exe = false;
		
		public Instruction(String op, int val) {
			if(op.equals("nop"))
				this.op = inst.nop;
			else if(op.equals("jmp"))
				this.op = inst.jmp;
			else
				this.op = inst.acc;
			this.val = val;
		}
		
		private Instruction(inst op, int val) {
			this.op = op;
			this.val = val;
		}
		
		public void call() {
			exe = true;
		}
		public Instruction deepcopy()
		{
			return new Instruction(op, val);
		}
	}
	
	
	public static void main(String[] args)
	{

		part1();
		part2();
	}


	private static void part1() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day8.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}

		List<Instruction> operations = new LinkedList<Instruction>();
		
		while(s.hasNextLine()) {
			String line = s.nextLine();
			String[] spt = line.split(" ");
			operations.add(new Instruction(spt[0], Integer.parseInt(spt[1])));
		}
		int acc = 0;
		int intpnt = 0;
		Instruction pnt = operations.get(intpnt);
		while(!pnt.exe)
		{	
			switch(pnt.op) {
			case jmp:
				intpnt += pnt.val;
				break;
			case acc:
				acc+=pnt.val;
			case nop:
				intpnt++;
				break;
			}
			pnt.call();
			pnt = operations.get(intpnt);
		}
		
		System.out.println(acc);
	}
	
	private static void part2() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day8.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}

		List<Instruction> holy = new LinkedList<Instruction>();
		
		while(s.hasNextLine()) {
			String line = s.nextLine();
			String[] spt = line.split(" ");
			holy.add(new Instruction(spt[0], Integer.parseInt(spt[1])));
		}

		for(int i = 0; i < holy.size(); i++)
		{
			List<Instruction> operations = new LinkedList<Instruction>();
			for(int j = 0; j < holy.size(); j++)
			{
				operations.add(holy.get(j).deepcopy());
			}
			Instruction changed = operations.get(i);
			if(changed.op.equals(inst.nop))
				changed.op = inst.jmp;
			else if(changed.op.equals(inst.jmp))
				changed.op = inst.nop;
			
			boolean found = false;
			int acc = 0;
			int intpnt = 0;
			Instruction pnt = operations.get(intpnt);
			while(!pnt.exe)
			{	
				switch(pnt.op) {
				case jmp:
					intpnt += pnt.val;
					break;
				case acc:
					acc+=pnt.val;
				case nop:
					intpnt++;
					break;
				}
				pnt.call();
				try{
					pnt = operations.get(intpnt);
				}
				catch(Exception e) {
					System.out.println(acc);
					found = true;
					break;
				}
			}
			
			if(found)
				break;
		
			if(changed.op.equals(inst.nop))
				changed.op = inst.jmp;
			else if(changed.op.equals(inst.jmp))
				changed.op = inst.nop;
		}
		
		
		
	}

}
