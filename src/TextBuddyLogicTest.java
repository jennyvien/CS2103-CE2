import static org.junit.Assert.*;

import org.junit.Test;

public class TextBuddyLogicTest {	
	/**
	 * Test to see if testExecuteUserCommand returns proper feedback messages
	 */
	@Test
	public void testExecuteUserCommand() {
		TextBuddyLogic tb = new TextBuddyLogic();
		
		Feedback addResult1 = tb.addItem("hello");
		assertEquals(String.format(addResult1.getMessage(), "mytextfile.txt", addResult1.getMessageArguments()), 
				"added to mytextfile.txt: \"hello\"");
		
		Feedback addResult2 = tb.addItem("");
		assertEquals(addResult2.getMessage(), "Please enter an argument.");
		
		Feedback executeInvalidCommandResult = tb.executeUserCommand(new Command("apple", "banana"));
		assertEquals(executeInvalidCommandResult.getMessage(), "Please enter a valid command.");
		
		Feedback clearResult = tb.clearFileContent();
		assertEquals(String.format(clearResult.getMessage(), "mytextfile.txt", clearResult.getMessageArguments()), "all content deleted from mytextfile.txt");
		
		Feedback displayResult1 = tb.displayItems();
		assertEquals(String.format(displayResult1.getMessage(), "mytextfile.txt", displayResult1.getMessageArguments()), "mytextfile.txt is empty");
		
		tb.addItem("hello");
		Feedback deleteResult = tb.deleteItem(1);
		assertEquals(String.format(deleteResult.getMessage(), "mytextfile.txt", deleteResult.getMessageArguments()), "deleted from mytextfile.txt: \"hello\"");
		
		Feedback displayResult2 = tb.displayItems();
		assertEquals(String.format(displayResult2.getMessage(), "mytextfile.txt", displayResult2.getMessageArguments()), "mytextfile.txt is empty");
	}

	@Test
	public void testAddItem() {
		TextBuddyLogic tb = new TextBuddyLogic();
		tb.addItem("a0");
		tb.addItem("a1");
		tb.addItem("a2");
		tb.addItem("a3");
		
		for(int i = 0; i < tb.getTempItemsStorage().size(); i++) {
			int id = i + 1;
			String actualOutput = id + ". " + "a" + i;
			assertEquals(tb.getTempItemsStorage().get(i), actualOutput);
		}
	}

	@Test
	public void testDeleteItem() {
		TextBuddyLogic tb = new TextBuddyLogic();
		tb.addItem("a0");
		tb.addItem("a1");
		tb.addItem("a2");
		tb.addItem("a3");
		
		System.out.println("hello");
		tb.deleteItem(2);
		assertEquals(tb.getTempItemsStorage().get(0), "1. a0");
		assertEquals(tb.getTempItemsStorage().get(1), "2. a2");
		assertEquals(tb.getTempItemsStorage().get(2), "3. a3");
		
		tb.deleteItem(3);
		assertEquals(tb.getTempItemsStorage().get(0), "1. a0");
		assertEquals(tb.getTempItemsStorage().get(1), "2. a2");
		
		tb.deleteItem(1);
		assertEquals(tb.getTempItemsStorage().get(0), "1. a2");
		
		tb.deleteItem(1);
		
		Feedback deleteWhenFileEmptyResult = tb.deleteItem(1);
		assertEquals(String.format(deleteWhenFileEmptyResult.getMessage(), "mytextfile.txt", deleteWhenFileEmptyResult.getMessageArguments()), "mytextfile.txt is empty. No item to delete.");
		
		Feedback displayResult2 = tb.displayItems();
		assertEquals(String.format(displayResult2.getMessage(), "mytextfile.txt", displayResult2.getMessageArguments()), "mytextfile.txt is empty");
		
	}


	//@Test
	public void testClearFileContent() {
		TextBuddyLogic tb = new TextBuddyLogic();
		tb.addItem("a0");
		tb.addItem("a1");
		tb.addItem("a2");
		tb.addItem("a3");
		
		tb.clearFileContent();
		assertEquals(tb.getNumItems(), 0);
		
		Feedback clearResult = tb.clearFileContent();
		assertEquals(String.format(clearResult.getMessage(), "mytextfile.txt", clearResult.getMessageArguments()), "all content deleted from mytextfile.txt");

	}

	//@Test
	public void testPrintItems() {
		fail("Not yet implemented");
	}

}
