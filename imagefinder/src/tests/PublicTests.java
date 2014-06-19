package tests;
import imageFinder.Utilities;
import java.util.*;

import junit.framework.TestCase;

public class PublicTests extends TestCase{
	public void testOne() {
		Set<String> urls = new TreeSet<String>();
		urls.add("http://www.cs.umd.edu/class/summer2010/cmsc132/homeworks/ImageFinder/documents/Set1/");
		
		Set<String> images = Utilities.findImages(urls);
		
		/* Using sorted set to get urls in sorted order */
		TreeSet<String> sortedSet = new TreeSet<String>(images);
		StringBuffer result = new StringBuffer();
		result.append("Images in url(s): ");
		result.append(urls.toString());
		for (String entry : sortedSet)
			result.append("\n" + entry);
		System.out.println(result.toString());
		assertTrue(TestingSupport.correctResults("pubOne.txt", result.toString()));
	}
	
	public void testTwo() {
		Set<String> urls = new TreeSet<String>();
		urls.add("http://www.cs.umd.edu/class/summer2010/cmsc132/homeworks/ImageFinder/documents/Set2/");
		urls.add("http://www.cs.umd.edu/class/summer2010/cmsc132/homeworks/ImageFinder/documents/Set1/");
		
		Set<String> images = Utilities.findImages(urls);
		
		/* Using sorted set to get urls in sorted order */
		TreeSet<String> sortedSet = new TreeSet<String>(images);
		StringBuffer result = new StringBuffer();
		result.append("Images in url(s): ");
		result.append(urls.toString());
		for (String entry : sortedSet)
			result.append("\n" + entry);
		assertTrue(TestingSupport.correctResults("pubTwo.txt", result.toString()));
	}
	
	public void testThree() {
		Set<String> urls = new TreeSet<String>();
		urls.add("http://www.cs.umd.edu/class/summer2010/cmsc132/homeworks/ImageFinder/documents/Set3/");
		urls.add("http://www.cs.umd.edu/class/summer2010/cmsc132/homeworks/ImageFinder/documents/Set2/");
		
		Set<String> images = Utilities.findImages(urls);
		
		/* Using sorted set to get urls in sorted order */
		TreeSet<String> sortedSet = new TreeSet<String>(images);
		StringBuffer result = new StringBuffer();
		result.append("Images in urls: ");
		result.append(urls.toString());
		for (String entry : sortedSet)
			result.append("\n" + entry);
		assertTrue(TestingSupport.correctResults("pubThree.txt", result.toString()));
	}
}