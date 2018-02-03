/**
 * @author alitayeh
 *
 *                                                  Ali Tayeh           CMSC256
 *
 *
 *  Poject_1:CustomDate
 *
 *  This project will take a CustomDate object with dates then determine if the date is valid and perform some operations
 *  on that date such as , setting/getting the date fields(month,day,year), advancing the date by one day/week and determining
 *  whether the year passed is a leap year or not!
 *  Some methods such as equals and toString are overriden to ensure appropriate output.
 *
 *
 */
public class CustomDate {

    // declaring our private fields month/day/year of type int
	private int month;
	private int day;
	private int year;

	// default constructor
	public CustomDate() {
		this.month = 1;
		this.day = 1;
		this.year = 2000;

	}

	/**
     * Our parameterized constructor using
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
				throw new IllegalArgumentException("February cannot exceed 28 days because "+ this.year +" is NOT a leap year");
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
	 * @param day,  the day to be set
	 */
	public void setDay(int day) {
			
			this.day = day;
			if(!(isValidDate())) {
				throw new IllegalArgumentException ("Day: "+ day+ ", is NOT a valid day!");
			}
			
		
	}

	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @param month,  the month to be set
	 */
	public void setMonth(int month) {

		this.month = month;
		if(!(isValidDate())) {
			throw new IllegalArgumentException ("Month: "+ month + ", is NOT a valid month!");
		}
		
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
		if(!(isValidDate())) {
			throw new IllegalArgumentException ("Year: "+ year + ", is not a valid year!");
		}
		
	}
	public boolean isValidDate() {
		
		if(this.month < 1 || this.month > 12) {
			return false;
		}
		if(this.day < 1 || this.day > 31) {
			return false;
		}
		if(this.year < 1) {
			return false;
		}
		
		switch(month) {
		
		case 1:
		case 3:
		case 5:
		case 7: 
		case 8:
		case 10:
		case 12:
			 if(day > 31) {
				 return false;
			}
			 break;
			 
		case 4:
		case 6:
		case 9:
		case 11:
			if(day > 30) {
				return false;
			}
			break;
			
		case 2:
			if(isLeapYear() && day > 29) {
				 return false;
				 
			}
			if((!isLeapYear() && day > 28)) {
				return false;
			}
			break;
		}
		
		return true;
	}
	
	public boolean isLeapYear() {
		if(this.year % 100 != 0 && this.year % 4 ==0 ) {
			return true;
		}
		else if(this.year % 100 == 0 && this.year % 400 == 0 ){
			return true;
		}
		return false;
	}
	
	
	public void advanceOneDay() {
		// switch statement that checks if month is 1,3,5,7,8,10 since these are the months with 31 days
		// and it has an if statement in case we go over 31 when we advance one day, if so, then increment month,
		// then set day = 1
		switch(month) {
		case 1: case 3: case 5: case 7: case 8: case 10:
			day = day+1;
			 if(day > 31) {
				month++;
				day = 1;
			 }
			 break;

		// switch statement that checks if month is 4,6,9,11 since these are the months with 30 days
		case 4: case 6: case 9: case 11:
			day = day+1;
			if(day > 30) {
				month++;
				day = 1;
			}
			break;

		// december is a special case, because we have to increment the year as well if we go beyond 31 days.
		case 12:
			day = day + 1;
			if(day > 31) {
				day = 1;
				month = 1;
				year++;
			}
			break;
			
		// february is 29 or 28 days depending on the fact if it's a leap year or not
		case 2:
			day = day+1;
			if(isLeapYear() && day > 29) {
				day = 1;
				month++;
			}
			else if((!isLeapYear() && day >28)) {
				day = 1;
				month++;
			}
			break;
		}
		
	}
	public void advanceOneWeek() {
		// switch statement that checks if month is 1,3,5,7,8,10 since these are the months with 31 days
		// and it has an if statement in case we go over 31 when we advance on week, if so, then increment month,
        // the set day to be the difference b/w day - 31 days after it has done the day += 7
		switch(month) {
			case 1: case 3: case 5: case 7: case 8: case 10:
				day = day + 7;
				 if(day > 31) {
					month++;
					day = day - 31;
				}
				 break;
			// switch statement that checks if month is 4,6,9,11 since these are the months with 30 days
			case 4: case 6: case 9: case 11:
				day = day + 7;
				if(day > 30) {
					month++;
                    day = day - 30;
				}
				break;
			// december is a special case, because we have to increment the year as well if we go beyond 31 days.
			case 12:
				day = day + 7;
				if(day > 31) {
					day = day - 31;
					month = 1;
					year++;
				}
				break;

			// february is 29 or 28 days depending on the fact if it's a leap year or not
            //
			case 2:
				day = day + 7;
				if(isLeapYear() && day > 29) {
                    day = day - 29;
					month++;
				}
				else if( (!(isLeapYear()) && day > 28)) {
                    day = day - 28;
					month++;
				}
				break;
		}
		
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		else if(!(obj instanceof CustomDate)) {
			return false;
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


	public int compareTo(Object obj) {
		// if the object is null then a NullPointerException will be thrown
		if(obj == null) {
			throw new NullPointerException("\nThe passed object is null!");
		}
        // if the object is not an instance of CustomDate class then an IllegalArgumentException will be thrown
		else if(!(obj instanceof CustomDate)){
			throw new IllegalArgumentException("Only objects of type CustomDate can be compared!");
		}

		// if the this object is equal to the o1 object
		if (this.equals((CustomDate)obj)) {
			return 0;
		}
        else if(this.year < ((CustomDate) obj).getYear()) {return -1;}

        else if(this.year > ((CustomDate) obj).getYear()) {return 1;}

	    else if(this.year == ((CustomDate) obj).getYear()){

                if(this.month < ((CustomDate) obj).getMonth()) {return -1;}
                else if(this.month > ((CustomDate) obj).getMonth()) {return 1;}

                else if(this.month == ((CustomDate) obj).getMonth()){

                    if(this.day < ((CustomDate) obj).getDay()) {return -1;}
                    else if(this.day > ((CustomDate) obj).getDay()) {return 1;}
                }
        }


		return 2;
	}


    @Override
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
			 break;}
		
		switch(day) {
		case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9:
			 dayZero = "0";
			 break;}
		
		// these if statements are to decide how many leading zeroes the year should have depending on the year value!
		if(year < 10) {
			yearZero = "000";}
		
		else if(year < 100) {
			yearZero = "00";}
		
		else if(year < 999) {
			yearZero = "0";}
		
		// this will print the month/day/year along with how many zeroes in 
		// front of each value depending on the results of our switch/if statements
		return String.format(monthZero + "%d/" +dayZero+ "%d/" +yearZero+"%d", this.month,this.day,this.year);
	}

	

}
