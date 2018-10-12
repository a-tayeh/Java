import java.util.Scanner;
public class Tayeh_Ali_Problem03 {
    public static void main(String [] args){
        Scanner in = new Scanner(System.in);
        String set = in.nextLine();
        set = set.replaceAll("\\{+|\\}+|,+", "");
        int length = in.nextInt();
        int numerator = sizeFactorial(set.length());
        int denominator = numOfSubsets(length) * getSubsets(set.length()-length);
        System.out.println(numerator/denominator);


    }


    public static int sizeFactorial(int size){
        if(size==0){ return 1; }
        return size * sizeFactorial(size-1);
    }
    public static int numOfSubsets(int n){
        if(n == 0){ return 1; }
        return n*numOfSubsets(n-1);
    }
    public static int getSubsets(int difference){
        if(difference==0){return 1;}
        return (difference)*getSubsets(difference-1);
    }

}
