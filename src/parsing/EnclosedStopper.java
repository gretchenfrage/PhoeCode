package parsing;

public class EnclosedStopper implements ParseComponent {
	
	private char content;
	
	public EnclosedStopper(char contentIn) {
		content = contentIn;
	}
	
	public String toString() {
		return "enclosed stopper: " + content;
	}

}
