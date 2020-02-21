public class schedulingTool {
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
	
	String[] tasks = {};

	// critical path algorithm
	public Task[] critical path() {

	}

	// print statement;
}



public class Task {
	// name for the unique ID of the task
	public String uniqueID;
	// name for the duration of the task
	public int duration;
	//  description of/for the task
	public String description
    // the earliest start
    public int earlyStart;
    // the earliest finish
    public int earlyFinish;
    // the latest start
    public int latestStart;
    // the latest finish
    public int latestFinish;
    // the difference between earliest start and latest start or earliest finish and latest finish.
    public int durFloat;
	
	// chart ganttChart;

	public String getUniqueID() {
        return uniqueID;
    }	
    public void setName(String uniqueID) {
        this.uniqueID = uniqueID;
    }
	
	public void viewTask(String uniqueID) {
		System.out.println("Task: " + Task.uniqueID);
	}
	
	public void getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	
	public int getDuration() {
        return duration;
	}
    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
	}
    public void setDescription(String description) {
        this.description = description;
    }
	
	public int getEarlyStart() {
        return earlyStart;
	}
    public void setEarlyStart(String earlyStart) {
        this.earlyStart = earlyStart;
    }
	
	public int getEarlyFinish() {
        return earlyFinish;
	}
    public void setEarlyFinish(String earlyFinish) {
        this.earlyFinish = earlyFinish;
    }

    public int getLatestStart() {
        return latestStart;
	}
    public void setLatestStart(String latestStart) {
        this.latestStart = latestStart;
    }
	
	public int getLatestFinish() {
        return latestFinish;
	}
    public void setLatestFinish(String latestFinish) {
        this.latestFinish = latestFinish;
    }

    public int getDurFloat() {
    	return durFloat;
    }
    public void setDurFloat() {
    	this.durFloat = durFloat;
    }

    public static void calculateEarly() {
    }

    public static void setEarly() {

    }

    public static void calculateLate() {
      
    }

    public static void setLate() {
        
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

}
