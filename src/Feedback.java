
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
