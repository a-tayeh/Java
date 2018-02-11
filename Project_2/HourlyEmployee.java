public class HourlyEmployee extends Employee {


    public HourlyEmployee(String firstName, String lastName, double hourlyWage){
        super(firstName, lastName, hourlyWage);
    }

    @Override
    public double computePay(double hours) {
        if(hours<=40){
            return getWage() * hours;
        }
        else if(hours>40){
            return hours * (1.5 * getWage());
        }
        return 0;
    }

    @Override
    public double getWage() {
        return super.getWage();
    }

    @Override
    public String toString() {
        return super.toString();

    }

}
