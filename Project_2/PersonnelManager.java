import java.util.Arrays;
public class PersonnelManager {
    private int numOfEntries = 0;
    private int capacity = 1;

    Employee [] data = new Employee[capacity];

    public void addHourlyEmployee(HourlyEmployee obj){
        if(obj==null || !(obj instanceof HourlyEmployee)){
            throw new IllegalArgumentException("Only objects of HourlyEmployee Type are permitted!");
        }
        if(isFull()){
            doubleArray();
            data[numOfEntries] = obj;
            numOfEntries++;
        }
        else {
            data[numOfEntries] = obj;
            numOfEntries++;
        }
    }

    public void addSalariedEmployee(SalariedEmployee obj){
        if(obj==null || !(obj instanceof SalariedEmployee)){
            throw new IllegalArgumentException("Only objects of SalariedEmployee Type are permitted!");
        }

        if(isFull()){
            doubleArray();
            data[numOfEntries] = obj;
            numOfEntries++;
        }
        else {
            data[numOfEntries] = obj;
            numOfEntries++;
        }
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
    public void print(){
        for(int i =0;i<numOfEntries;i++){
            if(data[i]!=null) {
                System.out.println(data[i].getFirstName() + " " + data[i].getLastName() + " " + data[i].getWage());
            }
        }
        System.out.println(getNumOfEntries());
    }

    private int getIndex(Employee t){
        for(int i = 0;i<data.length;i++){
            if(data[i].equals(t)){
                return i;
            }
        }
        return -1;
    }
    public String deleteEmployee(String name){
        for(Employee a: data){
            if(a.getLastName().equalsIgnoreCase(name)){
                String k = a.getName();
                Employee temp = a;
                data[getIndex(a)] = data[data.length-1];
                data[data.length-1] = null;

                numOfEntries--;
                return String.format("\tEmployee: "+k+", has been deleted!");


            }
        }
        return "";
    }
    @Override
    public String toString() {

        return "";

    }

}
