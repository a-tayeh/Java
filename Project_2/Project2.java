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
import java.util.Scanner;
public class Project2 {
    public static void main(String[] args) {

        PersonnelManager obj = new PersonnelManager();
        String[] arr = new String[4];
        String[] arr2 = new String[4];
        String[] arr3 = new String[4];
        ArrayList<String> newEmployees = new ArrayList<String>();
        ArrayList<String> newWages = new ArrayList<String>();
        String fName = "";
        String lName = "";
        double hourly = 0.0;
        String rate = "";
        String strDouble = "";
        String symbol = "";
        String inCase = "";

/****************************************************************************************************************
 *
 *                              READING & PARSING THE EMPLOYEEIN.DAT
 *
 *  **************************************************************************************************************
 */
        try {

            File n = new File("EmployeesIn.dat");
            Scanner fromFile = new Scanner(n);
            if (n.length() == 0) {
                throw new IOException("file is empty?");
            }
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
                            newEmployees.add(" ("+lName+", "+fName+") ");
                            obj.addSalariedEmployee(new SalariedEmployee(fName, lName, hourly));
                        }
                        else if (arr2[3].equalsIgnoreCase("h")) {
                            lName = arr2[1].replace(",", "");
                            fName = arr2[2];
                            hourly = Double.parseDouble(arr2[4]);
                            newEmployees.add(" ("+lName+", "+fName+") ");
                            obj.addHourlyEmployee(new HourlyEmployee(fName, lName, hourly));
                        }
                        break;
                    case "d":
                        System.out.println("Deleted employee: " + arr2[1]);
                        obj.deleteEmployee(arr2[1]);
                        break;
                    case "r":
//                        System.out.println("Wages have been raised by: " + arr2[1] + "%");
                        obj.raiseWage(Double.parseDouble(arr2[1]));

                        break;
                }

                if(!(symbol.equalsIgnoreCase("n") || symbol.equalsIgnoreCase("r")
                        || symbol.equalsIgnoreCase("d"))){

                    System.out.println("<Command not recognized on line:>\t"+inCase);
                }
            }

            fromFile2.close();
            if(newEmployees.size()>0){
                System.out.print("New Employee(s) Added: ");
                for(String a : newEmployees){
                    System.out.printf(a);
                }
                System.out.println();
            }

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
            while(fromFile3.hasNextLine()){
                inCase = fromFile3.nextLine();
                arr3 = inCase.split("\\s+");
                lName = arr3[0];
                hourly = Double.parseDouble(arr3[1]);

            }

        }catch (Exception err){
            System.out.println(err.getMessage());
        }






    }

    public static void employeesIn() {
        PersonnelManager obj = new PersonnelManager();
        String[] arr = new String[4];
        String fName = "";
        String lName = "";
        double hourly = 0.0;
        String rate = "";
        String strDouble = "";
        String symbol = "";
        try {

            File n = new File("EmployeesIn.dat");
            Scanner fromFile = new Scanner(n);
            if (n.length() == 0) {
                throw new IOException("file is empty?");
            }
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
                        obj.addSalariedEmployee(new SalariedEmployee(fName, lName, hourly));
                    }
                    arr = new String[4];
                }


            }
            fromFile.close();
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }



    }
    public static void updates(){
        PersonnelManager obj2 = new PersonnelManager();
        String[] arr2 = new String[4];
        String fName = "";
        String lName = "";
        double hourly = 0.0;
        String rate = "";
        String strDouble = "";
        String symbol = "";
        String inCase = "";
        try{
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
                        obj2.addSalariedEmployee(new SalariedEmployee(fName, lName, hourly));
                    }
                    else if (arr2[3].equalsIgnoreCase("h")) {
                        lName = arr2[1].replace(",", "");
                        fName = arr2[2];
                        hourly = Double.parseDouble(arr2[4]);
                        System.out.println("New Employee: " + fName + " " + lName);
                        obj2.addHourlyEmployee(new HourlyEmployee(fName, lName, hourly));
                    }
                    break;
                case "d":
                    System.out.println("Deleted employee: " + arr2[1]);
                    obj2.deleteEmployee(arr2[1]);
                    break;
                case "r":
                    System.out.println("Wages have been raised by: " + arr2[1] + "%");
                    obj2.raiseWage(Double.parseDouble(arr2[1]));
                    break;
            }

            if(!(symbol.equalsIgnoreCase("n") || symbol.equalsIgnoreCase("r")
                    || symbol.equalsIgnoreCase("d"))){

                System.out.println("<Command not recognized on line:>\t"+inCase);
            }
        }

        fromFile2.close();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void employeesOut(){
        PersonnelManager obj3 = new PersonnelManager();
        try {
            FileWriter writeOut = new FileWriter("EmployeesOut.dat");

            for (Employee e : obj3.getArray()) {
                if (e != null) {
                    writeOut.write(e.toString() + "\n");
                    writeOut.flush();
                }
            }
            writeOut.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }




    }
}
