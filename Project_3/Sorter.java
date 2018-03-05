/**
 * Project 3 - Sorter           Ali Tayeh           CMSC-256-001
 *
 * This programs has a class method that takes in an array of integer, creates an array that records counter of values
 * in that array then sorts the array in ascending order using the counter values.
 */
public class Sorter{

    public static int[] sortIntArray(int[] arr){

        if(arr==null)
            {throw new IllegalArgumentException("Cannot sort an array that is null!");}

        if(arr.length==0)
            {return arr;}

        for(int i = 0;i<arr.length;i++){
            if(arr[i]<0){ throw new IllegalArgumentException("Unable to sort negative values in the array"); }
        }
        // where our largest num in the array will be assigned
        int largest = 0;
        /**
         *  gets the largest integer in the array so we can build our counters array based on the size
         *  of the largest num in that array
         */
        for(int i = 0;i<arr.length;i++){
            if(largest<arr[i]) { largest = arr[i]; }
        }
        /**
         * creating the counters array and setting its size to be largest+1
         */
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
