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
	
	private Task[] tasks = {};

	public void readInputs(String definitionFileName, String dependencyFileName) {
		//Populate the task array "tasks" above
	}

	public void calculateEarly() {
		//russell's algorithm here
    }

    public void calculateLate() {
      	//mani's algorithm here
    }

    // critical path algorithm
	public void calculateCriticalPath() {
		//??? we need to do this as well I guess
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