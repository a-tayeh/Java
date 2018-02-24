import java.text.DecimalFormat;

public class SalariedEmployee extends Employee {

    public SalariedEmployee(String first, String last, double annualSalary) {
        super(first, last, annualSalary);

    }


    @Override
    public double computePay(double hours) {
        String strDouble = String.format("%.2f", (super.getWage()/52));
        return Double.parseDouble(strDouble);
    }
    public void setAnnualSalary(double annual){
        super.setWage(annual/2080);
    }
    public double getAnnualSalary(){
        return super.getWage();
    }
    @Override
    public double getWage() {
        String strDouble = String.format("%.2f", super.getWage()*2080);
        return Double.parseDouble(strDouble);
    }

    @Override
    public void setWage(double wage) {

        super.setWage(wage);
    }

    @Override
    public String toString() {
        String strDouble = String.format("%.2f", super.getWage());
        int count = 34 - (super.getName().length()+strDouble.length());
        String spaces = String.format("%"+count+"s", "");
        return String.format("%s%"+count+"s$%.2f/year",super.getName(),"",super.getWage());

    }
}
