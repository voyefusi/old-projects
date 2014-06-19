package tests;
import imageFinder.Utilities;
import imageFinder.processImage;

import java.util.*;

import junit.framework.TestCase;

public class studentTests extends TestCase{
	public void testProcessImage() {
//		Set<String> urls = new TreeSet<String>();
//		urls.add("http://www.cs.umd.edu/class/summer2010/cmsc132/homeworks/ImageFinder/documents/Set1/");
		String pic = "http://www.cs.umd.edu/class/summer2010/cmsc132/homeworks/ImageFinder/documents/Set1/";
		processImage image = new processImage(pic);
		image.run();
	}
	
}