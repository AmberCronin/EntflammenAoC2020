import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day4 {
	
	public static void main(String[] args)
	{
		part1();
		part2();
	}


	private static void part1() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day4.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}
		
		ArrayList<HashMap<String, String>> passports = new ArrayList<HashMap<String,String>>();
		
		HashMap<String, String> working = new HashMap<String, String>();
		
		while(s.hasNextLine())
		{
			String line = s.nextLine();
			if (line.equals(""))
			{
				passports.add(working);
				working = new HashMap<String, String>();
				continue;
			}
			
			String[] splt = line.split(" ");
			for(String sp : splt)
			{
				String[] keysplit = sp.split(":");
				working.put(keysplit[0], keysplit[1]);
			}
		}
		
		int validcnt = 0;
		System.out.println(passports.size());
		
		for(HashMap<String, String> pass : passports)
		{
			if(passportValid(pass))
				validcnt++;
		}
		System.out.println(validcnt);
	}

	private static boolean passportValid(HashMap<String, String> passport)
	{
		String[] keys = new String[] {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid" };
		
		for(String s : keys)
		{
			if (passport.get(s) == null)
			{
				return false;
			}
		}
		
		int byr = Integer.parseInt(passport.get("byr"));
		
		if(!(byr >= 1920 && byr <= 2002))
			return false;
		
		int iyr = Integer.parseInt(passport.get("iyr"));
		
		if(!(iyr >= 2010 && iyr <= 2020))
			return false;
		
		int eyr = Integer.parseInt(passport.get("eyr"));
		
		if(!(eyr >= 2020 && eyr <= 2030))
			return false;
		
		String h = passport.get("hgt");
		boolean inches = h.contains("in");
		
		if(inches)
		{
			String[] hgt = h.split("in");
			if(!(Integer.parseInt(hgt[0]) >= 59 && Integer.parseInt(hgt[0]) <= 76))
				return false;
		}
		else
		{
			String[] hgt = h.split("cm");
			if(!(Integer.parseInt(hgt[0]) >= 150 && Integer.parseInt(hgt[0]) <= 193))
				return false;
		}
		
		String hcl = passport.get("hcl");
		
		if(!(hcl.matches("#[a-f|0-9]+") && hcl.length() == 7))
			return false;
		
		String ecl = passport.get("ecl");
		List<String> validEyes = Arrays.asList(new String[] { "amb", "blu", "brn", "gry", "grn", "hzl", "oth" });
		
		if(!validEyes.contains(ecl))
			return false;
		
		String pid = passport.get("pid");
		if(!pid.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]"))
			return false;
		
		
		return true;
	}
	
	private static void part2() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day4.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}
		
		ArrayList<HashMap<String, String>> passports = new ArrayList<HashMap<String,String>>();
		
		HashMap<String, String> working = new HashMap<String, String>();
		
		while(s.hasNextLine())
		{
			String line = s.nextLine();
			if (line.equals(""))
			{
				passports.add(working);
				working = new HashMap<String, String>();
				continue;
			}
			
			String[] splt = line.split(" ");
			for(String sp : splt)
			{
				String[] keysplit = sp.split(":");
				working.put(keysplit[0], keysplit[1]);
			}
		}
		
		int validcnt = 0;
		System.out.println(passports.size());
		
		for(HashMap<String, String> pass : passports)
		{
			if(passportValid(pass))
				validcnt++;
		}
		System.out.println(validcnt);
		
		
		
	}

	
}
