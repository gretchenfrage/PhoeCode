package phoecode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class FileReader {

	private File fileFolder;
	private File[] files;
	
	public FileReader() {
		fileFolder = new File("files");
		fileFolder.mkdir();
		files = fileFolder.listFiles();
	}
	
	public ArrayList<String> readFiles() {
		ArrayList<String> out = new ArrayList<String>();
		for (File f : files) {
			try (FileInputStream in = new FileInputStream(f)) {
				byte[] contents = new byte[(int) f.length()];
				in.read(contents);
				out.add(new String(contents));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return out;
	}
	
}
