import java.io.File;
import java.util.Scanner;
public class Project2 {
    public static void main(String[] args){
        String [] arr = new String[4];
        String fName = "";
        String lName = "";
        double hourly = 0.0;
        String rate = "";

        try {
            PersonnelManager obj = new PersonnelManager();

            Scanner fromFile = new Scanner(new File("EmployeesIn.dat"));
            while(fromFile.hasNextLine() && arr.length<5){
                arr = fromFile.nextLine().split("\\s+");
                if(arr.length == 4){
                    arr[0] = arr[0].replace(",", "");

                    fName = arr[0];
                    lName = arr[1];
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
            obj.print();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
