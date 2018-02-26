import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
public class DogNamesLab {
    private static ArrayList<Dog>  dogsArray = new ArrayList<Dog>();
    private static Dog dog = new Dog();
    public static void main(String [] args){
//        ArrayList<String> me = new ArrayList<String>();
//        me.add("ali");
//        me.add("cally");
//        me.add("ephy");
//        me.add("dali");
//        me.add("aaa");
//        for(String a : stringOrder(me)){
//            System.out.println(a);
//        }
        int counter = 0;
        String[] dogData ;
        String firstLine = "";
        String dogName = "";
        int count = 0;
        try {
            Scanner in = new Scanner(new File("Dog_Names.csv"));

            firstLine = in.nextLine();
            while(in.hasNextLine() && !in.nextLine().equals("")){
                dogData = in.nextLine().split(",");
                dogName = dogData[0];
                count = Integer.parseInt(dogData[1]);
                dogsArray.add(new Dog(dogName,count));

            }

//            System.out.println(searchDogName("Zoey"));


//            for(Dog a : dogsArray){
//                System.out.println(a.getDogName());
//            }
//            for(Dog a : alphabeticalDogs(dogsArray)){
//                System.out.println(a.getDogName());
//            }
//            System.out.println(tryme("Tli", "Tli"));


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if(args.length>0) {
            Scanner input = new Scanner(System.in);
            if(args[0].equals("1")) {
                System.out.print("Enter your dog's name:");
                dogName = input.nextLine();
                if(searchDogName(dogName)>0){
                    System.out.println(dogName+" has been mentioned: "+searchDogName(dogName));
                }
                else{
                    System.out.println(dogName+", is a very unique name(none found in database), you're the first to have it!");
                }
            }
            else if(args[0].equals("2")){
                for(Dog a : alphabeticalDogs(dogsArray)){
                    System.out.println(a.getDogName());
                }
            }
        }
    }

    public static int searchDogName(String dogName){
        int counter = 0;
        for(Dog target: dogsArray){
            if(target.getDogName().equalsIgnoreCase(dogName)){
                counter = target.getCount();
            }
        }
        return counter;
    }

    public  static ArrayList<Dog> alphabeticalDogs(ArrayList<Dog> dogs){
        int counter = 0;
        int index = 0;
        Dog temp;
        while(counter<dogs.size()-1){
            if(dog.compare(dogs.get(index),dogs.get(index+1))>0){
                temp = dogs.get(index);
                dogs.set(index,dogs.get(index+1));
                dogs.set(index+1,temp);
                counter = 0;
                index = 0;
            }

            else if(dog.compare(dogs.get(index),dogs.get(index+1))<0){
                counter++;
                index++;
                continue;
            }
        }
        return dogs;
    }




}
