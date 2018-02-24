import java.util.Arrays;
import java.util.ArrayList;
public class PersonnelManager {
    private int numOfEntries = 0;
    private int capacity = 1;

    Employee [] data = new Employee[capacity];

    public boolean addHourlyEmployee(HourlyEmployee obj){
        if(obj==null || !(obj instanceof HourlyEmployee)){
            throw new IllegalArgumentException("Only objects of HourlyEmployee Type are permitted!");
        }
        if(isFull()){
            doubleArray();
            data[numOfEntries] = obj;
            numOfEntries++;
            return true;
        }
        else if(obj!=null) {
            data[numOfEntries] = obj;
            numOfEntries++;
            return true;
        }
        return false;
    }

    public boolean addSalariedEmployee(SalariedEmployee obj){
        if(obj==null || !(obj instanceof SalariedEmployee)){
            throw new IllegalArgumentException("Only objects of SalariedEmployee Type are permitted!");
        }

        if(isFull()){
            doubleArray();
            data[numOfEntries] = obj;
            numOfEntries++;
            return true;
        }
        else if(obj!=null){
            data[numOfEntries] = obj;
            numOfEntries++;
            return true;
        }
     return false;
    }

    public int getNumOfEntries() {
        return numOfEntries;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isFull(){
     return numOfEntries >= capacity;
    }

    private void doubleArray(){
        capacity = capacity*2;
        Employee [] newArr = new Employee[capacity];
        for(int i = 0;i<data.length;i++){
            newArr[i] = data[i];
        }
        data = newArr;
    }

    public Employee[] getArray(){
        return data;
    }

    private int getIndex(Employee t){
        for(int i = 0;i<=numOfEntries;i++){
            if(data[i].equals(t)){
                return i;
            }
        }
        return -1;
    }
    public void deleteEmployee(String name){
        boolean delete = false;
        for(int i = 0;i < data.length && !delete;i++){

            if(data[i].getLastName().equalsIgnoreCase(name)){
                    data[i] = data[data.length-1];
                    data[data.length-1] = null;
                    numOfEntries = numOfEntries-1;
                    delete = true;

            }
        }
    }
    public void print(){
        for(int i =0;i<data.length;i++){
            if(data[i]!=null) {
                System.out.println((data[i].toString()));
            }
        }

    }
    public void raiseWage(double raise){
        for(Employee a: data){

            a.raiseWages(raise);

        }

    }

    public void weeklyPayroll(String lName, int hours){
        for(int i = 0;i<numOfEntries;i++){
            if(data[i].getLastName().equalsIgnoreCase(lName)){
                String name = data[i].getName();
                double amount = data[i].computePay(hours);
                String amountStr = String.format("$%.2f", amount);
                int add = (int)(data[i].getName().length() + amountStr.length());
                int spaceCount = 40 - add;
                String count = Integer.toString(spaceCount);
                String nana = String.format("%s%"+count+"s%s",name,"",amountStr);
//                System.out.println(data[i].getName().length()+" "+ amountStr.length());
                System.out.println(nana);
            }
        }
//        for(Employee a : data){
//            int count = a.getName().length();
//            System.out.println(a.getName() + );
//        }
    }


    @Override
    public String toString() {

        return "";

    }

}
