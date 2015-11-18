package parsing;

public class Token implements ParseComponent {
	
	private String contents;
	
	public Token(String contentsIn) {
		contents = contentsIn;
	}
	
	public String toString() {
		return "token: " + contents;
	}

}
