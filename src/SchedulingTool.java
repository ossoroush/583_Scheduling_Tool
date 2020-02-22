import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class SchedulingTool {
	// Array carrying all strings of tasks
	// Desired inputs:
	// HS = Hardware selection
	// SC = Software configuration
	// IH = Install hardware
	// DM = Data migration
	// DOP = Draft office prodecdures
	// RS = Recruit staff
	// IT = Install and test
	// UT = User training
	
	private List<Task> tasks = new ArrayList<>();
	private List<Task> finalTasks = new ArrayList<>();
	private List<Task> criticalPath = new ArrayList<>();
	private int totalDuration = 0;

	public void readInputs(String definitionFileName, String dependencyFileName) {
		//Populate the task array "tasks" above
	}

	//calculates earliest start/end times, populates a list of final tasks, and total duration of project
	public void calculateEarly() {
		Deque<Task> dependencyQueue = new LinkedList<>();
		Task currentDependency = null;
		int earliestStartTime = 0;
		int projectDuration = 0;

		finalTasks = (ArrayList<Task>) ((ArrayList<Task>) tasks).clone();	//remove tasks from final tasks until only final tasks are left

		do {
			for (Task node : tasks) {
				if ((currentDependency == null && node.getDependencies().isEmpty()) || node.getDependencies().contains(currentDependency)) {
					if (currentDependency != null) {
						finalTasks.remove(currentDependency);
					}
					if (!dependencyQueue.contains(node)) {
						dependencyQueue.add(node);
					}
					if (earliestStartTime >= node.getEarlyStart()) {
						node.setEarlyStart(earliestStartTime);
						node.setEarlyFinish(earliestStartTime + node.getDuration());

						if (node.getEarlyFinish() > projectDuration) {
							projectDuration = node.getEarlyFinish();
						}
					}
				}
			}
			
			currentDependency = dependencyQueue.poll();
			if (currentDependency != null) {
				earliestStartTime = currentDependency.getEarlyFinish();
			}
		} while (currentDependency != null);

		totalDuration = projectDuration;
    }

    public void calculateLate() {
      	//mani's algorithm here
    }

    // critical path algorithm
	public void calculateCriticalPath() {
		List<Task> currentTasks = finalTasks;
		for (Task task : currentTasks) {
			//TODO: implement this
		}
	}

	public void writeOutputs() {
		//javier's algorithm here
	}

	public void runProgram(String definitionFileName, String dependencyFileName) {
		readInputs(definitionFileName, dependencyFileName);

		calculateEarly();
		calculateLate();
		calculateCriticalPath();

		writeOutputs();
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Task> getFinalTasks() {
		return finalTasks;
	}

	public int getTotalDuration() {
		return totalDuration;
	}

	/*
	public chart getGanttChart() {
		return ganttChart;
	}
	public void setGanttChart(uniqueID, earlyStart, earlyFinish, latestStart, latestFinish) {
		this.ganttChart = task, startDate, dueDate;
	}
	public void viewGanttChart(){
		ganttChart.setVisible(true);
	}
	*/

	public static void main (String[] args) {
		if (args.length < 2) {
			System.out.println("Program requires two arguments to run: definitionFileName dependencyFileName\nExiting...");
			System.exit(1);
		}
		String definitionFileName = args[0];
		String dependencyFileName = args[1];

		SchedulingTool tool = new SchedulingTool();
		tool.runProgram(definitionFileName, dependencyFileName);
	}
}