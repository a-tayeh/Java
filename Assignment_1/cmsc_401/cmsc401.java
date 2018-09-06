/*
*   Ali Tayeh           CMSC-401        Fall-2018
*
*                   Trial Assignment
*
* */

import java.util.ArrayList;
import java.util.Scanner;
public class cmsc401 {
    public static void main(String [] args){
        Scanner in = new Scanner(System.in);
        Scanner in2 = new Scanner(System.in);
        ArrayList<Integer> answers = new ArrayList<>();
        int [] nums;
        int numOfLines = 0;
        String  inputString = "";
        String [] inputToArray;
        int numOfDigits = 0;
        int counter = 0;
        int position = 0;
        int arrPos = 0;
        numOfLines = in.nextInt();
        while(counter<numOfLines){
            inputString = in2.nextLine();
            if(inputString.length()<1){
                break;
            }
            inputToArray = inputString.split(" ");
            position = 0;
            numOfDigits = Integer.parseInt(inputToArray[position]);
            nums = new int[numOfDigits+1];

            while(numOfDigits+1>0){

                nums[position] = Integer.parseInt(inputToArray[position]);
                numOfDigits--;
                position++;
            }
            counter++;
            answers.add(nums[nums[nums.length-1]] * nums[nums[nums.length-2]]);
            arrPos++;
        }
        for(int answer : answers){
            System.out.println(answer);
        }

    }
}
