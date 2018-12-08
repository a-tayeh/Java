/*          Ali Tayeh               CMSC-401                Assignment 4
*
* */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("unchecked")
public class cmsc401{
    private static int [][] r;
    private static ArrayList<Integer> costArr = new ArrayList<>();
    static ArrayList<List<Integer>> arrayOfIntList = new ArrayList<List<Integer>>();
    static List completeRod = new ArrayList();

    private static int cost = 0;
    static boolean end = false;
    static int inputSize = 0;

    public static void findMinRodCutCost(List intArray, int start, ArrayList<List<Integer>> in) {

        for(int i = start; i <intArray.size(); i++){
            int temp = (Integer) intArray.get(start);
            intArray.set(start,intArray.get(i));
            intArray.set(i,temp);
            findMinRodCutCost(intArray, start + 1,in);
            intArray.set(i,intArray.get(start));
            intArray.set(start,temp);

        }
        if (start == intArray.size() - 1) {
            calculateMinCut(intArray,in);
        }

    }

    public static int calculateMinCut(List cutCosts, ArrayList<List<Integer>> rodParts){

            int temp = 0;
            if(cutCosts.size()<1 ){
                return 0;
            }
            for( int i = 0;i<inputSize && cutCosts.size()>0;i++) {
                int currentListIndex = 0;


                if(end){
                    end = false;
                    costArr.add(cost);
                    cost = 0;
                    rodParts.removeAll(rodParts);
                    rodParts.add(completeRod);
                    return 0;
                }

                if(cutCosts.size()>1) {

                    temp = (int)cutCosts.get(i);
                    cutCosts = cutCosts.subList(i+1,cutCosts.size());
               }
               else if(cutCosts.size()==1){
                    temp = (int)(cutCosts.get(i));
                    end = true;


                }
                    for (List l : arrayOfIntList) {
                        if (l.contains(temp)) {
                            currentListIndex = arrayOfIntList.indexOf(l);
                            break;
                        }
                    }

                if(rodParts.get(currentListIndex).contains(temp)){
                            List<Integer> left;
                            List<Integer> right;
                            int targetIndex = 0;
                            int cutLocation = temp;

                            for(List l : arrayOfIntList){
                                if(l.contains(cutLocation)) {
                                    if(l.size()>1){
                                        cost+=l.size();
                                    }
                                    targetIndex = arrayOfIntList.indexOf(l);
                                    break;
                                }
                            }
                            int leftLimit = arrayOfIntList.get(targetIndex).indexOf(cutLocation);
                            left = arrayOfIntList.get(targetIndex).subList(0,leftLimit+1);
                            right = arrayOfIntList.get(targetIndex).subList(left.size(),arrayOfIntList.get(targetIndex).size());
                            arrayOfIntList.remove(targetIndex);
                            arrayOfIntList.add(left);
                            arrayOfIntList.add(right);

                }

                return calculateMinCut(cutCosts,rodParts);

            }

        return 0;
    }


        public static void main(String args[]) {
            Scanner userInput = new Scanner(System.in);
            int rodSize = userInput.nextInt();
            for(int i = 1;i<=rodSize;i++){
                completeRod.add(i);
            }
            List<Integer> input = new ArrayList();

            int numCuts = userInput.nextInt();
            for(int i = 0;i<numCuts;i++) {
                input.add(userInput.nextInt());
            }

            arrayOfIntList.add(completeRod);
            inputSize = input.size();
            findMinRodCutCost(input,0,arrayOfIntList);
            System.out.println(Collections.min(costArr));

        }



}
