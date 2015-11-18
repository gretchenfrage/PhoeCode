package parsing;

public class Symbol implements ParseComponent {
	
	private String contents;
	
	public Symbol(String contentsIn) {
		contents = contentsIn;
	}
	
	public String toString() {
		return "symbol: " + contents;
	}

}
