class Main {
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