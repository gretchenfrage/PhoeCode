package parsing;

import java.util.ArrayList;

public class CodeParser {

	public CodeParser(String contentsIn) {
		System.out.println(contentsIn);
		chars = contentsIn.toCharArray();
		try {
			parse();
		} catch (InvalidSyntaxException e) {
			System.out.println("Invalid Syntax Exception!");
		}
		System.out.println(contents);
	}
	
	private char[] chars;
	private int index = 0;
	private ArrayList<ParseComponent> contents = new ArrayList<ParseComponent>();
	
	/*
	 * converts char array to contents arraylist
	 */
	private void parse() throws InvalidSyntaxException {
		while (charsRemaining()) {
			if (dot()) {
				parseDot();
			} else if (enclosedStarter()) {
				parseEnclosedStarter();
			} else if (enclosedStopper()) {
				parseEnclosedStopper();
			} else if (numericLiteralStart()) {
				parseNumericLiteral();
			} else if (stringLiteralStart()) {
				System.out.println("string literal detected");
				parseStringLiteral();
			} else if (tokenStart()) {
				parseToken();
			} else if (whitespaceStart()) {
				parseWhitespace();
			} else if (nextLine()) {
				parseNextLine();
			} else {
				parseSymbol();
			}
		}
	}
	
	/*
	 * returns if calling chars and index would not result in an ArrayIndexOutOfBoundsException
	 */
	private boolean charsRemaining() {
		return index < chars.length;
	}
	
	/*
	 * returns if the inputted char is some acceptable form of quotation marks
	 */
	private boolean quotationMarks(char c) {
		return c == '"' || c == '“' || c == '”';
	}
	
	/*
	 * returns if index is at the start/entirety of the following components
	 */
	private boolean dot() {
		return chars[index] == '.';
	}
	
	private boolean enclosedStarter() {
		return "{[(<".indexOf(chars[index]) != -1;
	}
	
	private boolean enclosedStopper() {
		return "}])>".indexOf(chars[index]) != -1;
	}
	
	private boolean numericLiteralStart() {
		return Character.isDigit(chars[index]);
	}
	
	private boolean stringLiteralStart() {
		return quotationMarks(chars[index]);
	}
	
	private boolean tokenStart() {
		return Character.isAlphabetic(chars[index]);
	}
	
	private boolean whitespaceStart() {
		return Character.isWhitespace(chars[index]);
	}
	
	private boolean nextLine() {
		return chars[index] == ';';
	}
	
	/*
	 * assuming is confirmed to be the start of the following components, parses them
	 */
	private void parseDot() {
		contents.add(new Dot());
		index++;
	}
	
	private void parseEnclosedStarter() {
		contents.add(new EnclosedStarter(chars[index]));
		index++;
	}
	
	private void parseEnclosedStopper() {
		contents.add(new EnclosedStopper(chars[index]));
		index++;
	}
	
	private void parseNumericLiteral() {
		String collected = "";
		while (Character.isDigit(chars[index]) && charsRemaining()) {
			collected += chars[index];
			index++;
		}
		contents.add(new NumericLiteral(collected));
	}
	
	private void parseStringLiteral() throws UnclosedStringLiteralException {
		String collected = "";
		index++;
		while (!quotationMarks(chars[index])) {
			if (charsRemaining()) {
				collected += chars[index];
				index++;
			} else {
				throw new UnclosedStringLiteralException();
			}
		}
		index++;
		contents.add(new StringLiteral(collected));
	}
	
	private void parseToken() {
		String collected = "";
		while (Character.isAlphabetic(chars[index]) && charsRemaining()) {
			collected += chars[index];
			index++;
		}
		contents.add(new Token(collected));
	}
	
	private void parseWhitespace() {
		while (Character.isWhitespace(chars[index]) && charsRemaining()) {
			index++;
		}
		contents.add(new Whitespace());
	}

	private void parseNextLine() {
		contents.add(new NextLine());
		index++;
	}
	
	private void parseSymbol() {
		String collected = "";
		while (!(dot() || enclosedStarter() || enclosedStopper() || numericLiteralStart() ||
				stringLiteralStart() ||tokenStart() || whitespaceStart() || nextLine()) && charsRemaining()) {
			collected += chars[index];
			index++;
		}
		contents.add(new Symbol(collected));
	}
	
}
