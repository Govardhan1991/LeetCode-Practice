package week29;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class Solution{
	private static int sum=0;
	public static void main(String[] args){
		int a[] = {1,4,3,2};
		//arrayPairSum(a);
		
//		TreeNode root = new TreeNode(1);
//		root.left = new TreeNode(2);
//		root.right= new TreeNode(3);
//		root.left.left = new TreeNode(7);
//		root.right.right = new TreeNode(9);
//		System.out.println(findTilt(root));
		
		//int M[][] = {{0,1,1,0},{0,1,1,0},{0,0,0,1}};
		int M[][] = {{1,1,1}};
		//System.out.println(longestLine2(M));
		
		System.out.println(nearestPalindromic("127"));
		List<String> list = new ArrayList<String>();
		list.add("12:12");
		list.add("12:13");
		System.out.println(findMinDifference(list));
		
	}
	
	public static int findMinDifference(List<String> timePoints) {
		
		TreeSet<Integer> ts = new TreeSet<Integer>();
		for(String time:timePoints)
		{
			String[] split = time.split(":");
			int hh = Integer.parseInt(split[0]);
			int mm = Integer.parseInt(split[1]);
			if(ts.contains(hh*60+mm)){
				return 0;
			}
			ts.add(hh*60+mm);
		}
		
		int min=-1;
		Iterator<Integer> itr = ts.iterator();
		int first = itr.next();
		int c0=first;
		int second = itr.next();
		min = second-first;
		first = second;
		int clast=second;
		while(itr.hasNext())
		{
			second = itr.next();
			min = min>second-first?second-first:min;
			first = second;
			clast = second;
		}
		min = min>(24*60-clast)+c0?(24*60-clast)+c0:min;
		
		return min;
    }
	
	public static String nearestPalindromic(String n) {
        Long number = Long.parseLong(n);
        Long big = findHigherPalindrome(number + 1);
        Long small = findLowerPalindrome(number - 1);
        return Math.abs(number - small) > Math.abs(big - number) ? String.valueOf(big) : String.valueOf(small);
    }
	
	public static int longestLine(int[][] M) {
        
		if(M.length<1)
        {
            return 0;
        }
		
		int rows = M.length;
		int cols = M[0].length;
		int max1s = 0;
		int temp = 0;
		
		//rows
		for(int i=0; i<rows; i++)
		{
			for(int j=0; j<cols; j++)
			{
				if(M[i][j]==1)
				{
					temp++;
				}else
				{
					temp=0;
				}
				max1s = Math.max(temp, max1s);
			}
			temp=0;
		}
		temp=0;
		
		//cols
		for(int j=0; j<cols; j++)
		{
			for(int i=0; i<rows; i++)
			{
				if(M[i][j]==1)
				{
					temp++;
				}else
				{
					temp=0;
				}
				max1s = Math.max(temp, max1s);
			}
			temp=0;
		}
		
		temp=0;
		
		//diag
		for(int i=rows-1, itr=0; i>=0; i--, itr++)
		{
			for(int j=0, ri=i, itrLimit=itr; itrLimit>=0&&j<cols; j++, ri++, itrLimit--)
			{
				if(M[ri][j]==1)
				{
					temp++;
				}else
				{
					temp=0;
				}
				max1s = Math.max(temp, max1s);
			}
			temp=0;
		}
		//diag - 2nd
		for(int j=1, itr=cols-2; j<=cols-1; j++, itr--)
		{
			for(int i=0, jc=j, itrLimit=itr; itrLimit>=0&&i<rows&&jc<cols; i++, jc++, itrLimit--)
			{
				if(M[i][jc]==1)
				{
					temp++;
				}else
				{
					temp=0;
				}
				max1s = Math.max(temp, max1s);
			}
			temp=0;
		}
		
		//Anti diag
		for(int i=rows-1,itr=0; i>=0; i--,itr++)
		{
			for(int j=cols-1, ri=i,itrLimit=itr; itrLimit>=0&&j>=0&&ri<rows;j--,ri++, itrLimit--)
			{
				if(M[ri][j]==1)
				{
					temp++;
				}else
				{
					temp=0;
				}
				max1s = Math.max(temp, max1s);
			}
			temp=0;
		}
		
		//Anti diag - 2nd
		for(int j=cols-2, itr=rows-2; j>=0 ; j--, itr--)
		{
			for(int i=0, jc=j, itrLimit=itr; itrLimit>=0&&jc>=0&&i<rows; i++, jc--, itrLimit--)
			{
				if(M[i][jc]==1)
				{
					temp++;
				}else
				{
					temp=0;
				}
				max1s = Math.max(temp, max1s);
			}
			temp=0;
		}
		
		return max1s;
    }
	
	public static int longestLine2(int[][] M) {
		
		int rows = M.length;
		if(rows<1) return 0;
		int cols = M[0].length;
		int[][][] dp = new int[rows][cols][4];
		int max=0;
		int tempMax1, tempMax2;
		
		for(int i=0; i<rows; i++)
		{
			for(int j=0; j<cols; j++)
			{
				if(M[i][j]==0) continue;
				
				for(int k=0; k<4; k++)
					dp[i][j][k]=1;
				
				if(j>0&&M[i][j-1]>0) dp[i][j][0] += dp[i][j-1][0];  //Horizontal count
				
				if(i>0&&M[i-1][j]>0) dp[i][j][1] += dp[i-1][j][1];  //Vertical count
				
				if(i>0&&j>0&&M[i-1][j-1]>0) dp[i][j][2] += dp[i-1][j-1][2]; //Diagonal count
				
				if(i>0&&j<cols-1&&M[i-1][j+1]>0) dp[i][j][3] += dp[i-1][j+1][3]; //Anti-diagonal count
				
				tempMax1 = Math.max(dp[i][j][0], dp[i][j][0]);
				tempMax2 = Math.max(dp[i][j][2], dp[i][j][3]);
				tempMax1 = Math.max(tempMax1, tempMax2);
				max = Math.max(tempMax1, max);
			}
		}
		
		return max;
	}

	/*
	561. Array Partition
	Difficulty: Easy
	Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.

	Example 1:
	Input: [1,4,3,2]

	Output: 4
	Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).
	Note:
	n is a positive integer, which is in the range of [1, 10000].
	All the integers in the array will be in the range of [-10000, 10000].
	 */
 	public static int arrayPairSum(int[] nums) {
		Arrays.sort(nums);
		int sum=0;
		for(int i=0; i<nums.length/2;i++)
		{
			sum+=nums[2*i];
		}
		
		//System.out.println(sum);
		return sum;
	}
	
 	public static Long findHigherPalindrome(Long limit) {
        String n = Long.toString(limit);
        char[] s = n.toCharArray(); // limit
        int m = s.length;
        char[] t = Arrays.copyOf(s, m); // target
        for (int i = 0; i < m / 2; i++) {
            t[m - 1 - i] = t[i];
        }
        for (int i = 0; i < m; i++) {
            if (s[i] < t[i]) {
                return Long.parseLong(String.valueOf(t)); 
            } else if (s[i] > t[i]) { 
                for (int j = (m - 1) / 2; j >= 0; j--) {
                    if (++t[j] > '9') {
                        t[j] = '0';
                    } else {
                        break;
                    }
                }
                // make it palindrome again
                for (int k = 0; k < m / 2; k++) {
                    t[m - 1 - k] = t[k];
                }
                return Long.parseLong(String.valueOf(t)); 
            }
        }
        return Long.parseLong(String.valueOf(t));    
    }
    
 	public static Long findLowerPalindrome(Long limit) {
        String n = Long.toString(limit);
        char[] s = n.toCharArray();
        int m = s.length;
        char[] t = Arrays.copyOf(s, m);
        for (int i = 0; i < m / 2; i++) {
            t[m - 1 - i] = t[i];
        }
        for (int i = 0; i < m; i++) {
            if (s[i] > t[i]) {
                return Long.parseLong(String.valueOf(t)); 
            } else if (s[i] < t[i]) {
                for (int j = (m - 1) / 2; j >= 0; j--) {
                    if (--t[j] < '0') {
                        t[j] = '9';
                    } else {
                        break;
                    }
                }
                if (t[0] == '0') {
                    char[] a = new char[m - 1];
                    Arrays.fill(a, '9');
                    return Long.parseLong(String.valueOf(a)); 
                }
                // make it palindrome again
                for (int k = 0; k < m / 2; k++) {
                    t[m - 1 - k] = t[k];
                }
                return Long.parseLong(String.valueOf(t)); 
            }
        }
         return Long.parseLong(String.valueOf(t));  
    }

    /*
	Difficulty: Easy
	Given a binary tree, return the tilt of the whole tree.

	The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and the sum of all right subtree node values. Null node has tilt 0.

	The tilt of the whole tree is defined as the sum of all nodes' tilt.

	Example:
	Input:
			 1
		   /   \
		  2     3
	Output: 1
	Explanation:
	Tilt of node 2 : 0
	Tilt of node 3 : 0
	Tilt of node 1 : |2-3| = 1
	Tilt of binary tree : 0 + 0 + 1 = 1
	Note:

	The sum of node values in any subtree won't exceed the range of 32-bit integer.
	All the tilt values won't exceed the range of 32-bit integer.
     */

    private static int tilt;
	public static int findTilt(TreeNode root)
	{
		if(root==null)
			return 0;
		int s = findTiltHelp(root);
		//int r = findTiltHelp(root.right);
		return s;
	}

	private static int findTiltHelp(TreeNode root) {
		if(root==null)
			return 0;

		int left = findTiltHelp(root.left);
		int right = findTiltHelp(root.right);
		tilt += Math.abs(left-right);
		return root.val;
	}


	public static int findTilt1(TreeNode root)
	{
		if(root==null)
			return 0;
		iterateAllNodes(root);
		return sum;
	}
	
	public static void iterateAllNodes(TreeNode root)
	{
		if(root==null)
			return;
		iterateAllNodes(root.left);
		iterateAllNodes(root.right);
		findTiltSingleNode(root);
		return;
	}
	
	public static void findTiltSingleNode(TreeNode root) {
	    
		if(root==null)
			return ;
		
		int left = sumUp(root.left);
		int right = sumUp(root.right);
		sum += Math.abs(left-right);
	}
	
	public static int sumUp(TreeNode root) {
		if(root==null)
			return 0;
		
		int sum = root.val+sumUp(root.left)+sumUp(root.right);
		return sum;
	}
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
}
