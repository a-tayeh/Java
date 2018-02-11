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
        return super.getWage();
    }

    @Override
    public void setWage(double wage) {

        setWage(wage);
    }
}
