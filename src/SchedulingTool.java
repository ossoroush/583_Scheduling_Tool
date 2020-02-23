import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;

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
	
	private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String FILE_HEADER = "Task uniqueID: description" +
            ", Top: earlyStart - duration - earlyFinish" +
            ", Bottom: latestStart - durFloat - latestFinish";

	public void readInputs(String definitionFileName, String dependencyFileName) {
		//Populate the task array "tasks" above
		try (BufferedReader tasksReader = Files.newBufferedReader(Paths.get(definitionFileName))){
			String line;
			int lineNumber = 1;
			while((line = tasksReader.readLine()) != null){
				String[] attributes = line.split(",");
				if (attributes.length != 3) {
					System.out.println("wrong number of attributes in line " + lineNumber + ", tasks file");
				}
				else {
					String uniqueID = attributes[0].trim();
					String description = attributes[1].trim();
					int duration;
					try {
						duration = Integer.parseInt(attributes[2].trim());
						Task task = new Task(uniqueID, description, duration);
						tasks.add(task);
					}
					catch (NumberFormatException e)
					{
						System.out.println("the duration should be an integer, line" + lineNumber + ", tasks file");
					}
				}
				lineNumber++;
			}
		}

		catch( IOException jse) {
			System.out.println("could not find Tasks file");
		}
		try (BufferedReader depReader = Files.newBufferedReader(Paths.get(dependencyFileName))){
			String line;
			int lineNumber = 1;
			while((line = depReader.readLine()) != null){
				String[] attributes = line.split(",");
				if (attributes.length != 2) {
					System.out.println("wrong number of strings in line " + lineNumber + ", dependencies file");
				}
				else {
					String dependencyID = attributes[0].trim();
					String theTaskID = attributes[1].trim();
					if(!(dependencyID.equals("Start") || theTaskID.equals("End"))) {
						Task depndencyTask = searchByID(dependencyID);
						Task theTask = searchByID(theTaskID);
						if(depndencyTask != null && theTask != null) {
							theTask.addToDependencies(depndencyTask);
						}
						else {
							System.out.println("wrong ID in line " + lineNumber + ", dependencies file");
						}
					}
				}
				lineNumber++;
			}

			/**For testing purposes( uncomment these lines to see all tasks and their dependencies)**/
//			for(Task task: tasks) {
//				System.out.println(task.getUniqueID() + "," + task.getDescription() + "," + task.getDuration());
//				List<Task> dependencies = task.getDependencies();
//				for(Task dependency: dependencies) {
//					System.out.print(dependency.getUniqueID()+",");
//				}
//				System.out.println();
//			}
		}

		catch( IOException jse) {
			System.out.println("could not find Dependencies file");
		}
	}

	//takes an ID and returns the corresponding Task
	public Task searchByID(String uniqueID) {
		for(Task task: tasks) {
			if(task.getUniqueID().equals(uniqueID)) {
				return task;
			}
		}
		return null;
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
		Task criticalPathTask = null;

		do {
			for (Task task : currentTasks) {
				if (task.getEarlyFinish() == task.getLatestFinish()) {
					criticalPathTask = task;
					break;
				}
			}
			if (criticalPathTask != null) {
				criticalPath.add(0, criticalPathTask);
				currentTasks = criticalPathTask.getDependencies();
			}
		} while (criticalPathTask != null && !currentTasks.isEmpty());
		
	}
	
	public void writeOutputs() {
        FileWriter fileWriter = null;
                 
        try {
            fileWriter = new FileWriter("Sandwich_Project.csv");
 
            fileWriter.append(FILE_HEADER.toString());
            
            fileWriter.append(NEW_LINE_SEPARATOR);
            
            for (Task tasks : this.tasks) {
                fileWriter.append(tasks.toString());
				fileWriter.append(NEW_LINE_SEPARATOR);
            }
             
            System.out.println("CSV file was created successfully !!!");
             
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {
             
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
             
        }
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

	public List<Task> getCriticalPath() {
		return criticalPath;
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