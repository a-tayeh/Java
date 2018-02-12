import java.text.DecimalFormat;

public class SalariedEmployee extends Employee {

    public SalariedEmployee(String first, String last, double annualSalary) {
        super(first, last, annualSalary);

    }

    @Override
    public double computePay(double hours) {
        return 0;
    }

    @Override
    public double getWage() {
        String strDouble = String.format("%.2f", super.getWage()/2080);
        return Double.parseDouble(strDouble);
    }

    @Override
    public void setWage(double wage) {

        setWage(wage);
    }

    @Override
    public String toString() {
        return String.format("%s      $%.2f/year",super.getName(), super.getWage());

    }
}
