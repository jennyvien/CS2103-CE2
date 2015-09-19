import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * TextBuddyFileManager is in charge writing results out to the output file.
 * @author Jenny
 *
 */
public class TextBuddyFileManager {
	
	
	private String outputFileName;
	
	public TextBuddyFileManager() { }
	
	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	public void updateOutputFile(ArrayList<String> tempItemsStorage) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(this.outputFileName));
			
			if(tempItemsStorage.isEmpty()) {
				writer.append("");
			} else {
				for(int i = 0; i < tempItemsStorage.size(); i++) {
					writer.append(tempItemsStorage.get(i));
					writer.newLine();
				}
			}
			
			writer.close();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	} 
}	