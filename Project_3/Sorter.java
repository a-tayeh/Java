import java.util.ArrayList;

public class Sorter{
    public static void main(String [] args) {

        int[] nums = {4, 4, 3, 1, 2, 3,0,17,17,17};
        int [] sorted = new int [nums.length];
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
        ArrayList<Integer> met = new ArrayList<Integer>();
        int index = 0;
        int index2 = 0;
        boolean sort = false;


            for(int i = 0;i<counters.length;i++){
            if(counters[i]!=0) {

                System.out.println("There are: " + counters[i] + " ---> " + index);
                met.add(counters[i]);

                index++;


            }
            else{

                index++;

            }
        }
        int [] newCounters = new int[met.size()];
            for(int i = 0;i<newCounters.length;i++){
                newCounters[i] = met.get(i);
            }
          int index8 = 0;
          for(int i = 0;i<18;i++) {
              if (counters[i] != 0){
                  int index3 = 0;
                  while (index3 < counters[i]) {
                      if (index3 > 0) {
                          nums[index8] = i;
                          index3++;
                      } else {
                          nums[index8] = i;
                          index3++;
                      }
                      index8++;
                  }
          }
          }

        for(int a : nums){
            System.out.print(a+" ");
        }






    }

//    public int[] sortIntArray(int[] arr){
//
//        return arr;
//    }
}
