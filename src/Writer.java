import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
		PrintWriter pw = new PrintWriter(new File("ToDo.csv"));
		pw.write(taskList);
		pw.close();
		System.out.println("done!");
	}

}