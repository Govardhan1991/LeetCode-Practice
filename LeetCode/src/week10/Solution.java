package week10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class Solution {

	public static void main(String[] args)
	{
		int [][]grid = {{0,1,0,0},
		                {1,1,1,0},
		                {0,1,0,0},
		                {1,1,0,0}};
		//System.out.println(islandPerimeter(grid));
		
		int[] nums = {203125577,-349566234,230332704,48321315,66379082,386516853,50986744,-250908656,-425653504,-212123143};
		//int[] nums = {1,2,3};
		//System.out.println(minMoves2(nums));
		ListNode root = new ListNode(1);
		root.next = new ListNode(4);
		root.next.next = new ListNode(7);
		
		ListNode root2 = new ListNode(2);
		root2.next = new ListNode(3);
		root2.next.next = new ListNode(8);
		
		ListNode[] lists = new ListNode[]{root, root2};
		ListNode head = mergeKLists(lists);
//		root.next.next.next = new ListNode(4);
//		root.next.next.next.next = new ListNode(5);
		
		//System.out.println(palidromeCheck(root));
		//System.out.println(palindromeCheckWithStack(root));
		//System.out.println(isPalindrome1(root));
		//root = reverseBetween(root, 1, 5);
		System.out.println("\n");
	}

	/*
	463. Island Perimeter My SubmissionsBack to Contest
	User Accepted: 513
	User Tried: 753
	Total Accepted: 541
	Total Submissions: 874
	Difficulty: Easy
	You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells). The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

	Example:

	[[0,1,0,0],
	 [1,1,1,0],
	 [0,1,0,0],
	 [1,1,0,0]]

	Answer: 16
	Explanation: The perimeter is the 16 yellow stripes in the image below:

	 */
	public static int islandPerimeter(int[][] grid) {
        
		int perimeter=0;
		if(grid.length==0)
			return 0;
		for(int i=0; i<grid.length; i++)
		{
			for(int j=0; j<grid[0].length; j++)
			{
				if(grid[i][j]==1)
				{
					//Corners
					if(i==0)
						perimeter++;
					if(j==0)
						perimeter++;
					if(i==grid.length-1)
						perimeter++;
					if(j==grid[0].length-1)
						perimeter++;
					
					if(i!=0)
					{
						if(grid[i-1][j]==0)
						{
							perimeter++;
						}
					}
					if(i!=grid.length-1)
					{
						if(grid[i+1][j]==0)
						{
							perimeter++;
						}
					}
					
					if(j!=0)
					{
						if(grid[i][j-1]==0)
						{
							perimeter++;
						}
					}
					if(j!=grid[0].length-1)
					{
						if(grid[i][j+1]==0)
						{
							perimeter++;
						}
					}
				}
			}
		}
		return perimeter;
    }

    public static int islandPerimeterDFS(int[][] grid){

		if(grid.length==0)
			return 0;

		int perimeter=0;
		for(int i=0; i<grid.length; i++)
			for(int j=0; j<grid[0].length; j++)
			{
				if(grid[i][j]==1) {
					perimeter = islandPerimeterDFSHelper(grid, i, j);
				}
			}

		return  perimeter;
	}

	private static int islandPerimeterDFSHelper(int[][] grid, int i, int j) {
    	grid[i][j]=-1;
    	int perimeter = 0;

    	if(i-1<0||grid[i-1][j]==0) perimeter++;
    	else if (grid[i-1][j]==1) perimeter+=islandPerimeterDFSHelper(grid, i-1, j);

    	if(i+1>=grid.length || grid[i+1][j]==0) perimeter++;
    	else if(grid[i+1][j]==1) perimeter+=islandPerimeterDFSHelper(grid, i+1, j);

		if(j-1<0||grid[i][j-1]==0) perimeter++;
		else if (grid[i][j-1]==1) perimeter+=islandPerimeterDFSHelper(grid, i, j-1);

		if(j+1>=grid[0].length || grid[i][j+1]==0) perimeter++;
		else if(grid[i][j+1]==1) perimeter+=islandPerimeterDFSHelper(grid, i, j+1);

		return perimeter;
	}

	/*
	462. Minimum Moves to Equal Array Elements II
	Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, where a move is incrementing a selected element by 1 or decrementing a selected element by 1.

	You may assume the array's length is at most 10,000.

	Example:

	Input:
	[1,2,3]

	Output:
	2

	Explanation:
	Only two moves are needed (remember each move increments or decrements one element):

	[1,2,3]  =>  [2,2,3]  =>  [2,2,2]
	 */
	public static int minMoves2(int[] nums) {
        
		Integer[] ar = new Integer[nums.length];
		for(int i=0; i<nums.length; i++)
		{
			ar[i] = nums[i];
		}
		List<Integer> list = Arrays.asList(ar);
		Collections.sort(list);
		int n=list.size();
		int median=0;
		int median1=0;
		int median2=0;
		int least=0;
		if(n%2==1)
		{
			median = list.get((n)/2);
			for(int i=0; i<nums.length; i++)
			{
				least+=Math.abs(nums[i]-median);
			}
			return least;
		}else{
			median1 = list.get(n/2-1);
			median2 = list.get(n/2);
			for(int i=0; i<nums.length; i++)
			{
				least+=Math.abs(nums[i]-median1);
			}
			int temp=least;
			least=0;
			for(int i=0; i<nums.length; i++)
			{
				least+=Math.abs(nums[i]-median2);
			}
			return least>temp?temp:least;
		}
    }


	private static int[] duplicate(int[] nums) {
		int[] clone = new int[nums.length];
		for(int i=0; i<nums.length; i++)
			clone[i] = nums[i];
		return clone;
	}
	
	public static boolean palidromeCheck(ListNode root)
	{
		
		if(root==null)
			return true;
		
		return palidromeCheck(new ListNode[]{root}, root);
	}
	
	public static ListNode reverseBetween(ListNode head, int m, int n) {
        
		int count = 1;
		ListNode itr = head;
		
		if(m==n)
			return head;
		ListNode nextNode;
		ListNode mNode = null;
		ListNode mPrev=null;
		ListNode prev=null;
		for(;itr!=null&&count<=n; count++)
		{
			if(count==m-1)
			{
				mPrev = itr;
			}
			if(count>=m && count<=n)
			{
				if(count==m)
				{
					mNode = itr;
				}
				nextNode = itr.next;
				if(count==n)
				{
					if(m!=1)
					{
						mPrev.next = itr;
						mNode.next = nextNode;
					}else{
						head = itr;
						mNode.next = nextNode;
					}
				}
				itr.next = prev;
				prev = itr;
				itr = nextNode;
			}else{
				itr=itr.next;
			}
			
		}
		
		return head;
    }
	
	private static boolean palidromeCheck(ListNode[] head, ListNode tail) {
		if(tail == null)
			return true;
		boolean b = palidromeCheck(head, tail.next);
		if(b)
		{
			if(head[0].val==tail.val)
			{
				head[0] = head[0].next;
				return true;
			}
		}
		return false;
	}
	
	private static boolean palindromeCheckWithStack(ListNode root)
	{
		Stack<Integer> stk = new Stack();
		ListNode first=root;
		ListNode last=root;
		for(;last!=null&&last.next!=null;first=first.next, last=last.next.next)
		{
			stk.add(first.val);
		}
		
		if(last!=null&&last.next==null)
			first=first.next;
		
		while(!stk.isEmpty())
		{
			if(first.val!=stk.pop())
				return false;
			
			first = first.next;
		}
		return true;
		
	}

	public static boolean isPalindrome1(ListNode head)
	{
		ListNode fast = head;
		ListNode slow = head;
		if(head==null||head.next==null)
			return true;
		while(fast!=null&&fast.next!=null)
		{
			slow=slow.next;
			fast=fast.next.next;
		}
		
		ListNode prev = slow;
		slow = slow.next;
		ListNode next=null;
		for(;slow!=null;)
		{
			next = slow.next;
			slow.next = prev;
			prev = slow;
			slow = next;
		}
		
		ListNode last = prev;
		ListNode first = head;
		while(last!=null&&last!=first&&last.next!=first)
		{
			if(first.val!=last.val)
				return false;
			
			last = last.next;
			first = first.next;
		}
		return true;
	}
	
	public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> priQ = new PriorityQueue<>(new Comparator<ListNode>() {

			@Override
			public int compare(ListNode o1, ListNode o2) {
				return o1.val-o2.val;
			}
		});
		if(lists==null)
			return null;
//        ListNode head = null;
        ListNode dummy = new ListNode(0);
//        priQ.add(dummy);
//        if(checkIfAllEmpty(lists))
//        	return null;
        
//        diff implementation given below
//        for(int i=0; i<lists.length; i++){
//        	priQ.add(lists[i]);
//        	lists[i] = lists[i].next;
//        }
        
        for(ListNode n: lists){
        	if(n!=null)
        		priQ.add(n);
        }
        
        ListNode tail = dummy;
        while(!priQ.isEmpty())
        {
        	tail.next = priQ.poll();
        	tail = tail.next;
        	if(tail.next!=null)
        		priQ.add(tail.next);
        }
        
        return dummy.next;
        //head = priQ.remove();
//        ListNode lastSortedNode = head;
//        //moveArrayPtrToNext(lists, lastSortedNode);
//        
//        ListNode curr=null;
//        for(;!checkIfAllEmpty(lists);)
//        {
//        	if(lastSortedNode.next!=null)
//        		priQ.add(lastSortedNode.next);
//        	curr = priQ.remove();
//        	lastSortedNode.next = curr;
//        	lastSortedNode = curr;
//        	moveArrayPtrToNext(lists, lastSortedNode);
//        }
//        
//        while(!priQ.isEmpty())
//        {
//        	curr = priQ.remove();
//        	lastSortedNode.next = curr;
//        	lastSortedNode = curr;
//        }
//		return head;
    }
	
//	private static void moveArrayPtrToNext(ListNode[] lists, ListNode lastSortedNode) {
//		for(int i=0; i<lists.length; i++)
//        {
//        	if(lists[i]!=null&&lists[i].equals(lastSortedNode))
//        	{
//        		lists[i] = lists[i].next;
//        		break;
//        	}
//        }
//	}
//
//	private static boolean checkIfAllEmpty(ListNode[] lists) {
//		
//		for(int i=0; i<lists.length; i++)
//			if(lists[i]!=null)
//				return false;
//		return true;
//	}

	static class ListNode{
		int val;
		ListNode next;
		public ListNode(int x)
		{
			val=x;
			ListNode next=null;
		}
	}
}
