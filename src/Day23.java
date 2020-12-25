import java.util.*;
import java.io.*;


public class Day23 {
	static class Node<T> {
		Node<T> next;
		T val;
		
		public Node(T v) {
			val = v;
		}
	}
	
	public static void main(String[] args) {
		//part1();
		part2();
	}
	static void part1() {
		String input = "158937462";
		int moves = 100;
		
		Node<Integer> first = new Node<Integer>(Integer.valueOf(input.substring(0, 1)));
		Node<Integer> pnt = first;
		for(int i = 1; i < input.length(); i++) {
			pnt.next = new Node<Integer>(Integer.valueOf(input.substring(i, i+1)));
			pnt = pnt.next;
		}
		pnt.next = first;

		pnt = first;
		
		for(int i = 0; i < moves; i++) {
			Node<Integer> destNode = getDestNode(pnt);
			
			Node<Integer> remStart = pnt.next;
			
			pnt.next = pnt.next.next.next.next;
			
			Node<Integer> destNext = destNode.next;
			
			destNode.next = remStart;
			
			remStart.next.next.next = destNext;
			
			pnt = pnt.next;
		}
		
		pnt = first;
		for(int i = 0; i < input.length(); i++) {
			System.out.print(pnt.val);
			pnt = pnt.next;
		}
	}
	static void part2() {
		String input = "158937462";
		int moves = 10000000;
		Node<Integer> first = new Node<Integer>(Integer.valueOf(input.substring(0, 1)));
		int nodeCount = 1;
		Node<Integer> pnt = first;
		for(int i = 1; i < input.length(); i++) {
			pnt.next = new Node<Integer>(Integer.valueOf(input.substring(i, i+1)));
			pnt = pnt.next;
			nodeCount++;
		}
		while(nodeCount < 1000000) {
			pnt.next = new Node<Integer>(nodeCount+1);
			nodeCount++;
			pnt = pnt.next;
		}
		pnt.next = first;

		HashMap<Integer, Node<Integer>> nodeLookup = new HashMap<Integer, Day23.Node<Integer>>();
		
		pnt = first;
		
		do {
			nodeLookup.put(pnt.val, pnt);
			pnt = pnt.next;
		} while(pnt != first);
		
		pnt = first;
		
		for(int i = 0; i < moves; i++) {
			Node<Integer> destNode = getDestNode2(pnt, nodeLookup);
			
			
			Node<Integer> remStart = pnt.next;
			
			pnt.next = pnt.next.next.next.next;
			
			Node<Integer> destNext = destNode.next;
			
			destNode.next = remStart;
			
			remStart.next.next.next = destNext;
			
			pnt = pnt.next;
		}
		
		pnt = first;
		
		long sum = 1;
		sum *= pnt.next.val;
		sum *= pnt.next.next.val;
		System.out.println(sum);
	}
	static Node<Integer> getDestNode2(Node<Integer> start, HashMap<Integer, Node<Integer>> lookup) {
		int searchVal = start.val - 1;
		if(searchVal < 1)
			searchVal = 1000000;
		while(start.next.val.equals(Integer.valueOf(searchVal)) || start.next.next.val.equals(Integer.valueOf(searchVal)) || start.next.next.next.val.equals(Integer.valueOf(searchVal))) {
			searchVal--;
			if(searchVal < 1)
				searchVal = 1000000;
		}
		Node<Integer> node = lookup.get(searchVal);
		
		if(node == null)
			System.out.println();

		return node;
		
	}

	static Node<Integer> getDestNode(Node<Integer> start) {
		Node<Integer> pnt = start.next.next.next.next;
		int searchVal = start.val - 1;

		while(pnt != start && searchVal >= 1) {
			if(start.next.val.equals(Integer.valueOf(searchVal)) || start.next.next.val.equals(Integer.valueOf(searchVal)) || start.next.next.next.val.equals(Integer.valueOf(searchVal))) {
				searchVal--;
				continue;
			}
			if(pnt.val == searchVal)
				return pnt;
			
			pnt = pnt.next;
		}
		Node<Integer> highNode = pnt;
		while(pnt != start.next) {
			highNode = (pnt.val > highNode.val ? pnt : highNode);
			pnt = pnt.next;
		}
		return highNode;
	}
}
