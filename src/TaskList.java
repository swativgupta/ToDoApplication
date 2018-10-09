import java.util.ArrayList;

public class TaskList {

	ArrayList<Task> tasks= new ArrayList<>();
	int totalTask = 0;
	int doneTask=0;
	public ArrayList<Task> getTasks() {
		return tasks;
	}
	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}
	public int getTotalTask() {
		return totalTask;
	}
	public void setTotalTask(int totalTask) {
		this.totalTask = totalTask;
	}
	public int getDoneTask() {
		return doneTask;
	}
	public void setDoneTask(int doneTask) {
		this.doneTask = doneTask;
	}
}
