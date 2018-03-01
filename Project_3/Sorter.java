import java.util.ArrayList;

public class Sorter{
    public static void main(String [] args) {
//        ArrayList<int[]> coll = new ArrayList<int[]>();
        int[] nums = {4, 4, 3, 1, 2, 3,0};
        int [] counters = new int[nums.length];

        for (int i = 0; i < nums.length; i++){
            counters[nums[i]]++;
        }
        int index = 0;
//        System.out.println("There are:");
        for(int i = 0;i<counters.length;i++){
            if(counters[i]!=0) {
                System.out.println("There are: " + counters[i] + " --> " + index);
                index++;
            }
        }

        int [] sorted = new int[nums.length];


    }

//    public int[] sortIntArray(int[] arr){
//
//        return arr;
//    }
}
