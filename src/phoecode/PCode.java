package phoecode;

import parsing.CodeParser;

public class PCode {
	
	public static void main(String[] args) {
		for (String s : new FileReader().readFiles()) {
			new CodeParser(s);
		}
	}

}
