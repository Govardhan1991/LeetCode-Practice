
public class LC_Contest42 {

    public static void main(String[] args) {

        int nums[] = {2,3,2};

        int ar[] = findErrorNums(nums);
        System.out.println(ar[0] + " " + ar[1]);

        String s = "aaa";
        System.out.println(countSubstrings(s));
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



    public static int findLongestChain(int[][] pairs) {


        return 0;
    }

    public static void mergerSortFor2d(int[][] pairs, int start, int end)
    {
        //basecase
        if(start>=end)
            return;

        //split
        int mid = (start+end)/2;
        mergerSortFor2d(pairs, start, mid);
        mergerSortFor2d(pairs, mid+1, end);

        //merge

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
