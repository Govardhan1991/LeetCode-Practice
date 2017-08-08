public class LC_Contest42 {

    public static void main(String[] args) {

        int nums[] = {2,3,2};

        //int ar[] = findErrorNums(nums);
        //System.out.println(ar[0] + " " + ar[1]);

        String s = "aaa";
        //System.out.println(countSubstrings(s));

        int[][] ar = {{3,4}, {2,3}, {1,2}};
        //int[][] ar = {{-1,1},{-2,7},{-5,8},{-3,8},{1,3},{-2,9},{-5,2}};
        int t = findLongestChain(ar);
        System.out.println(t);
    }


    /*

            645. Set Mismatch My SubmissionsBack to Contest

            The set S originally contains numbers from 1 to n. But unfortunately, due to the data error, one of the numbers in the set got duplicated to another number in the set, which results in repetition of one number and loss of another number.

            Given an array nums representing the data status of this set after the error. Your task is to firstly find the number occurs twice and then find the number that is missing. Return them in the form of an array.

            Example 1:
            Input: nums = [1,2,2,4]
            Output: [2,3]
            Note:
            The given array size will in the range [2, 10000].
            The given array's numbers won't have any order.

    */
    public static int[] findErrorNums(int[] nums) {

        if(nums == null)
            return null;

        int dup = -1;
        for(int i=0; i<nums.length; i++)
        {
            if(nums[Math.abs(nums[i])-1]>0)
            {
                nums[Math.abs(nums[i])-1] *= -1;
            }else{
                dup = Math.abs(nums[i]);
                break;
            }
        }

        int total = 0;
        for (int x: nums) {
            total+=Math.abs(x);
        }

        int expected = nums.length * (nums.length+1)/2;
        return new int[]{dup, expected-(total-dup)};
    }


    /*

            You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.

            Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.

            Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.

            Example 1:
            Input: [[1,2], [2,3], [3,4]]
            Output: 2
            Explanation: The longest chain is [1,2] -> [3,4]
            Note:
            The number of given pairs will be in the range [1, 1000].

    */
    public static int findLongestChain(int[][] pairs) {

        //Arrays.sort(pairs, (a, b) -> a[1] - b[1]); //Could have been used rather mergeSortFor2d
        if(pairs.length==0)
            return 0;
        mergeSortFor2d(pairs, 0, pairs.length-1);
        int longestLen = 0;//First one must be in the list
        int prevStop=Integer.MIN_VALUE;
        for(int i=0; i<pairs.length; i++)
        {
            if(prevStop<pairs[i][0]) {
                prevStop=pairs[i][1];
                longestLen++;
            }
        }

        return longestLen;
    }

    public static void mergeSortFor2d(int[][] pairs, int start, int end)
    {
        //base case
        if(start>=end)
            return;

        //split
        int mid = (start+end)/2;
        mergeSortFor2d(pairs, start, mid);
        mergeSortFor2d(pairs, mid+1, end);

        //merge
        mergeData(pairs, start, mid, end);
    }

    private static void mergeData(int[][] pairs, int start, int mid, int end) {
        int[][] temp = new int[end-start+1][2];
        for(int i=0, left=start, right=mid+1; i<temp.length && (left<=mid || right<=end); i++)
        {
            if(left==mid+1) {
                temp[i][0] = pairs[right][0];
                temp[i][1] = pairs[right][1];
                right++;
            }else if(right==end+1)
            {
                temp[i][0] = pairs[left][0];
                temp[i][1] = pairs[left][1];
                left++;
            }else{
                if(pairs[left][1]>pairs[right][1])
                {
                    temp[i][0] = pairs[right][0];
                    temp[i][1] = pairs[right][1];
                    right++;
                }else{
                    temp[i][0] = pairs[left][0];
                    temp[i][1] = pairs[left][1];
                    left++;
                }
            }
        }

        for(int i=0; i<temp.length; i++, start++) {
            pairs[start][0] = temp[i][0];
            pairs[start][1] = temp[i][1];
        }
    }


    /*
            Given a string, your task is to count how many palindromic substrings in this string.

            The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

            Example 1:
            Input: "abc"
            Output: 3
            Explanation: Three palindromic strings: "a", "b", "c".
            Example 2:
            Input: "aaa"
            Output: 6
            Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
            Note:
            The input string length won't exceed 1000.
     */
    public static int countSubstrings(String s) {

        if(s==null || s.length()==0)
            return 0;

        int totalSubStrs = s.length(); //Length 1 substrings.

        //i as mid point. odd length
        for(int i=1; i<s.length()-1; i++)
        {
            for(int j=1; j<s.length(); j++)
            {
                if(i-j<0 || j+i>s.length()-1)
                    break;

                if(s.charAt(i+j) == s.charAt(i-j))
                    totalSubStrs++;
                else
                    break;
            }
        }

        //i,i+1 as mid points. even length
        for(int leftStart=0; leftStart<s.length(); leftStart++)
        {
            for(int offset=1; leftStart+offset<s.length() && leftStart-offset+1>=0 ; offset++)
            {
                if(s.charAt(leftStart-offset+1) == s.charAt(leftStart+offset))
                    totalSubStrs++;
                else
                    break;
            }
        }
        return totalSubStrs;
    }


}
