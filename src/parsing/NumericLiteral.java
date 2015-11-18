package parsing;

public class NumericLiteral implements ParseComponent {

	private String contents;
	
	public NumericLiteral(String contentsIn) {
		contents = contentsIn;
	}
	
	public String toString() {
		return "numeric literal: " + contents;
	}
	
}
