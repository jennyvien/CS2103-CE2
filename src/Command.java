
/**
 * A command object consist of a command, what the user wants to do (i.e. add, display, clear, ...)
 * and a commandArgument, the additional information necessary to process the command. 
 * (i.e. when you add an item, you need to specify what you want to add.)
 * 
 * @author Jenny
 */
public class Command {
	private String command;
	private String commandArgument;
	
	public Command() {}

	public Command(String cmd, String cmdArgument) {
		this.command = cmd;
		this.commandArgument = cmdArgument;
	}
	
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getCommandArgument() {
		return commandArgument;
	}
	public void setCommandArgument(String commandArgument) {
		this.commandArgument = commandArgument;
	}
	
	
	
}
