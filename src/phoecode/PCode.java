package phoecode;

import parsing.FileParser;

public class PCode {
	
	public static void main(String[] args) {
		for (String s : new FileReader().readFiles()) {
			new FileParser(s);
		}
	}

}
