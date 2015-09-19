
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
