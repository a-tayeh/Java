import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
public class DogNamesLab {
    public static void main(String [] args){
        int counter = 0;
        String[] dogData ;
        String firstLine = "";
        String dogName = "";
        int count = 0;
        ArrayList<Dog> dogsArray = new ArrayList<Dog>();
        try {
            Scanner in = new Scanner(new File("Dog_Names.csv"));
            firstLine = in.nextLine();
            while(in.hasNextLine() && !in.nextLine().equals("")){
                dogData = in.nextLine().split(",");
                dogName = dogData[0];
                count = Integer.parseInt(dogData[1]);
//                System.out.println(dogName+" "+count);
                dogsArray.add(new Dog(dogName,count));

            }
//            for(Dog a : dogsArray){
//                System.out.println(a.getDogName());
//            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
//        if(args.length>0) {
//            if(args[0].equals("1")) {
//
//            }
//        }
    }
}
