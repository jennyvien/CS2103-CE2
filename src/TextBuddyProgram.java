import java.util.Scanner;

/**
 * TextBuddy is a program that takes user inputs and records it into a text
 * file. User can view and delete items from the file as s/he wants.
 * 
 * If the user does not specify what file s/he would like inputs to get written
 * out to, program will store it into "mytextfile.txt" by default.
 * 
 * If the user does not enter in a valid command, they will be prompted0 to
 * enter another command.
 * 
 * @author Jenny Vien
 */
public class TextBuddyProgram {
	private static final String MSG_WELCOME = "Welcome to TextBuddy. %s is ready for use.";
	private static final String MSG_ENTER_COMMAND = "command:";

	private static final int COMMAND_HAS_NO_ARGS = 1;

	private static final String DEFAULT_OUTPUT_FILE = "mytextfile.txt";

	private String outputFileName;
	private Scanner scanner;
	private TextBuddyLogic tbLogic;
	private TextBuddyFileManager tbFileManager;

	public static void main(String[] args) {
		TextBuddyProgram tb = new TextBuddyProgram();
		tb.determineOutputFile(args);
		tb.tbFileManager.setOutputFileName(tb.outputFileName);
		tb.startProgram();
	}

	public TextBuddyProgram() {
		scanner = new Scanner(System.in);
		tbLogic = new TextBuddyLogic();
		tbFileManager = new TextBuddyFileManager();

	}

	public void startProgram() {
		this.printMessage(new Feedback(MSG_WELCOME));
		this.run();
	}

	/**
	 * Runs the program until the user specifies that s/he wants to exit.
	 */
	private void run() {
		while (true) {
			this.printMessage(new Feedback(MSG_ENTER_COMMAND));
			Command newCommand = this.getUserCommand();
			Feedback feedbackMessage = tbLogic.executeUserCommand(newCommand);
			this.printMessage(feedbackMessage);
			tbFileManager.updateOutputFile(tbLogic.getTempItemsStorage());
		}
	}

	/**
	 * @return an instance of Command 
	 * userInput[0] contains the command (add, display, etc.) 
	 * userInput[1] contains the arguments for the command. (i.e. for "add item," userInput[1] contains "item")
	 */
	private Command getUserCommand() {
		String[] userInput = this.getUserInput();
		Command userCommand = this.parseUserInput(userInput);
		return userCommand;
	}

	/**
	 * Get user input from System.in
	 */
	private String[] getUserInput() {
		String[] userInput = new String[2];

		if (this.scanner.hasNextLine()) {
			userInput = this.scanner.nextLine().split(" ", 2);
		} else {
			System.exit(0);
		}
		return userInput;
	}
	
	/**
	 * Helper method for getUserCommand which parses userInput and returns a command.
	 */
	private Command parseUserInput(String[] userInput) {
		Command userCommand = new Command();

		userCommand.setCommand(userInput[0].trim());

		if (userInput.length == COMMAND_HAS_NO_ARGS) {
			userCommand.setCommandArgument("");
		} else {
			userCommand.setCommandArgument(userInput[1].trim());
		}
		return userCommand;
	}

	/**
	 * Print feedback messages to System.out.
	 */
	private void printMessage(Feedback feedback) {
		if(feedback.getMessage() != "") {
 			System.out.println(String.format(feedback.getMessage(), this.outputFileName, feedback.getMessageArguments()));
		}	
	}
	
	/**
	 * Determines which outputFile to write to.
	 * If user specified a file, the program will write to the specified file.
	 * Otherwise, the program will write to "mytextfile.txt"
	 */
	private void determineOutputFile(String[] args) {
		if (args.length == 0) {
			this.outputFileName = DEFAULT_OUTPUT_FILE;
		} else {
			this.outputFileName = args[0].trim();
		}
	}
}
