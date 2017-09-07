package week48;

import java.util.*;

public class Solution {
    public static void main(String[] args) {

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        System.out.println(findSecondMinimumValue(root));

        int k=98368;
        System.out.println(maximumSwap(k));
    }


    /*

        Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.

        Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.

        If no such second minimum value exists, output -1 instead.

        Example 1:
        Input:
           2
          / \
         2   5
        / \
       5   7

        Output: 5
        Explanation: The smallest value is 2, the second smallest value is 5.
        Example 2:
        Input:
         2
        / \
        2   2

        Output: -1
        Explanation: The smallest value is 2, but there isn't any second smallest value.
     */
    public static int findSecondMinimumValue(TreeNode root) {

        if(root==null||root.left==null)
            return -1;

        Set<Integer> set = new TreeSet<>();
        set.add(root.val);
        dfs(root.left, set);
        dfs(root.right, set);
        if(set.size()>1)
        {
            Iterator<Integer> itr = set.iterator();
            itr.next();
            return itr.next();
        }
        return -1;
    }

    private static void dfs(TreeNode root, Set set) {
        if(root==null)
            return;;

        set.add(root.val);
        dfs(root.left, set);
        dfs(root.right, set);
    }

    /*

        Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.

        Example 1:
        Input: 2736
        Output: 7236
        Explanation: Swap the number 2 and the number 7.
        Example 2:
        Input: 9973
        Output: 9973
        Explanation: No swap.
        Note:
        The given number is in the range [0, 108]
     */
    public static int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray();

        int[] buckets = new int[10];
        for (int i = 0; i < digits.length; i++) {
            buckets[digits[i] - '0'] = i;
        }

        for (int i = 0; i < digits.length; i++) {
            for (int k = 9; k > digits[i] - '0'; k--) {
                if (buckets[k] > i) {
                    char tmp = digits[i];
                    digits[i] = digits[buckets[k]];
                    digits[buckets[k]] = tmp;
                    return Integer.valueOf(new String(digits));
                }
            }
        }

        return num;
    }

    public static int maximumSwap1(int num) {

        ArrayList<Integer> list = new ArrayList<>();
        int temp = num;
        while (temp>0){
            list.add(temp%10);
            temp = temp/10;
        }
        reverseList(list);

        ArrayList<Integer> mainList = (ArrayList<Integer>) list.clone();
        Collections.sort(list, (o1, o2) -> o2-o1);
        int fromIndex=0;
        int returnNum=0;
        int currentIndex=0;
        while (list.size()>currentIndex) {

            if (list.get(currentIndex) != mainList.get(currentIndex)) {
                int indexMax = getIndexof(list.get(currentIndex), currentIndex, mainList);
                if (indexMax > currentIndex) {
                    int maxDigit = mainList.get(indexMax);
                    int mDigit = mainList.get(currentIndex);
                    mainList.remove(indexMax);
                    mainList.remove(currentIndex);
                    mainList.add(currentIndex, maxDigit);
                    mainList.add(indexMax, mDigit);
                    int newindex = 0;
                    while (newindex < mainList.size()) {
                        returnNum = returnNum * 10 + mainList.get(newindex);
                        newindex++;
                    }
                    return returnNum;
                }
            }
            currentIndex++;
        }
            /*
            int max = list.get(currentIndex);
            int indexMax = getIndexof(max, fromIndex, mainList);
            if(indexMax>currentIndex)
            {
                int maxDigit = mainList.get(indexMax);
                int mDigit = mainList.get(currentIndex);
                mainList.remove(indexMax);
                mainList.remove(currentIndex);
                mainList.add(currentIndex, maxDigit);
                mainList.add(indexMax, mDigit);
                int newindex=0;
                while(newindex<mainList.size()){
                    returnNum = returnNum*10+mainList.get(newindex);
                    newindex++;
                }
                return returnNum;
            }
            fromIndex++;
            currentIndex++;
        }
        */
        return num;
    }

    private static void reverseList(ArrayList<Integer> list) {
        for(int i=0; i<list.size()/2; i++)
        {
            int l = list.get(i);
            int r = list.get(list.size()-i-1);
            list.remove(list.size()-1-i);
            list.add(list.size()-i, l);
            list.remove(i);
            list.add(i, r);
        }
    }

    private static int getIndexof(int max, int fromIndex, ArrayList<Integer> main) {

        int maxIndex = main.size()-1;
        while (fromIndex<maxIndex){
            if(max == main.get(maxIndex))
                return maxIndex;
            maxIndex--;
        }
        return -1;
    }

    /*

        Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] (R >= L). You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.

        Example 1:
        Input:
            1
           / \
          0   2

          L = 1
          R = 2

        Output:
            1
              \
               2
        Example 2:
        Input:
            3
           / \
          0   4
           \
            2
           /
          1

          L = 1
          R = 3

        Output:
              3
             /
           2
          /
         1
     */

    public TreeNode trimBST(TreeNode root, int L, int R) {

        if (root==null)
            return null;

        if(root.val>=L&&root.val<=R)
        {
            root.left = trimBST(root.left, L, R);
            root.right = trimBST(root.right, L, R);
            return root;
        }else{
            if(root.val<L)
                return trimBST(root.right, L, R);
            else
                return trimBST(root.left, L, R);
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
  }
}
