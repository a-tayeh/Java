/**
 * 
 */

/**
 * @author alitayeh
 *
 */
public class CustomDate {


	private int month;
	private int day;
	private int year;
	
	public CustomDate() {
		this.month = 1;
		this.day = 1;
		this.year = 2000;
	}

	/**
	 * @param day
	 * @param month
	 * @param year
	 */
	public CustomDate(int month, int day, int year) {

			
		this.day = day;
		this.month = month;
		this.year = year;
	
		if(!(isValidDate())) {
			throw new IllegalArgumentException("Invalid Date ---> " + toString());
			
		}
		else if(isValidDate() && isLeapYear()) {
			if(this.month == 2 && this.day >29) {
				throw new IllegalArgumentException("February cannot exceed 29 days because "+ this.year +" is a leap year");
				
			}
		}
		else if(isValidDate() && !(isLeapYear()) ) {
			if(this.month == 2 && this.day >28) {
				throw new IllegalArgumentException("February cannot exceed 28 days because "+ this.year +" is NOT leap year");
			 	}
			}
			

	}

	/**
	 * @return the day
	 */
	public int getDay() {

		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(int day) {

			this.day = day;
			
		
	}

	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {

		this.month = month;
		
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		
		this.year = year;
		
	}
	public boolean isValidDate() {
		
		if(this.month < 1 || this.month > 12) {
			return false;
		}
		else if(this.day < 1 || this.day > 31) {
			return false;
		}
		else if(this.year < 1) {
			return false;
		}
		
		switch(month) {
		case 1: case 3: case 5: case 7: case 8: case 10: case 12:
			 if(day > 31) {
				 return false;
			}
			 break;
			 
		case 4: case 6: case 9: case 11:
			if(day > 30) {
				return false;
			}
			break;
			
		case 2:
			if(isLeapYear() && day > 29) {
				 return false;
			}
			else if(!(isLeapYear() && day > 28)) {
				return false;
			}
			break;
		}
		return true;
	}
	
	public boolean isLeapYear() {
		if(this.year % 100 != 0 && this.year % 4 ==0) {
			return true;
		}
		return false;
	}
	
	
	public void advanceOneDay() {
		switch(month) {
		case 1: case 3: case 5: case 7: case 8: case 10: case 12:
			day = day+1;
			 if(day > 31) {
				month++;
				day = 1;
			 }
			 break;
			 
		case 4: case 6: case 9: case 11:
			day = day+1;
			if(day > 30) {
				month++;
				day = 1;
			}
			break;
			
		case 2:
			day = day+1;
			if(isLeapYear() && day > 29) {
				day = 1;
				month++;
			}
			else if(!(isLeapYear() && day >28)) {
				day = 1;
				month++;
			}
			break;
		}
		
	}
	public void advanceOneWeek() {
		
		switch(month) {
			case 1: case 3: case 5: case 7: case 8: case 10: case 12:
				day = day + 7;
				 if(day > 31) {
					month++;
					day = 1;
				}
				 break;
			 
			case 4: case 6: case 9: case 11:
				day = day + 7;
				if(day > 30) {
					month++;
					day = 1;
				}
				break;
			
			case 2:
				day = day + 7;
				if(isLeapYear() && day > 29) {
					day = 1;
					month++;
				}
				else if( (!(isLeapYear()) && day > 28)) {
					day = 1;
					month++;
				}
				break;
		}
		
	}
	
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		else if(!(obj instanceof CustomDate)) {
			throw new ClassCastException("The specified object is not of CustomDate type!");
		}
		// checking if the month/day/year of our passed-in object equals our values from CustomDate while making sure that we're casting 
		// CustomDate to our passed-in object
		
		if((this.day ) == (((CustomDate) obj).day ) &&
				(this.month ) == (((CustomDate) obj).month ) 
				&& (this.year ) == (((CustomDate) obj).year )) {
			return true;
		}
		
		return false;
	}
	public String toString() {
		// These will hold our number of zeroes depending on our month/day/year value
		String monthZero = "";
		String dayZero = "";
		String yearZero = "";
		
		// the next two switch statements are just in case our months are from 1-9, then a leading zero will be added 
		// next to that month when toString is called!
		switch(month) {
		case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9:
			 monthZero = "0";
			 break;
		}
		switch(day) {
		case 1: case 2: case 3: case 4: case 5:
		case 6: case 7: case 8: case 9:
			 dayZero = "0";
			 break;
		}
		
		// these if statements are to decide how many leading zeroes the year should have depending on the year value!
		if(year < 10) {
			yearZero = "000";
		}
		else if(year < 100) {
			yearZero = "00";
		}
		else if(year < 999) {
			yearZero = "0";
		}



		// this will print the month/day/year along with how many zeroes in 
		// front of each value depending on the results of our switch/if statements
		return String.format(monthZero + "%d/" +dayZero+ "%d/" +yearZero+"%d", this.month,this.day,this.year);
	}
	
	public int compareTo(Object o1) {
		if(o1 == null) {
			throw new NullPointerException("\nThe passed object is null!");
		}
		else if (this == (CustomDate) o1) {
			return 0;
		}
		if(toString().compareTo(o1.toString()) < 0) {
			return -1;
		}
		if(toString().compareTo(o1.toString()) > 0) {
			return 1;
		}
		
		
		
		return 2;
	}

	

}
