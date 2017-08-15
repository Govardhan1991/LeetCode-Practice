package week30;

public class Question {
	public static void main(String[] args){
		
//		int[][] nums = {{1,2,3,11},{4,5,6,22},{7,8,9,33}};
//		int[][] newmatrix = matrixReshape(nums, 4, 3);
//		for(int i=0; i<newmatrix.length; i++)
//		{
//			for(int j=0; j<newmatrix[0].length; j++)
//				System.out.print(newmatrix[i][j]);
//			System.out.println();
//		}
		
		//int[] nums = {1,2,5,-3,1,4};
		//System.out.println(subarraySum(nums, 2));
		//String s1 = "ab", s2 = "ab";
		String s1 = "a";
		String s2 = "zca";
		System.out.println(checkInclusion(s1, s2));
	}
	
	public static int[][] matrixReshape(int[][] nums, int r, int c) {
		
		if(nums==null)
			return null;
		int newr = r;
		int newc = c;
		
		int oldr = nums.length;
		int oldc = nums[0].length;
		
		if(newr*newc != oldr*oldc)
		{
			return nums;
		}
		
		int[][] newmatrix = new int[newr][newc];
		for(int nr=0, or=0, oc=0; nr<newr; nr++)
		{
			for(int nc=0; nc<newc; nc++)
			{
				newmatrix[nr][nc] = nums[or][oc];
				oc++;
				if(oc==oldc){
					or++;
					oc=0;
				}
			}
		}
		
		return newmatrix;
        
    }

	public static int subarraySum1(int[] nums, int k) {
        
		int count=0;
		if(nums==null)
			return count;
		
		int i=0;
		int oldi=0;
		int tempSum=0;
		int expected=k;
		
		for(;i<nums.length; i++)
		{
			tempSum+=nums[i];
			if(tempSum>=expected)
			{
				if(tempSum==expected)
					count++;
				
				for(; oldi<=i; oldi++)
				{
					tempSum-=nums[oldi];
					if(tempSum==expected)
						count++;
					if(tempSum<expected)
					{
						break;
					}
				}
			}
		}
		
		return count;
    }

	public static int subarraySum(int[] nums, int k) {
		int count=0;
		if(nums==null)
			return count;

		//Use 2-D array to track all sums.
		//Can use DP optimization here
		int sum[][] = new int[nums.length][nums.length];
		for(int i=0; i<nums.length; i++)
			for(int j=0; j<nums.length; j++)
				if(i>j)
					sum[i][j]=-1;
		
		sum[0][0]=nums[0];
		for(int i=1; i<nums.length; i++){
			sum[0][i]=sum[0][i-1]+nums[i];
		}
		
		for(int i=1; i<nums.length; i++)
		{
			for(int j=i; j<nums.length; j++)
			{
				sum[i][j]=sum[i-1][j] - sum[i-1][i-1];
			}
		}
		
		
		for(int i=0; i<nums.length; i++)
		{
			for(int j=0; j<nums.length; j++)
			{
				if(i<=j)
				{
					if(sum[i][j]==k)
						count++;
				}
			}
		}
		return count;
	}
	
	public static boolean checkInclusion(String s1, String s2) {
        
		int []count = new int[26];
		
		if(s1==null||s2==null||s1.length()>s2.length())
			return false;
		
		for(int i=0; i<s1.length(); i++){
			count[s1.charAt(i)-'a'] += 1;
			count[s2.charAt(i)-'a'] -= 1;
		}

		//Check if first window contains all the chars
		if(allZero(count)) return true;
		
		for(int i=s1.length(); i<s2.length(); i++)
		{
			count[s2.charAt(i)-'a'] -= 1;
			count[s2.charAt(i-s1.length())-'a'] += 1;
			if(allZero(count)) return true;
		}
		
		return false;
    }
	
	private static boolean allZero(int[] count) {
		
		for (int i : count) {
			if(i!=0)
				return false;
		}
		return true;
	}
}
