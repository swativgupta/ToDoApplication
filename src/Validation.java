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

public class Validation {

	ArrayList<Task> tasktList = new ArrayList<>();

	/**
	 * This method will validate the task during creation.
	 * 
	 * @author Swati Gupta
	 * 
	 * @return boolean
	 */

	public boolean validateTask() {

		return true;
	}

	/**
	 * This method will will check if user have entered a valid date in currect
	 * format.
	 * 
	 * @author Swati Gupta
	 * @param dueDate
	 * @return String
	 */

	public String validateDate(String dueDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
		sdf.setLenient(false);
		try {
			sdf.parse(dueDate);
			if (validateDueDate(dueDate)) {
				return dueDate;
			} else {
				System.out.println("The date should be either future or current date");
				return null;
			}
		} catch (Exception e) {
			System.out.println(dueDate + " is not a valid Date");
			return null;
		}

	}

	/**
	 * This method will alow the user to enter only present or future dates
	 * 
	 * @author Swati Gupta
	 * @param dueDate
	 * @return String
	 */

	public boolean validateDueDate(String dueDate) {

		// To validate if date entered is a future date
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date date = null;
		try {
			date = format.parse(dueDate);

		} catch (ParseException e) {

			e.printStackTrace();
		}
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
		int value = date.compareTo(currDate);
		// System.out.println("value of comparision is "+value);
		if (value < 0) {

			return false;
		} else {
			// System.out.println("Exiting Validation checking for date so that no back
			// dates are allowed");
			return true;
		}
	}

}
