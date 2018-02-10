import java.util.Scanner;
import java.util.Arrays;
public class Tayeh_Ali_Problem01{
	
	public static void main(String[] args){
	
		
		Scanner in = new Scanner(System.in);
		int listNum = 0;
		listNum = in.nextInt();
		double[] priceList = new double[listNum];
		double num = 0.0;
		int counter = 0;

		// this will add the user input into the array and will stop when the number of inputs reaches the size of the array
		while(counter<priceList.length){
			num = in.nextDouble();
			priceList[counter] = num;
			counter++;
			
		}
		// Sorts the array small-large nums, then store the substraction results of largest num - smallest num 
		// in result variable
		Arrays.sort(priceList);
		double result = priceList[priceList.length-1] - priceList[0];
		result = Math.round(result*100.00)/100.00;;
		// outputs the results
		System.out.println(result);

	}

}