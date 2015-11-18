package parsing;

public class EnclosedStarter implements ParseComponent {
	
	private char content;
	
	public EnclosedStarter(char contentIn) {
		content = contentIn;
	}
	
	public String toString() {
		return "enclosed starter: " + content;
	}

}
