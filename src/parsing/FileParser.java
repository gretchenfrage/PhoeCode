package parsing;

import java.util.ArrayList;

public class FileParser {

	private ArrayList<ParseComponent> components = new ArrayList<ParseComponent>();
	private char[] contents;
	private int pointer = 0;
	
	public FileParser(String contentsIn) {
		contents = contentsIn.toCharArray();
		parseContents();
		System.out.println(components);
	}
	
	/*
	 * returns if is the start of the following kinds of components
	 */
	private boolean enclosureStart() {
		return "<({[".indexOf(contents[pointer]) != -1;
	}
	
	private boolean stringLiteralStart() {
		return contents[pointer] == '"';
	}
	
	private boolean dotStart() {
		return contents[pointer] == '.';
	}
	
	private boolean numericLiteralStart() {
		return Character.isDigit(contents[pointer]);
	}
	
	private boolean tokenStart() {
		return Character.isAlphabetic(contents[pointer]);
	}
	
	private boolean whitespaceStart() {
		return Character.isWhitespace(contents[pointer]) || contents[pointer] == ';';
	}
	
	/*
	 * parses the following kinds of components after it is verified that it is started
	 */
	private void parseEnclosure() {
		char enter = contents[pointer];
		char exit = 0;
		switch (enter) {
		case '<':
			exit = '>';
			break;
		case '(':
			exit = ')';
			break;
		case '{':
			exit = '}';
			break;
		case '[':
			exit = ']';
			break;
		}
		
		String enclosedContents = "";
		
		pointer++;
		int enclosedness = 1;
		while (enclosedness > 0) {
			enclosedContents += contents[pointer];
			if (contents[pointer] == enter) {
				enclosedness++;
			} else if (contents[pointer] == exit) {
				enclosedness--;
			}
			pointer++;
		}
		pointer++;
		
		components.add(new EnclosedContents(enclosedContents));
	}
	
	private void parseStringLiteral() {
		String literalContents = "";
		pointer++;
		while (!(contents[pointer] == '"' && contents[pointer - 1] != '\\')) {
			literalContents += contents[pointer];
			pointer++;
		}
		pointer++;
		components.add(new StringLiteral(literalContents));
	}

	private void parseDot() {
		pointer++;
		components.add(new Dot());
	}
	
	private void parseNumericLiteral() {
		String literalContents = "";
		while (Character.isDigit(contents[pointer])) {
			literalContents += contents[pointer];
			pointer++;
		}
		components.add(new NumericLiteral(literalContents));
	}
	
	private void parseToken() {
		String tokenContents = "";
		while (!(Character.isAlphabetic(contents[pointer]) || Character.isDigit(contents[pointer]))) {
			tokenContents += contents[pointer];
			pointer++;
		}
		components.add(new Token(tokenContents));
	}
	
	private void parseWhitespace() {
		while (Character.isWhitespace(contents[pointer])) {
			pointer++;
		}
		components.add(new Whitespace());
	}
	
	private void parseSymbol() {
		String symbolContents = "";
		while (!(enclosureStart() || stringLiteralStart() || dotStart() || numericLiteralStart() || tokenStart() || whitespaceStart())) {
			symbolContents += contents[pointer];
			pointer++;
		}
		components.add(new Symbol(symbolContents));
	}
	
	/*
	 * converts the char[] contents to the ArrayList<ParseComponent> components
	 */
	private void parseContents() {
		if (enclosureStart()) {
			parseEnclosure();
		} else if (stringLiteralStart()) {
			parseStringLiteral();
		} else if (dotStart()) {
			parseDot();
		} else if (numericLiteralStart()) {
			parseNumericLiteral();
		} else if (tokenStart()) {
			parseToken();
		} else if (whitespaceStart()) {
			parseWhitespace();
		} else {
			parseSymbol();
		}
	}
	
}
