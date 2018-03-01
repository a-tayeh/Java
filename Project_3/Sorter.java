import java.util.ArrayList;

public class Sorter{
    public static void main(String [] args) {

        int[] nums = {4, 4, 3, 1, 2, 3,0,17,17,17};
        int largest = 0;
        for(int i = 0;i<nums.length;i++){
            if(largest<nums[i]){
                largest = nums[i];
            }
        }
        int [] counters = new int[largest+1];

        for (int i = 0; i < nums.length; i++){
            counters[nums[i]]++;
        }
        int index = 0;
        for(int i = 0;i<largest+1;i++){
            if(counters[i]!=0) {
                System.out.println("There are: " + counters[i] + " ---> " + index);
                index++;
            }
            else{
                index++;
            }
        }

//        int [] sorted = new int[nums.length];
//        int index2 = 0;
//        while(index2<nums.length){
//            for(int i = 0;i<counters.length;i++){
//                sorted[i] = i;
//            }
//            index2++;
//        }
//
//        for(int a : sorted){
////            if(a!=0) {
//                System.out.print(a + " ");
////            }
//        }


    }

//    public int[] sortIntArray(int[] arr){
//
//        return arr;
//    }
}
