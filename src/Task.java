public class Task {
	//--Entered values--
	// name for the unique ID of the task
	private String uniqueID;
	// name for the duration of the task
	private int duration;
	//  description of/for the task
	private String description;
	// tasks that must complete before this task begins
	private Task[] dependencies = {};

	//--Calculated values--
    // the earliest start
    private int earlyStart;
    // the earliest finish
    private int earlyFinish;
    // the latest start
    private int latestStart;
    // the latest finish
    private int latestFinish;
    // the difference between earliest start and latest start or earliest finish and latest finish.
    private int durFloat;
	
	// chart ganttChart;


	//Constructor
	public Task(String uniqueID, String description, int duration) {
		this.uniqueID = uniqueID;
		this.description = description;
		this.duration = duration;
	}

	public Task(String uniqueID, String description, int duration, Task[] dependencies) {
		this.uniqueID = uniqueID;
		this.description = description;
		this.duration = duration;
		this.dependencies = dependencies;
	}

	public String getUniqueID() {
        return uniqueID;
    }	
    public void setName(String uniqueID) {
        this.uniqueID = uniqueID;
    }
	
	public void viewTask(String uniqueID) {
		System.out.println("Task: " + uniqueID);
	}
	
	public int getDuration() {
        return duration;
	}
    public void setDuration(int duration) {
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
    public void setEarlyStart(int earlyStart) {
        this.earlyStart = earlyStart;
    }
	
	public int getEarlyFinish() {
        return earlyFinish;
	}
    public void setEarlyFinish(int earlyFinish) {
        this.earlyFinish = earlyFinish;
    }

    public int getLatestStart() {
        return latestStart;
	}
    public void setLatestStart(int latestStart) {
        this.latestStart = latestStart;
    }
	
	public int getLatestFinish() {
        return latestFinish;
	}
    public void setLatestFinish(int latestFinish) {
        this.latestFinish = latestFinish;
    }

    public int getDurFloat() {
    	return durFloat;
    }
    public void setDurFloat() {
    	this.durFloat = durFloat;
    }
}