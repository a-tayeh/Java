/**
 *  Ali Tayeh                   CMSC-256-001                Project2
 *
 *  This class has a main method that handles our reading and parsing data from a file then passing it to the
 *  appropriate objects
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Project2 {
    public static void main(String[] args){
        String [] arr = new String[4];
        String [] arr2 = new String[4];
        String fName = "";
        String lName = "";
        double hourly = 0.0;
        String rate = "";
        String strDouble = "";
        String symbol = "";
        boolean done = false;
        String inCase = "";
        PersonnelManager obj = new PersonnelManager();
        try {

            File n = new File("EmployeesIn.dat");
            Scanner fromFile = new Scanner(n);
            if(n.length()==0){
                throw new IOException("file is empty?");
            }
            while(fromFile.hasNextLine() && arr.length<5){
                arr = fromFile.nextLine().split("\\s+");
                if(arr.length == 4){
                    arr[0] = arr[0].replace(",", "");

                    lName = arr[0];
                    fName = arr[1];
                    rate = arr[2];
                    hourly = Double.parseDouble(arr[3]);
                    if(rate.equalsIgnoreCase("h")){
                        obj.addHourlyEmployee(new HourlyEmployee(fName,lName,hourly));
                    }
                    else if(rate.equalsIgnoreCase("s")){
                        obj.addSalariedEmployee(new SalariedEmployee(fName,lName,hourly));
                    }
                    arr = new String[4];
                }


            }
            fromFile.close();
            Scanner fromFile2 = new Scanner(new File("Updates.dat"));
            while(fromFile2.hasNextLine()) {
                inCase = fromFile2.nextLine();
                arr2 = inCase.split("\\s+");
                symbol = arr2[0].toLowerCase();
                switch (symbol) {
                    case "n":
                        if (arr2[3].equalsIgnoreCase("s")) {
                            lName = arr2[1].replace(",", "");
                            fName = arr2[2];
                            hourly = Double.parseDouble(arr2[4]);
                            System.out.println("New Employee: " + fName + ", " + lName);
                            obj.addSalariedEmployee(new SalariedEmployee(fName, lName, hourly));
                        }
                        else if (arr2[3].equalsIgnoreCase("h")) {
                            lName = arr2[1].replace(",", "");
                            fName = arr2[2];
                            hourly = Double.parseDouble(arr2[4]);
                            System.out.println("New Employee: " + fName + " " + lName);
                            obj.addHourlyEmployee(new HourlyEmployee(fName, lName, hourly));
                        }
                        break;
                    case "d":
                        System.out.println("Deleted employee: " + arr2[1]);
                        obj.deleteEmployee(arr2[1]);
                        break;
                    case "r":
                        System.out.println("Wages have been raised by: " + arr2[1] + "%");
                        obj.raiseWage(Double.parseDouble(arr2[1]));
                        break;
                }

                if(!(symbol.equalsIgnoreCase("n") || symbol.equalsIgnoreCase("r")
                        || symbol.equalsIgnoreCase("d"))){

                                System.out.println("<Command not recognized on line:>\t"+inCase);
                }
            }

            fromFile2.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            FileWriter writeOut = new FileWriter("EmployeesOut.dat");

            for(Employee e: obj.getArray()){
                if(e!=null) {
                    writeOut.write(e.toString() + "\n");
                    writeOut.flush();
                }
            }
            writeOut.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        obj.print();
        System.out.println(obj.getNumOfEntries());


    }
}
