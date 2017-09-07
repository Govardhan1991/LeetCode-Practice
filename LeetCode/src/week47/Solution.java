package week47;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {

        //int[] ar = {3,4,2,3};
        //System.out.println(checkPossibility(ar));

        //int ar[] = {113, 215, 221};
        //System.out.println(pathSum(ar));

        int n=3, k=2;
        int[] ar = constructArray(n, k);
        System.out.println(ar[0] + " " + ar[1] + " " + ar[2]);
    }

    /*
        665. Non-decreasing Array
            Given an array with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.

            We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).

            Example 1:
            Input: [4,2,3]
            Output: True
            Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
            Example 2:
            Input: [4,2,1]
            Output: False
            Explanation: You can't get a non-decreasing array by modify at most one element.
            Note: The n belongs to [1, 10,000].
     */

    public static boolean checkPossibility(int[] nums)
    {
        if(nums.length==0)
            return false;

        int count=0;
        for(int i=0; i<nums.length-1; i++)
        {
            if(nums[i]>nums[i+1])
            {
                count++;
                if(i>0&&nums[i-1]>nums[i+1])
                    nums[i+1] = nums[i];
                else
                    nums[i] = nums[i+1];
            }
        }

        return count<=1;
    }

    public static boolean checkPossibility1(int[] nums) {

        if(nums==null||nums.length==0)
            return false;

        if(nums.length==1)
            return true;

        boolean chance = false;


        for(int i=1; i<nums.length; i++)
        {
            if(nums[i]<nums[i-1])
            {
                if(!chance) {
                    chance = true;
                    int leftMax = getMax(nums, i-2);
                    if(leftMax > nums[i])
                    {
                        int rightMin = getMin(nums, i+1);
                        if(rightMin<=nums[i])
                            return false;
                        else{
                            nums[i] = getMax(nums, i-1);
                        }
                    }
                }
                else
                    return false;
            }
        }
        return true;
    }

    public static int getMax(int[] ar, int i)
    {
        int max = Integer.MIN_VALUE;
        for(int j=0; j<=i; j++)
            if(ar[j]>max)
                max = ar[j];
        return max;
    }

    public static int getMin(int[] ar, int i)
    {
        int min = Integer.MAX_VALUE;
        for(int j=i; j<ar.length; j++)
            if(ar[j]<min)
                min = ar[j];
        return min;
    }

    /*
        666. Path Sum IV
        If the depth of a tree is smaller than 5, then this tree can be represented by a list of three-digits integers.

        For each integer in this list:
        The hundreds digit represents the depth D of this node, 1 <= D <= 4.
        The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8. The position is the same as that in a full binary tree.
        The units digit represents the value V of this node, 0 <= V <= 9.
        Given a list of ascending three-digits integers representing a binary with the depth smaller than 5. You need to return the sum of all paths from the root towards the leaves.

        Example 1:
        Input: [113, 215, 221]
        Output: 12
        Explanation:
        The tree that the list represents is:
            3
           / \
          5   1

        The path sum is (3 + 5) + (3 + 1) = 12.
        Example 2:
        Input: [113, 221]
        Output: 4
        Explanation:
        The tree that the list represents is:
            3
             \
              1

        The path sum is (3 + 1) = 4.

     */

    static int sum=0;
    static Map<Integer, Integer>tree = new HashMap<Integer, Integer>();

    public static int pathSum(int[] nums) {

        for(int num:nums)
        {
            int val = num%10;
            int key = num/10;
            tree.put(key, val);
        }

        traverseSum(nums[0]/10, 0);
        return sum;
    }

    private static void traverseSum(int root, int preSum) {
        int cursum = preSum + tree.get(root);

        int left = (root/10 + 1)*10 + (root%10)*2-1;
        int right = (root/10 + 1)*10 + (root%10)*2;
        if(!tree.containsKey(left)&&!tree.containsKey(right))
        {
            sum += cursum;
            return;
        }
        if(tree.containsKey(left))
            traverseSum(left, cursum);

        if(tree.containsKey(right))
            traverseSum(right, cursum);
    }

    //brute force
    public static int pathSum2(int[] nums) {

        int ar[] = new int[15];
        for(int i=0; i<ar.length; i++)
            ar[i] = -1;
        for(int num:nums)
        {
            int z = num%10;
            num/=10;
            int y = num%10;
            num/=10;
            int x = num%10;

            ar[(int)Math.pow(2, x-1)+(y-1)-1] = z;
        }

        int sum=0;

        int i=7;
        while(i<=14)
        {
            if(ar[i]!=-1)
            {
                sum+=ar[i];
                sum+=ar[(i-1)/2];
                sum+=ar[((i-1)/2-1)/2];
                sum+=ar[0];
            }
            i++;
        }

        i=3;
        while(i<=6)
        {
            if(ar[i]!=-1)
            {
                if(ar[i*2+1]==-1&&ar[i*2+2]==-1)
                {
                    sum+=ar[i];
                    sum+=ar[(i-1)/2];
                    sum+=ar[0];
                }
            }
            i++;
        }

        i=1;
        while (i<=2)
        {
            if(ar[i]!=-1) {
                if (ar[i * 2 + 1] == -1 && ar[i * 2 + 2] == -1 && ar[(i * 2 + 1) * 2 + 1] == -1 && ar[(i * 2 + 1) * 2 + 2] == -1 && ar[(i * 2 + 2) * 2 + 1] == -1 && ar[(i * 2 + 2) * 2 + 2] == -1) {
                    sum += ar[i];
                    sum += ar[0];
                }
            }
            i++;
        }

        if(nums.length==1)
            sum=ar[0];


        return sum;
    }


    /*
        667. Beautiful Arrangement II

        Given two integers n and k, you need to construct a list which contains n different positive integers ranging from 1 to n and obeys the following requirement:
        Suppose this list is [a1, a2, a3, ... , an], then the list [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] has exactly k distinct integers.

        If there are multiple answers, print any of them.

        Example 1:
        Input: n = 3, k = 1
        Output: [1, 2, 3]
        Explanation: The [1, 2, 3] has three different positive integers ranging from 1 to 3, and the [1, 1] has exactly 1 distinct integer: 1.
        Example 2:
        Input: n = 3, k = 2
        Output: [1, 3, 2]
        Explanation: The [1, 3, 2] has three different positive integers ranging from 1 to 3, and the [2, 1] has exactly 2 distinct integers: 1 and 2.
        Note:
        The n and k are in the range 1 <= k < n <= 104.
     */

    /*
    Arrange nums in the sequence 1,k+1,2,k,3,k-1,...,k+2,k+3,...,n.
     */
    public static int[] constructArray(int n, int k) {

        int l=1, r=k+1;
        int []ar = new int[n];
        int i=0;
        while(l<=r)
        {
            if(i%2==0) {
                ar[i] = l;
                l++;
            }
            else {
                ar[i] = r;
                r--;
            }
            i++;
        }

        r=k+2;
        while(i<n) {
            ar[i] = r;
            i++;
            r++;
        }
        return ar;
    }
}
