package parsing;

import java.util.ArrayList;

public class EnclosedContents implements ParseComponent {

	private ArrayList<ParseComponent> components = new ArrayList<ParseComponent>();
	
	public EnclosedContents(String contents) {
		components = new CodeParser(contents).getComponents();
	}
	
}
