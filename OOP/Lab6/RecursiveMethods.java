/*
*	Programmer #1:
*	Programmer #2:
*/

public class RecursiveMethods {
	/* 
	 * @returns a String of character, ch. The length is determined
	 * 			by the second parameter, length.
	 */
	public static String buildStringOfCharacters(char ch, int length) {
		if(length==0){
			return ;
		}
		return ch + buildStringOfCharacters(ch,length-1);
	}
	
	/*
	 * returns an int array that has the elements in reverse order
	 * 			of the parameter array, nums. 
	 * Process this recursively beginning with the last element.
	 */
	public static int[] reverseNumArray(int[] nums, int backIndex) {
		
		
		return nums[num];
	}
	
	/*
	 * returns true if the int array parameter is sorted from smallest
	 * 			to largest, false otherwise.
	 * Process this recursively beginning with the first element.
	 */
	public static boolean isSmallestToLargest(int[] values, int firstIndex) {
		return false;
	}
	
	/* 
	 * @returns true if the parameter String, str is a palindrome
	 * 			false otherwise
	 */
	public static boolean isPalindrome(String str, int begin, int end) {
		return false;
	}
}
