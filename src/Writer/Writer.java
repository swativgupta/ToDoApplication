package Writer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Utility.Constants;

/**
 * This class saves the task list to a file
 * 
 * @author Swati Gupta
 *
 */
public class Writer {
	/**
	 * This function saves the task list to a file
	 * 
	 * @author Swati Gupta
	 * @param taskList
	 *
	 */

	public void writeToFile(String taskList) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File(Constants.FILE_PATH));
		pw.write(taskList);
		pw.close();
		System.out.println(Constants.SAVE);
	}

}