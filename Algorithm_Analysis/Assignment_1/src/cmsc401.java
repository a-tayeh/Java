import java.util.Scanner;
public class cmsc401 {

    public static void main(String[]args){

        Scanner in = new Scanner(System.in);
        int arrSize = in.nextInt();
        int []  arr = new int[arrSize];
        for(int i = 0;i<arrSize;i++){
            arr[i] = in.nextInt();
        }

        int counter = 0;
        int cME = 0;

        for(int i = 0; i<arr.length; i++){

            if(i==0) {
                cME = arr[i];
                counter++;
            }
            else{
                if(i<arr.length) {
                    if (cME == arr[i]) {
                        counter++;
                    } else {
                        counter--;
                    }
                }
            }
            if(counter==0) {
                if(arr.length%2==0){
                    cME = arr[i];
                }
                else{
                    cME = arr[i+1];
                }

            }
        }

        counter = 0;

        for(int num : arr){
            if(num==cME){
                counter++;
            }
        }
        if((arr.length/2) < counter) {
            System.out.println(cME);
        }else{
            System.out.println("-1");
        }

    }

}
