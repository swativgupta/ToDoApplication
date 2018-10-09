import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This class will be responsible to read data from file
 * 
 * @author Swati Gupta
 * @version 2018.09.27
 */
public class Reader {

	// How many fields are expected.
	private static final int NUMBER_OF_FIELDS = 5;
	// Index values for the fields in each record.
	private static final int TaskId = 0, TaskDescription = 1, TaskStatus = 2, ProjectDescription = 3, DueDate = 4;

	/**
	 * Create a SightingReader.
	 */
	public Reader() {
	}

	/**
	 * Read sightings in CSV format from the given file. Return an ArrayList of
	 * Sighting objects created from the information in the file.
	 * 
	 * @param filename The file to be read - should be in CSV format.
	 * @return A list of Sightings.
	 */
	public ArrayList<Task> getTasks(String filename) {
		// Create a TaskList from a CSV input line.
		Function<String, Task> createTask = record -> {
			String[] parts = record.split(",");
			if (parts.length == NUMBER_OF_FIELDS) {
				try {
					String taskId = parts[TaskId].trim();
					String taskDescription = parts[TaskDescription].trim();
					String taskStatus = parts[TaskStatus].trim();
					String projectDescription = parts[ProjectDescription].trim();
					String dueDate = parts[DueDate].trim();
					return new Task(taskId, taskDescription, taskStatus, projectDescription, dueDate);
				} catch (NumberFormatException e) {
					System.out.println("Task record has a malformed integer: " + record);
					return null;
				}
			} else {
				System.out.println("Task record has the wrong number of fields: " + record);
				return null;
			}
		};
		ArrayList<Task> tasks;
		try {
			System.out.println(Files.lines(Paths.get(filename)));
			tasks = Files.lines(Paths.get(filename))

					.filter(record -> record.length() > 0 && record.charAt(0) != '#').map(createTask)
					.filter(task -> task != null).collect(Collectors.toCollection(ArrayList::new));
		} catch (IOException e) {
			System.out.println("Unable to open " + filename);

			tasks = new ArrayList<>();
		}

		return tasks;
	}

}
