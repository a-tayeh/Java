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
        return 0.0;
    }

    @Override
    public double getWage() {
        String strDouble = String.format("%.2f", super.getWage());
        return Double.parseDouble(strDouble);
    }

    @Override
    public String toString() {
        String strDouble = String.format("%.2f", super.getWage());

        return String.format("%s      $ %.2f/hour",super.getName(), super.getWage());

    }

}
