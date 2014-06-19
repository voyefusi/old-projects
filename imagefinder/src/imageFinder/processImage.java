package imageFinder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class processImage implements Runnable {
	String link;
	StringBuffer buff;
	static Set<String> allURLs;
	public processImage(String link){
		this.link = link;
		this.buff = new StringBuffer();
		allURLs = new TreeSet<String>();
	}

	public void run(){

		URL url = null;
		try {
			url = new URL(link);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		URLConnection connection = null;
		try {
			connection = url.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(connection
					.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String inputLine;

		try {
			while ((inputLine = reader.readLine()) != null){
				buff.append(inputLine);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//System.out.println(buff.toString());
		getImageURLs();
	}
	public void getImageURLs(){
		String content = buff.toString();
		System.out.println(content);
		for(int i = 0; i < content.length(); i++){

			if(content.charAt(i)==('s')){
				if(content.charAt(i+1)==('r')){
					if(content.charAt(i+2)==('c')){
						int j = i+2;
						while(content.charAt(j)!=('"')){
							j++;
						}
						int begin = j+1;
						j++;
						while(content.charAt(j)!=('"')){
							j++;
						}
						int end = j;
						i=j;
						//						System.out.println(content.substring(begin, end));
						String str = content.substring(begin, end);
						if(!str.startsWith("http")){
							str = link + str;
						}
						//System.out.println(str);
						allURLs.add(str);
					}
				}
			}
		}

	}

	public static Set<String> getImageSet(){
		synchronized(allURLs){
			return allURLs;
		}
	}
}
