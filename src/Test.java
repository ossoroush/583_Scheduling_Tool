import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class Test {
	public static void main (String[] args) {
		Task a = new Task("a", "Hardware Selection", 6);
		Task b = new Task("b", "Software Config", 4);
		Task c = new Task("c", "Install Hardware", 3, Arrays.asList(a));
		Task d = new Task("d", "Data Management", 4, Arrays.asList(b));
		Task e = new Task("e", "Draft Docs", 3, Arrays.asList(b));
		Task f = new Task("f", "Recruit Staff", 10);
		Task g = new Task("g", "User Training", 3, Arrays.asList(e, f));
		Task h = new Task("h", "Install / Test", 2, Arrays.asList(c, d));

		SchedulingTool tool = new SchedulingTool();
		List<Task> tasks = new ArrayList<>();
		tasks.add(a);
		tasks.add(b);
		tasks.add(c);
		tasks.add(d);
		tasks.add(e);
		tasks.add(f);
		tasks.add(g);
		tasks.add(h);

		tool.setTasks(tasks);

		tool.calculateEarly();

		System.out.println("--ALL TASKS--");
		for (Task task : tool.getTasks()) {
			System.out.println(task);
		}

		System.out.println("\n--FINAL TASKS--");
		for (Task task : tool.getFinalTasks()) {
			System.out.println(task);
		}

		//temp: sets latest values until calculateLatest is implemented
		a.setLatestStart(2);
		a.setLatestFinish(8);
		b.setLatestStart(3);
		b.setLatestFinish(7);
		c.setLatestStart(8);
		c.setLatestFinish(11);
		d.setLatestStart(7);
		d.setLatestFinish(11);
		e.setLatestStart(7);
		e.setLatestFinish(10);
		f.setLatestStart(0);
		f.setLatestFinish(10);
		g.setLatestStart(10);
		g.setLatestFinish(13);
		h.setLatestStart(11);
		h.setLatestFinish(13);
		//end temp

		tool.calculateCriticalPath();

		System.out.println("\n--CRITICAL PATH--");
		for (Task task : tool.getCriticalPath()) {
			System.out.println(task);
		}
		tool.writeOutputs();

	}
}