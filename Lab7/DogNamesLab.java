import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;
public class DogNamesLab {
    private static ArrayList<Dog>  dogsArray = new ArrayList<Dog>();
    private static Dog dog = new Dog();
    public static void main(String [] args){
        Scanner input = new Scanner(System.in);
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
            //gets rid of first useless row
            firstLine = in.nextLine();
            while(in.hasNextLine() && !(in.nextLine().equals(""))){
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
            else if(args[0].equals("3")) {
                boolean playAgain = false;

                while (!playAgain) {

                    int choice = 0;
                    int randDog1 = (int) Math.ceil(Math.random() * dogsArray.size());
                    int randDog2 = (int) Math.ceil(Math.random() * dogsArray.size());
                    int randDog1Count = dogsArray.get(randDog1).getCount();
                    int randDog2Count = dogsArray.get(randDog2).getCount();
                    String randDog1Name = dogsArray.get(randDog1).getDogName();
                    String randDog2Name = dogsArray.get(randDog2).getDogName();
                    System.out.println("Which name is more popular for Anchorage dogs?(Type 1 or 2)");
                    System.out.printf("1. %s %7s2. %s\n", randDog1Name, "", randDog2Name);
                    choice = input.nextInt();

                    if (randDog1Count > randDog2Count && choice == 1) {
                        System.out.printf("Correcto mundo, %s has a count of %d while %s has a " +
                                "count of %d", randDog1Name, randDog1Count, randDog2Name, randDog2Count);
                    }
                    if (randDog1Count < randDog2Count && choice == 1) {
                        System.out.printf("Nope, %s has a count of %d while %s has a " +
                                "count of %d", randDog1Name, randDog1Count, randDog2Name, randDog2Count);
                    }
                    if (randDog1Count < randDog2Count && choice == 2) {
                        System.out.printf("Correcto mundo, %s has a count of %d while %s has a " +
                                "count of %d", randDog1Name, randDog1Count, randDog2Name, randDog2Count);
                    }
                    if (randDog1Count > randDog2Count && choice == 2) {
                        System.out.printf("Nope, %s has a count of %d while %s has a " +
                                "count of %d", randDog1Name, randDog1Count, randDog2Name, randDog2Count);
                    }
                    System.out.println("Wanna Play Again? Y/N");
                    String reset = input.nextLine();

                    if (reset.equalsIgnoreCase("Y")) {
                        playAgain = false;
                    } else if (reset.equalsIgnoreCase("N")) {
                        playAgain = true;
                    }
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
