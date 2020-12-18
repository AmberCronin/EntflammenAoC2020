import java.util.*;
import java.util.function.Function;
import java.io.*;

public class Day18 {
	static interface Expression {
		long evaluate ();
	}
	static class LiteralExpression implements Expression {

		long value;
		
		public LiteralExpression(String s) {
			value = Integer.valueOf(s);
		}
		@Override
		public long evaluate() {
			return value;
		}
	}
	static class StandardExpression implements Expression {
		Expression left, right;
		char op;
		public StandardExpression(Expression l, Expression r, char o) {
			left = l;
			right = r;
			op = o;
		}
		@Override
		public long evaluate() {
			return (op == '+' ? left.evaluate() + right.evaluate() : left.evaluate() * right.evaluate());
		}
	}
	static class ParentheticalExpression implements Expression {
		Expression inner;
		public ParentheticalExpression(Expression in) {
			inner = in;
		}
		@Override
		public long evaluate() {
			return inner.evaluate();
		}
	}
	public static void main(String[] args) {
		part2();
	}
	
	static void part1() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day18.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}

		long sum = 0;
		
		while(s.hasNextLine()) {
			sum += parse(s.nextLine().replace(" ", ""));
		}
		
		System.out.println(sum);
	}
	
	static void part2() {
		Scanner s = null;
		try
		{
			s = new Scanner(new File("/home/entflammen/git/EntflammenAoC2020/src/day18.dat"), "ISO-8859-1");
		}
		catch(FileNotFoundException e) {}

		long sum = 0;
		
		while(s.hasNextLine()) {
			sum += parse2(s.nextLine().replace(" ", ""));
		}
		
		System.out.println(sum);

	}
	
	/*
	 * M -> M * A | A
	 * A -> A * P | P
	 * P -> L | (M)
	 */
	
	static long parse2(String str) {
		return parseMultiplicativeExpression(str).evaluate();
	}
	static Expression parseMultiplicativeExpression(String str) {
		Character[] af = new Character[] {'*'};
		List<Character> funcs = Arrays.asList(af);
		Expression ret = parseHelper(str, funcs, (s) -> parseMultiplicativeExpression(s), (s) -> parseAdditiveExpression(s), (s) -> parseAdditiveExpression(s));
		return ret;
	}
	static Expression parseAdditiveExpression(String str) {
		Character[] af = new Character[] {'+'};
		List<Character> funcs = Arrays.asList(af);
		Expression ret = parseHelper(str, funcs, (s) -> parseAdditiveExpression(s), (s) -> parseParentheticalExpression2(s), (s) -> parseParentheticalExpression2(s));
		return ret;
	}
	
	static long parse(String str) {
		return parseStandardExpression(str).evaluate();
	}
	
	/*
	 * S -> S + S | S * S | P
	 * P -> L | (S)
	 * 
	 */
	
	protected static Expression parseStandardExpression(String str) {
		Character[] af = new Character[] {'+', '*'};
		List<Character> funcs = Arrays.asList(af);
		Expression ret = parseHelper(str, funcs, (s) -> parseStandardExpression(s), (s) -> parseStandardExpression(s), (s) -> parseParentheticalExpression(s));
		return ret;
	}
	protected static  Expression parseParentheticalExpression(String str) {
		Expression expression = null;
		
		// P -> L | V
		expression = parseLiteralExpression(str);
		if(expression != null)
			return expression;		
		
		// P -> (A)
		int parenCounter = 1;
		for(int i = str.length() - 2; i >= 1; i--) {
			char c = str.charAt(i);
			
			if(c == ')')
				parenCounter++;
			if(c == '(')
				parenCounter--;
			
			if(parenCounter != 0 && str.charAt(0) == '(' && str.charAt(str.length() - 1) == ')')
				continue;
			else
				return null;
		}
		
		Expression innerExpression = parseStandardExpression(str.substring(1, str.length() - 1));
		if(innerExpression != null)
			return new ParentheticalExpression(innerExpression);
		else
			return null;
	}
	protected static  Expression parseParentheticalExpression2(String str) {
		Expression expression = null;
		
		// P -> L | V
		expression = parseLiteralExpression(str);
		if(expression != null)
			return expression;		
		
		// P -> (A)
		int parenCounter = 1;
		for(int i = str.length() - 2; i >= 1; i--) {
			char c = str.charAt(i);
			
			if(c == ')')
				parenCounter++;
			if(c == '(')
				parenCounter--;
			
			if(parenCounter != 0 && str.charAt(0) == '(' && str.charAt(str.length() - 1) == ')')
				continue;
			else
				return null;
		}
		
		Expression innerExpression = parseMultiplicativeExpression(str.substring(1, str.length() - 1));
		if(innerExpression != null)
			return new ParentheticalExpression(innerExpression);
		else
			return null;
	}


	protected static Expression parseLiteralExpression(String str) {
		try {
			Integer val = Integer.valueOf(str);
			return new LiteralExpression(str);
		}
		catch(Exception e) {
			return null;
		}
	}
	protected static Expression parseHelper(String str, List<Character> func, Function<String, Expression> lFunc, Function<String, Expression> rFunc, Function<String, Expression> failFunc) {
		Expression expression = null;
		Expression lExpr, rExpr;

		int parenCounter = 0;
		for(int i = str.length() - 1; i >= 0; i--) {
			char c = str.charAt(i);
			
			if(c == ')')
				parenCounter++;
			if(c == '(')
				parenCounter--;

			if(parenCounter == 0 && func.contains(c)) {

				lExpr = lFunc.apply(str.substring(0, i));
				rExpr = rFunc.apply(str.substring(i+1));
				
				if(lExpr != null && rExpr != null)
					return new StandardExpression(lExpr, rExpr, c);
				else
					return null;
			}
		}
		return failFunc.apply(str);
	}

}
