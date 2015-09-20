/**
 * A Feedback object consists of feedback message specifying the result of a command execution, 
 * as well as feedback message arguments, the additional information needed for the feedback. 
 * 
 * @author Jenny
 *
 */

public class Feedback {
	private String message;
	private String messageArguments;
	
	public Feedback() {
		this.message = "";
		this.messageArguments = "";
	}
	
	public Feedback(String msg) {
		this.message = msg;
		this.messageArguments = "";
	}
	
	public Feedback(String msg, String msgArguments) {
		this.message = msg;
		this.messageArguments = msgArguments;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessageArguments() {
		return messageArguments;
	}
	
	public void setMessageArguments(String messageArguments) {
		this.messageArguments = messageArguments;
	}
	
}
