import java.io.*;
import java.util.*;
import java.util.Scanner;


/**
 * Primes is a program that will compute prime numbers using the sieve of Eratosthenes.
 */
public class Primes{

    public static void main(String args[]) {

        int max;
        Scanner in = new Scanner(System.in);
        ListWithIteratorInterface<Integer> candidates = new ArrayListWithIterator<>();
        int count = 1;
        System.out.println("Write shit");
        while (count <= 7) {
            int input = in.nextInt();
            candidates.add(input);
            count++;
        }

        Iterator<Integer> itrForCandidates = candidates.iterator();
        getComposites(candidates, itrForCandidates);


    }


    public static void getComposites(ListInterface<Integer> candidates,  Iterator<Integer> itrForCandidates){
        ArrayListWithIterator<Integer> primes, composites;
        primes = new ArrayListWithIterator<>();
        composites = new ArrayListWithIterator<>();
        boolean stop = true;
        int prime = 0;
        while(stop) {
            if(itrForCandidates.hasNext()) {
                prime = itrForCandidates.next();
                if(prime % 2 != 0 || prime == 2) {
                    primes.add(prime);
                }
                System.out.println("This is the candidates string before removing prime: " + candidates.toString());
                itrForCandidates.remove();
                System.out.println("The current prime is " + prime);
                System.out.println("Candidates after removing prime"+ candidates.toString());
                System.out.println("---------------------------------");

                int index = 1;
                for (int i = 1; i <= candidates.getLength(); i++) {
                    if (candidates.getEntry(i) % prime == 0) {
                        int r = candidates.remove(index);
                        composites.add(r);
                    }

                    index++;
                }


            }
            else{

                stop = false;
            }
        }

        System.out.println("This is the composite list " + composites.toString());

        System.out.println("Primes are : "+primes.toString());

    }
    
    
    /**
     * getComposites - Remove the composite values from possibles list and
     * put them in the composites list.
     *
     * @param  candidates   A list of integers holding the possible values.
     * @param  composites   A list of integers holding the composite values.
     * @param  prime   An Integer that is prime.
     */

    
    
    /**
     * Get an integer value.
     *
     * @return     An integer. 
     */
    private static int getInt(String rangePrompt)  {
        Scanner input;
        int result = 10;        //Default value is 10
        try   {
            input = new Scanner(System.in);
            System.out.println(rangePrompt);
            result = input.nextInt();    
        }
        catch(NumberFormatException e) {
            System.out.println("Could not convert input to an integer");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        }        
        catch(Exception e)  {
            System.out.println("There was an error with System.in");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        }
        return result;                                   
    }    
    
}
