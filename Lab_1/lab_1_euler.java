/**
 * 
 */

/**
 * @author alitayeh
 *
 */
import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
public class lab_1_euler {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
////		mult_3_5(); //Answer: 233168
////		sum_sqr();  // Answer: 25164150
////		prm_fctr(); // Answer: 6857
////		fnd_par(); // Answer: 906609
//		fibonnaci();
		prm_sum();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void mult_3_5() {
      ArrayList<Integer> arr = new ArrayList<Integer>();


		int sum = 0;
		for(int i = 1; i<1000;i++) {
			if(i % 5 == 0 || i % 3 == 0) {
				arr.add(i);
				
			}
		}
		for (int n : arr) {
			sum+=n;
		}
		System.out.println(sum);
	}
	
	public static void sum_sqr() {
		
		ArrayList<Integer> arr = new ArrayList<Integer>();
		ArrayList<Integer> arr2 = new ArrayList<Integer>();
		for(int i = 1; i<=100;i++) {
			arr.add((int) Math.pow(i, 2));
		}
		int sqr_sum = 0;
		for(int n:arr) {
			sqr_sum+=n;
		}
		for(int i = 1; i<=100; i++) {
			arr2.add(i);
		}
		int sum_100 = 0;
		for(int m:arr2) {
			sum_100+=m;
		}
		sum_100 = sum_100 * sum_100;
		
		System.out.println(sum_100 - sqr_sum);
	}
	
	public static void prm_fctr() {
		long a = 600851475143L;
		ArrayList<Integer> arr = new ArrayList<Integer>();
		ArrayList<Integer> arr2 = new ArrayList<Integer>();
		int count = 2;
		
		while(a>1) {
			if(a % count == 0) {
				arr.add(count);
				a = (a/count);
			}
			else {
				count ++;
			}
		}
		for(int n : arr) {
			if(n % 2 != 0) {
				arr2.add(n);
			}
			else if(n == 2) {
				arr2.add(n);
			}
		}
		System.out.println(arr2.get(arr2.size()-1));
	}
	
	public static void fnd_par() {
		int fDigit = 100;
		int sDigit = 100;
		int results = 0;
		String results2 = "";
		ArrayList<Integer> arr = new ArrayList<Integer>();
		
		boolean con = true;
		
		while(con) {
			results = fDigit * sDigit;
			results2 = Integer.toString(results);
			if(results2.length() == 5) {
				if(results2.charAt(0) == results2.charAt(4) && results2.charAt(1) == results2.charAt(3)) {
					arr.add(results);
				}
			}
			else if(results2.length() == 6) {
				if(results2.charAt(0) == results2.charAt(5) && results2.charAt(1) == results2.charAt(4)
					&& results2.charAt(2) == results2.charAt(3)	){
					arr.add(results);
				}
			}
			sDigit++;
			if(sDigit == 1000) {
				sDigit = 100;
				fDigit++;
			}
			else if(fDigit == 1000) {
				break;
			}
		}
		Set<Integer> arr2 = new HashSet<>();
		arr2.addAll(arr);
		arr.clear();
		arr.addAll(arr2);
		Collections.sort(arr);
		System.out.println(arr);
//		System.out.println(arr.get(arr.size()-1));
	}
	
	public static void fibonnaci() {
		int fDigit = 0;
		int sDigit = 1;
		int results = 0;
		int evenFib = 0;
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(fDigit);
		arr.add(sDigit);
		boolean con = true;
		while(con) {
			results = fDigit + sDigit;
			arr.add(results);
			fDigit = sDigit;
			sDigit = results;
			if(results >4000000) {
				break;
			}
			
		}
		for(int n : arr) {
			if(n % 2 == 0) {
				evenFib += n;
			}
		}
		System.out.println(evenFib);
	}
	private static boolean isPrime( int num) {
		if(num < 2) {return false;}
	    if (num == 2) { return true;}
	    if (num % 2 == 0) { return false;}
	    for(int i = 2; i <= Math.sqrt(num); i++){

	        if(num % i == 0) {return false;}          
	    }
	    return true;
	}

	public static void prm_sum() {
		ArrayList<Integer> arr1 = new ArrayList<Integer>();
		int prmSum = 0;

		for(int i = 2;i<=2000000;i++) {
			if(isPrime(i)) {
				prmSum+=i;
			}
		}

		
		System.out.println(prmSum);
	}
	
// 123321
}
