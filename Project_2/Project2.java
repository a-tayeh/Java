/**
 *  Ali Tayeh                   CMSC-256-001                Project2
 *
 *  This class has a main method that handles our reading and parsing data from a file then passing it to the
 *  appropriate objects
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Project2 {
    public static void main(String[] args) {

        PersonnelManager obj = new PersonnelManager();
        String[] arr = new String[4];
        String[] arr2 = new String[4];
        String[] arr3 = new String[4];
        ArrayList<String> newEmployees = new ArrayList<String>();
        ArrayList<String> newWages = new ArrayList<String>();
        ArrayList<String> deletedEmployees = new ArrayList<String>();

        String fName = "";
        String lName = "";
        double hourly = 0.0;
        String rate = "";
        String strDouble = "";
        String symbol = "";
        String inCase = "";
        int lineCounter = 1;

/****************************************************************************************************************
 *
 *                              READING & PARSING THE EMPLOYEEIN.DAT
 *
 *  **************************************************************************************************************
 */
        try {
            Scanner in = new Scanner(System.in);
            String fileName = "EmployeesIn.dat";
            File newFile = new File(fileName);
            if(newFile.length()<1) {
                do {
                    System.out.print("The file doesn't exist,or contains no data , please enter another file name with extension: ");
                    fileName = in.nextLine();
                } while (newFile.length() < 1);
            }
            Scanner fromFile = new Scanner(newFile);
            while (fromFile.hasNextLine() && arr.length < 5) {
                arr = fromFile.nextLine().split("\\s+");
                if (arr.length == 4) {
                    arr[0] = arr[0].replace(",", "");

                    lName = arr[0];
                    fName = arr[1];
                    rate = arr[2];
                    hourly = Double.parseDouble(arr[3]);
                    if (rate.equalsIgnoreCase("h")) {
                        obj.addHourlyEmployee(new HourlyEmployee(fName, lName, hourly));
                    } else if (rate.equalsIgnoreCase("s")) {
                        hourly = hourly/2080;
                        obj.addSalariedEmployee(new SalariedEmployee(fName, lName, hourly));
                    }
                    arr = new String[4];
                }


            }
            fromFile.close();
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
/****************************************************************************************************************
 *
 *                      END OF READING & PARSING THE EMPLOYEEIN.DAT
 *
 *  **************************************************************************************************************
 */


/****************************************************************************************************************
 *
 *                      READING & PARSING THE UPDATES.DAT
 *
 *  **************************************************************************************************************
 */
        try{
            Scanner in = new Scanner(System.in);
            String fileName = "Updates.dat";
            File newFile = new File(fileName);
            if(newFile.length()<1) {
                do {
                    System.out.print("The file doesn't exist,or contains no data , please enter another file name with extension: ");
                    fileName = in.nextLine();
                } while (newFile.length() < 1);
            }
            Scanner fromFile2 = new Scanner(newFile);
            while(fromFile2.hasNextLine()) {
                inCase = fromFile2.nextLine();
                arr2 = inCase.split("\\s+");
                symbol = arr2[0].toLowerCase();
                switch (symbol) {
                    case "n":
                        if (arr2[3].equalsIgnoreCase("s")) {
                            lName = arr2[1].replace(",", "");
                            fName = arr2[2];
                            hourly = Double.parseDouble(arr2[4])/2080;
                            newEmployees.add(lName+", "+fName);
                            obj.addSalariedEmployee(new SalariedEmployee(fName, lName, hourly));
                        }
                        else if (arr2[3].equalsIgnoreCase("h")) {
                            lName = arr2[1].replace(",", "");
                            fName = arr2[2];
                            hourly = Double.parseDouble(arr2[4]);
                            newEmployees.add(lName+", "+fName);
                            obj.addHourlyEmployee(new HourlyEmployee(fName, lName, hourly));
                        }
                        break;
                    case "d":
//                        System.out.println("Deleted employee: " + arr2[1]);

                        deletedEmployees.add(obj.deleteEmployee(arr2[1]));
                        break;
                    case "r":
                        obj.raiseWage(Double.parseDouble(arr2[1]));
                        newWages.add(obj.toString());
                        break;
                }

                if(!(symbol.equalsIgnoreCase("n") || symbol.equalsIgnoreCase("r")
                        || symbol.equalsIgnoreCase("d"))){

                    System.out.println("Command not recognized on line "+lineCounter+": \t"+inCase);
                }
                lineCounter++;
            }

            fromFile2.close();

            if(newEmployees.size()>0){
                System.out.print("New Employee(s) Added:  ");
                for(String a : newEmployees){
                    System.out.printf("%s\n %23s", a.toString(), "");
                }
                System.out.println();
            }
            if(deletedEmployees.size()>0){
                System.out.print("Deleted Employee(s):    ");
                for(String a: deletedEmployees){
                    System.out.printf("%s%23s\n",a, "");
                }
            }
            System.out.println();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    /****************************************************************************************************************
     *
     *                     END OF READING & PARSING THE UPDATES.DAT
     *
     *  **************************************************************************************************************
     */


        /****************************************************************************************************************
         *
         *                     WRITING OUT THE EMPLOYEESOUT.DAT
         *
         *  **************************************************************************************************************
         */

        try {
            FileWriter writeOut = new FileWriter("EmployeesOut.dat");

            for (Employee e : obj.getArray()) {
                if (e != null) {
                    writeOut.write(e.toString() + "\n");
                    writeOut.flush();
                }
            }
            writeOut.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nWriting out to EmployeesOut.dat\n");
        obj.print();
/****************************************************************************************************************
 *
 *                                      END OF WRITING OUT THE EMPLOYEESOUT.DAT
 *
 *  **************************************************************************************************************
 */


/****************************************************************************************************************
 *
 *                                      READING & PARSING HOURSWORKED.DAT
 *
 *  **************************************************************************************************************
 */
        try {
            File na = new File("HoursWorked.dat");
            Scanner fromFile3 = new Scanner(na);
            while (fromFile3.hasNextLine()) {
                inCase = fromFile3.nextLine();
                arr3 = inCase.split("\\s+");
                lName = arr3[0];
                int hours = Integer.parseInt(arr3[1]);
                obj.weeklyPayroll(lName, hours);


            }


        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        try {
            FileWriter writeOut = new FileWriter("WeeklyPayroll.txt");
            writeOut.write("Paycheck amount:\n");
            writeOut.flush();
            for (Object e : obj.getPayroll()) {
                if (e != null) {
                    writeOut.write("     " + e + "\n");
                    writeOut.flush();
                }
            }
            writeOut.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\nWriting out to WeeklyPayroll.txt");
        System.out.println("Paycheck amount:");
//        String [] arr4 = new String[2];
//        int total = 0;
//        for (Object a : obj.getPayroll()) {
//            a.toString().split("//s+");
//            arr4[1]
//        }
        for (Object a : obj.getPayroll()) {
            System.out.println("     "+a);
        }


    }
    private void printHeading(){
        String name = "Ali Tayeh";
        String projectNum = "Project2";

        System.out.printf("%s");
    }
}

