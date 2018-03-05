/**
 * Project 3 - Sorter           Ali Tayeh           CMSC-256-001
 *
 * This programs has a class method that takes in an array of integer, creates an array that records counter of values
 * in that array then sorts the array in ascending order using the counter values.
 */
public class Sorter{

    public static int[] sortIntArray(int[] arr){
        for(int i = 0;i<arr.length;i++){
            if(arr[i]<0){ throw new IllegalArgumentException("The array cannot contain negative integers"); }
        }
        int largest = 0;

        for(int i = 0;i<arr.length;i++){
            if(largest<arr[i]) { largest = arr[i]; }
        }

        int [] counters = new int[largest+1];

        for (int i = 0; i < arr.length; i++) {
            counters[arr[i]]++ ;
        }

        int index = 0;
        for(int i = 0;i<counters.length;i++) {
            if (counters[i] != 0){
                int iterationTimes = 0;
                while (iterationTimes < counters[i]) {
                    if (iterationTimes > 0) {
                        arr[index] = i;
                        iterationTimes++;
                    } else {
                        arr[index] = i;
                        iterationTimes++;
                    }
                    index++;
                }
            }
        }
        return arr;
    }
}
