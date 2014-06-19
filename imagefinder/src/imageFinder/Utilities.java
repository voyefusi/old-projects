package imageFinder;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


public class Utilities {


	//class processURL
	//reads cntents of web. huge string buffer. analyze it get images and put it into a set. 
	/**URL url = new URL(args.length == 0 ? "http://www.yahoo.com/" : args[0]);
		URLConnection connection = url.openConnection();
		System.out.println("Type : " + connection.getContentType());
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection
				.getInputStream()));

		String inputLine;

		while ((inputLine = reader.readLine()) != null)
			System.out.println(inputLine);

		reader.close();
		*/
	
	public static Set<String> findImages(Set<String> urls) {
		//for each url create new thread. each new thread searches through url to find image urls
		ArrayList<Thread> set = new ArrayList<Thread>();
		Iterator<String> it = urls.iterator();
		while(it.hasNext()){
			processImage pros = new processImage(it.next());
			Thread ted = new Thread(pros);
			set.add(ted);

		}
		for(Thread thread : set){
			thread.start();
			
		}
		for(Thread thread : set){
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return processImage.getImageSet();
	}
	/**For this homework you will implement a system that allow us to find urls of images present in a collection of web sites.
	In order to find these images we will use the static method Utilities.findImages
	(see code distribution) which takes a set of web sites and returns
	a set of urls that corresponds to images found (if any) in the specified sites.

	To recognize images, your system will search through the html code of the specified web page,
	looking for entries starting with "<img src=" where any number of spaces and options (e.g., border)
	may exist in between img and src. An image is represented by the string following "src=".
	The following is a representative example of one possible entry you will be searching for:   

	<img src="http://www.cs.umd.edu/class/summer2010/cmsc132/homeworks/ImageFinder/documents/Set1/Set1a.jpg" />

	The findImages method will return complete urls of images found.
	A complete url is defined as one that starts with "http://" and which provides the exact location of the image
	in such a way that we can cut and past the url in a browser and actually see the image.
	For this project you don't have to worry about sites that may use uppercase letters for img or src in the html code.
*/
}