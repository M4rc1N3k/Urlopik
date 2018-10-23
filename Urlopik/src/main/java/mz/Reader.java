package mz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
	
	public static String dataFromFile(String path) {
	try(BufferedReader br = new BufferedReader(new FileReader(path))){
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();
		
		while (line!=null) {
			sb.append(line);
			sb.append(System.lineSeparator());
			line = br.readLine();
		}
		
		String all = sb.toString();
		return all;
	}
	catch (FileNotFoundException e) {
	    e.printStackTrace();
	    return null;
	} catch (IOException e) {
	    e.printStackTrace();
	    return null;
	}
	
	}
}
