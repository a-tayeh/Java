public class HourlyEmployee extends Employee {


    public HourlyEmployee(String firstName, String lastName, double hourlyWage){
        super(firstName, lastName, hourlyWage);
    }

    @Override
    public double computePay(double hours) {
        if(hours<=40){
            String strDouble = String.format("%.2f", getWage() * hours);
            return Double.parseDouble(strDouble);
        }
        else if(hours>40){
            String strDouble = String.format("%.2f", hours * (1.5 * getWage()));
            return Double.parseDouble(strDouble);
        }
        return 0.0;
    }



    @Override
    public double getWage() {
        String strDouble = String.format("%.2f", super.getWage());
        return Double.parseDouble(strDouble);
    }

//    public String weeklyPayroll() {
//        String strDouble = String.format("%.2f", super.getWage());
//        int count = 29 - (super.getName().length()+strDouble.length());
//        String spaces = String.format("%"+count+"s", "");
//        return String.format("%s"+"      h%"+ count+"s$%.2f/hour",super.getName(),"",super.getWage());
//
//    }
    @Override
    public String toString() {
        String strDouble = String.format("%.2f", super.getWage());
        int count = 29 - (super.getName().length()+strDouble.length());
        String spaces = String.format("%"+count+"s", "");
        return String.format("%s"+"      h%"+ count+"s$%.2f/hour",super.getName(),"",super.getWage());

    }

}
