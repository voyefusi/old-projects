package calculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utilities {
	public static String getFileContents(String filename) {
		StringBuffer stringBuffer = new StringBuffer();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(filename));
			String line = bufferedReader.readLine();
			boolean firstTime = true;
			while (line != null) {
				if (!firstTime)
					stringBuffer.append("\n");
				stringBuffer.append(line);
				firstTime = false;

				line = bufferedReader.readLine();
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return stringBuffer.toString();
	}
}
