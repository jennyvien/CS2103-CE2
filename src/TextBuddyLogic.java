
import java.util.ArrayList;

/**
 * TextBuddyLogic is the brain behind the TextBuddyProgram. This unit accepts
 * the user command and executes it. After executing the command it will return
 * a feedback to TextBuddyProgram.
 * 
 * @author Jenny
 *
 */
public class TextBuddyLogic {

	private static final String MSG_ADDED_ITEM = "added to %s: \"%s\"";
	private static final String MSG_DELETED_ITEM = "deleted from %s: \"%s\"";
	private static final String MSG_CLEARED_CONTENT = "all content deleted from %s";
	private static final String MSG_NO_ITEM_TO_DISPLAY = "%s is empty";
	private static final String MSG_NO_ITEM_TO_DELETE = "%s is empty. No item to delete.";
	private static final String MSG_INVALID_COMMAND = "Please enter a valid command.";
	private static final String MSG_INVALID_ARG = "Please enter an argument.";

	private static final String EMPTY_STRING = "";
	private int numItems = 0;
	private ArrayList<String> tempItemsStorage = new ArrayList<String>();

	public Feedback executeUserCommand(Command userCommand) {
		String command = userCommand.getCommand();
		String commandArgument = userCommand.getCommandArgument();
		Feedback result;

		switch (command) {
		case "add":
			result = this.addItem(commandArgument);
			break;
		case "display":
			result = this.displayItems();
			break;
		case "delete":
			result = this.deleteItem(Integer.parseInt(commandArgument));
			break;
		case "clear":
			result = this.clearFileContent();
			break;
		case "search":
			result = this.searchItem(commandArgument);
			break;
		case "sort":
			result = this.sortItems();
			break;
		case "exit":
			System.exit(0);
		default:
			result = new Feedback(MSG_INVALID_COMMAND);
		}
		return result;
	}

	/**
	 * @param item
	 *            The item to be added.
	 */
	public Feedback addItem(String item) {
		if (item.equals(EMPTY_STRING)) {
			return new Feedback(MSG_INVALID_ARG);
		} else {
			this.tempItemsStorage.add(++this.numItems + ". " + item);
		}
		return new Feedback(MSG_ADDED_ITEM, item);
	}

	/**
	 * Displays all the items added by the user.
	 */
	public Feedback displayItems() {
		if (this.numItems == 0) {
			return new Feedback(MSG_NO_ITEM_TO_DISPLAY);
		} else {
			this.printItems();
			return new Feedback();
		}
	}

	/**
	 * @param deleteId
	 *            The id of the item the user want to delete.
	 * @return The item that was deleted from the file.
	 */
	public Feedback deleteItem(int deleteId) {
		if (this.numItems == 0) {
			return new Feedback(MSG_NO_ITEM_TO_DELETE);
		} else if (deleteId > this.numItems) {
			return new Feedback(MSG_INVALID_ARG);
		} else {
			int deleteIndex = deleteId - 1;
			String deletedItem = tempItemsStorage.get(deleteIndex).split(" ", 2)[1];
			this.tempItemsStorage.remove(deleteIndex);
			this.updateIds(deleteId);
			this.numItems--;
			return new Feedback(MSG_DELETED_ITEM, deletedItem);
		}

	}

	/**
	 * Update the id of all items following the item deleted.
	 * 
	 * @param itemDeleted
	 *            the id to start renumbering from For instance after deleting
	 *            the item 2 1. jopjpo => 1. jopjpo 3. popo => 2. popo 4. jpoo
	 *            => 3. jpoo
	 */
	public void updateIds(int startId) {
		int startIndex = startId - 1;
		int newId = startId;
		for (int i = startIndex; i < tempItemsStorage.size(); i++) {
			String item = tempItemsStorage.get(i).split(" ", 2)[1];
			tempItemsStorage.set(i, newId + ". " + item);
			newId++;
		}
	}

	/**
	 * This method deletes all the entry from the file.
	 */
	public Feedback clearFileContent() {
		tempItemsStorage.clear();
		this.numItems = 0;
		return new Feedback(MSG_CLEARED_CONTENT);
	}

	/**
	 * Prints out all the items in the file to System.out
	 */
	public void printItems() {
		for (int i = 0; i < tempItemsStorage.size(); i++) {
			System.out.println(tempItemsStorage.get(i));
		}
	}

	/**
	 * @param item
	 *            The word the user would like to search for.
	 */
	public Feedback searchItem(String item) {
		ArrayList<String> searchResult = searchInStorage(item);
		printSearchResult(searchResult);
		return new Feedback();
	}

	/**
	 * Helper method that searches for word in tempItemStorage.
	 */
	public ArrayList<String> searchInStorage(String item) {
		ArrayList<String> searchResult = new ArrayList<String>();

		for (int i = 0; i < tempItemsStorage.size(); i++) {
			String line = tempItemsStorage.get(i);
			if (searchLine(line, item.trim()) == true) {
				searchResult.add(line);
			}
		}
		return searchResult;
	}

	/**
	 * Print search result to sysout.
	 * 
	 * @param searchResult
	 */
	public void printSearchResult(ArrayList<String> searchResult) {
		for (int i = 0; i < searchResult.size(); i++) {
			System.out.println(searchResult.get(i));
		}
	}

	/**
	 * Helper method to search each individual line for the word.
	 */
	public boolean searchLine(String line, String word) {
		String[] tokens = breakUpLine(line);

		boolean isWordFound = false;
		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i].trim().equals(word)) {
				isWordFound = true;
			}
		}
		return isWordFound;
	}

	/**
	 * Helper method for searchLine that breaks up each line into tokens.
	 */
	public String[] breakUpLine(String lineInFile) {
		return lineInFile.split("\\W+");
	}

	public Feedback sortItems() {
		this.removeIds();
		java.util.Collections.sort(tempItemsStorage);
		this.reassignIds();
		return new Feedback();
	}

	/**
	 * Helper method for sortItems that strips each line of the id before
	 * sorting.
	 */
	public void removeIds() {
		for (int i = 0; i < tempItemsStorage.size(); i++) {
			String item = tempItemsStorage.get(i).split(" ")[1];
			tempItemsStorage.set(i, item);
		}
	}

	/**
	 * Helper method for sortItems to reassign new ids after the items have been
	 * sorted.
	 */
	public void reassignIds() {
		int id = 1;
		for (int i = 0; i < tempItemsStorage.size(); i++) {
			String item = tempItemsStorage.get(i);
			tempItemsStorage.set(i, id + ". " + item);
			id++;
		}
	}

	/**
	 * Getter for tempItemsStorage
	 */
	public ArrayList<String> getTempItemsStorage() {
		return tempItemsStorage;
	}

	public int getNumItems() {
		return numItems;
	}
}
