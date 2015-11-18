package parsing;

public class StringLiteral implements ParseComponent {
	
	private String contents;
	
	public StringLiteral(String contentsIn) {
		contents = contentsIn;
	}
	
	public String toString() {
		return "string literal: " + contents;
	}

}
