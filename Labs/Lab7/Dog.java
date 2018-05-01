public class Dog implements Comparator<Dog>{
    private String dogName;
    private int count;

    public Dog(String dogName, int count) {
        this.dogName = dogName;
        this.count = count;
    }
    public Dog() {
        this("no-name",0);
    }

    public String getDogName() {

        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    @Override
    public  int compare(Dog lhs, Dog rhs) {
        return lhs.getDogName().compareToIgnoreCase(rhs.getDogName());
    }


}
