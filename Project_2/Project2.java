import java.io.File;
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


        try {
            PersonnelManager obj = new PersonnelManager();

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
//                        strDouble = String.format("%.2f", (hourly/2080));
//                        hourly = Double.parseDouble(strDouble);
                        obj.addSalariedEmployee(new SalariedEmployee(fName,lName,hourly));
                    }
                    arr = new String[4];
                }


            }
            fromFile.close();
            Scanner fromFile2 = new Scanner(new File("Updates.dat"));
            while (fromFile2.hasNextLine()){
                arr2 = fromFile2.nextLine().split("\\s+");
                symbol = arr2[0];
                if(symbol.equalsIgnoreCase("d")){
                    obj.deleteEmployee(arr2[1]);
                }
                else if(symbol.equalsIgnoreCase("r")){
                    obj.raiseWage(Double.parseDouble(arr2[1]));
                }


            }

            fromFile2.close();

            obj.print();
            //System.out.println(obj.getNumOfEntries());

        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
}
