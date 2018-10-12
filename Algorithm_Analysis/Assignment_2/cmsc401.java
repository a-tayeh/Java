/*
*                           Ali Tayeh           CMSC_401                Assignment2
* */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class cmsc401 {


    public static void main(String[]args){

        Scanner in = new Scanner(System.in);
        int counter = in.nextInt();

        int [] arr =  new int[counter];

        for(int i = 0;i<counter;i++){
            arr[i] = in.nextInt();
        }
        in.close();

        int median = 0;
        
        // if our array is of an even size then our median is n/2, if our array is of odd size, then we pick (n/2)+1
        if(arr.length % 2 ==0){
             median = arr.length/2;
        }
        else{
             median = (arr.length/2)+1;
        }
        
        int optimalPath = findMedian(arr,0,(arr.length),median);
        System.out.println(optimalPath);
    }

    public static int findMedian(int [] arr,int start, int end, int medianNum){
        Random rand = new Random();
        ArrayList<Integer> lowerThanPivot = new ArrayList<>();
        ArrayList<Integer> higherThanPivot = new ArrayList<>();

        //base case
        if(arr.length==1){
            return arr[0];
        }

        /*   these lines of code allow us to choose a random pivot and split the array into two parts,
         *   higher than pivot and lower than pivot
         */
        int pivot = rand.nextInt(arr.length);
        for(int i = 0;i<arr.length;i++){
            if(arr[i]<=arr[pivot]){
                lowerThanPivot.add(arr[i]);
            }
            else if(arr[i]>arr[pivot]){
                higherThanPivot.add(arr[i]);
            }

        }
        /*
        *   If our left partition has n(median) or more elements then recurse on left, otherwise, recurse on right
        *   partition with our median being subtracted from the length of the left partition
        * */
        if(medianNum<=lowerThanPivot.size()){

            int [] lowPartition = new int[lowerThanPivot.size()];
            for(int j = 0 ;j<lowerThanPivot.size();j++){
                lowPartition[j]=lowerThanPivot.get(j);
            }
            medianNum=findMedian(lowPartition,0,lowPartition.length,medianNum);
            return medianNum;

        }

        else if(medianNum > lowerThanPivot.size()){

            int [] highPartition = new int[higherThanPivot.size()];
            for(int l = 0 ;l<higherThanPivot.size();l++){
                highPartition[l]=higherThanPivot.get(l);
            }
            medianNum = findMedian(highPartition,0,highPartition.length,medianNum - (lowerThanPivot.size()));
            return medianNum;

        }
        return medianNum;

    }
}
    

