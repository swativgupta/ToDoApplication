package Utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * This class is responsible for providing different kind of validations for the
 * application
 * 
 * @author Swati Gupta
 * @version 2018.09.27
 */

public class Validator {

	/*public boolean validateDueDate(Date dueDate) {

		// For fetching the current date
		Date currDate = new Date();

		Calendar cal = Calendar.getInstance();
		cal.setTime(currDate);

		// Set time fields to zero
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		// Put it back in the Date object
		currDate = cal.getTime();

		// System.out.println("Current date is :::" +currDate);
		// System.out.println("Date entered " +date);
		if (dueDate != null) {
			
			
		//	int value = dueDate.compareTo(currDate);

			// System.out.println("value of comparision is "+value);
			if (dueDate.before(currDate)) {

				return false;
			} else {
				// System.out.println("Exiting Validation checking for date so that no back
				// dates are allowed");
				return true;
			}
		} else {
			return false;

		}
	}*/

	/**
	 * This method will convert string date to Date format.
	 * 
	 * @author Swati Gupta
	 * @param dueDate
	 * @return String
	 */
	public Date convertToDate(String dueDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
		sdf.setLenient(false);
		Date date = null;
		try {
			date = sdf.parse(dueDate);
		} catch (Exception e) {
			System.out.println("WRONG FORMAT DATE");
			date = null;
		}
		return date;
	}

	public boolean validate(Date date) {

		// For fetching the current date
		Date currDate = new Date();

		Calendar cal = Calendar.getInstance();
		cal.setTime(currDate);

		// Set time fields to zero
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		// Put it back in the Date object
		currDate = cal.getTime();
		
		return date.before(currDate);
	}
}
