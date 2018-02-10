public class SalariedEmployee extends Employee {

    public SalariedEmployee(String first, String last, double annualSalary) {
        super(first, last, annualSalary);
        annualSalary = 40 *52;

    }

    @Override
    public double computePay(double hours) {
        return 0;
    }

    @Override
    public double getWage() {
        return getWage();
    }

    @Override
    public void setWage(double wage) {

        setWage(wage);
    }
}
