package tests;

import junit.framework.TestCase;
import MyList.MySortedLinkedList;
import mediaManager.*;
import java.util.*;

public class PublicTests extends TestCase {

	public void testListAdd() {
		MySortedLinkedList<String> myList = new MySortedLinkedList<String>(
				String.CASE_INSENSITIVE_ORDER);
		String[] data = { "John", "Albert", "Mary", "Bob", "Tom", "Robert",
				"Laura" };

		for (String name : data)
			myList.add(name);

//		for(String t : myList){
//			System.out.println(t);
//		}
		
		Iterator<String> iterator = myList.iterator();

		/* Multiple hasNext() */
		iterator.hasNext();
		iterator.hasNext();
		iterator.hasNext(); 
		

		StringBuffer resultBuffer = new StringBuffer();
		while (iterator.hasNext())
			resultBuffer.append(iterator.next() + "\n");
//		System.out.println(resultBuffer);

		assertTrue(TestingSupport.correctResults("pubTestListAdd.txt",
				resultBuffer.toString()));
	}

	public void testListRemove() {
		MySortedLinkedList<String> myList = new MySortedLinkedList<String>(
				String.CASE_INSENSITIVE_ORDER);
		String[] data = { "John", "Albert", "Mary", "Bob", "Tom", "Robert",
				"Laura" };

		for (String name : data)
			myList.add(name);

		myList.remove("Albert");
		myList.remove("Tom");
		myList.remove("John");

		StringBuffer resultBuffer = new StringBuffer();
		for (String name : myList)
			resultBuffer.append(name + "\n");

		assertTrue(TestingSupport.correctResults("pubTestListRemove.txt",
				resultBuffer.toString()));
	}

	public void testListGetSize() {
		MySortedLinkedList<String> myList = new MySortedLinkedList<String>(
				String.CASE_INSENSITIVE_ORDER);
		String[] data = { "John", "Albert", "Mary", "Bob", "Tom", "Robert",
				"Laura" };

		for (String name : data)
			myList.add(name);

		StringBuffer resultBuffer = new StringBuffer();
		resultBuffer.append("Size: " + myList.size());
		resultBuffer.append("\nFirst Element: " + myList.get(0));
		resultBuffer.append("\nLast Element: " + myList.get(myList.size() - 1));
		resultBuffer.append("\nIsEmpty: " + myList.isEmpty());

		assertTrue(TestingSupport.correctResults("pubTestListGetSize.txt",
				resultBuffer.toString()));
	}

	public void testManagerAdd() {
		Manager manager = new Manager();

		/* Adding a song */
		String name = "Do you remember", artists = "Phil Collins", genre = "Pop", album = "But Seriously";
		int durationInSeconds = 276;
		manager.addSong(name, durationInSeconds, artists, genre, album);
		manager.increasePlayCount("Do you remember");
		manager.increasePlayCount("Do you remember");
		manager.increasePlayCount("Do you remember");

		/* Adding a movie */
		name = "Casino Royale";
		artists = "Daniel Craig & Mads Mikkelsen";
		genre = "Action & Adventure";
		String resolution = "HD", rating = "PG-13";
		durationInSeconds = 8640;
		manager.addMovie(name, durationInSeconds, artists, genre, resolution,
				rating);
		manager.increasePlayCount("Casino Royale");
		manager.increasePlayCount("Casino Royale");


		StringBuffer resultBuffer = new StringBuffer();
		resultBuffer.append(manager.getMediaFileInfo("Do you remember"));
		resultBuffer.append(manager.getMediaFileInfo("Casino Royale"));

		assertTrue(TestingSupport.correctResults("pubTestManagerAdd.txt",
				resultBuffer.toString()));
	}

	public void testPlayLists() {
		StringBuffer resultBuffer = new StringBuffer();
		Manager manager = loadData();
		manager.createPlayList("SummerList");
		manager.createPlayList("Favorites");
		manager.addMediaToPlayList("Favorites", "Piano Man");
		manager.addMediaToPlayList("Favorites", "Dancing Queen");
		manager.addMediaToPlayList("Favorites", "Invincible");
		manager.addMediaToPlayList("SummerList", "Gladiator");
		resultBuffer.append("\n**** Favorites playlist");
		resultBuffer.append(manager.getPlayList("Favorites"));
		resultBuffer.append("\n**** All PlayLists");
		resultBuffer.append(new TreeSet<String>(manager.getAllPlayLists()));
		manager.removeMediaFromPlayList("Favorites", "Dancing Queen");
		resultBuffer.append("\n**** After removal");
		resultBuffer.append(manager.getPlayList("Favorites"));

		assertTrue(TestingSupport.correctResults("pubTestPlayLists.txt",
				resultBuffer.toString()));
	}

	public void testSearch() {
		Manager manager = loadData();

		StringBuffer resultBuffer = new StringBuffer();
		MySortedLinkedList<String> myList = manager.getAllMediaFileNames();
		resultBuffer.append("**** All Media Files Info\n");
		for (String name : myList)
			resultBuffer.append(manager.getMediaFileInfo(name) + "\n");

		resultBuffer.append("**** All Songs Names\n");
		myList = manager.getAllSongNames();
		for (String name : myList)
			resultBuffer.append(name + "\n");

		resultBuffer.append("**** All Movies Names\n");
		myList = manager.getAllMovieNames();
		for (String name : myList)
			resultBuffer.append(name + "\n");

		resultBuffer.append("**** Country Songs\n");
		myList = manager.getMediaFileNamesWith(-1, null, "Country", null, null,
				null);
		for (String name : myList)
			resultBuffer.append(name + "\n");

		resultBuffer.append("**** HD PG Movies\n");
		myList = manager.getMediaFileNamesWith(-1, null, null, null, "HD", "PG");
		for (String name : myList)
			resultBuffer.append(name + "\n");

		resultBuffer.append("**** PlayCount Retrieval \n");
		manager.increasePlayCount("How Do I Live");
		manager.increasePlayCount("The Wrestler");
		manager.increasePlayCount("Islands in the Stream");
		manager.increasePlayCount("Invincible");
		manager.increasePlayCount("The Wrestler");
		manager.increasePlayCount("Islands in the Stream");
		manager.increasePlayCount("The Wrestler");
		manager.increasePlayCount("Islands in the Stream");
		manager.increasePlayCount("Invincible");
		manager.increasePlayCount("The Wrestler");

		ArrayList<String> list = manager.getAllMediaFilesNamesByPlayCount();
		for (String name : list)
			resultBuffer.append(name + "\n");

		resultBuffer.append("**** Retrieval by genre\n");
		TreeMap<String, TreeSet<String>> map = manager
				.getAllMediaFilesNamesByGenre();
		for (String genre : map.keySet()) {
			resultBuffer.append("Genre: " + genre + "\n");
			resultBuffer.append(map.get(genre) + "\n\n");
		}

		assertTrue(TestingSupport.correctResults("pubTestSearch.txt",
				resultBuffer.toString()));
	}

	private static Manager loadData() {
		Manager manager = new Manager();

		// Adding Songs
		manager.addSong("Dancing Queen", 212, "ABBA", "Pop", "ABBA Gold");
		manager.addSong("Islands in the Stream", 250,
				"Dolly Parton & Kenny Rogers", "Country",
				"Dolly Parton: Greatest Hits");
		manager.addSong("How Do I Live", 267, "LeAnn Rimes", "Country",
				"LeAnn Rimes: Greatest Hits");
		manager.addSong("I'll Be There", 238, "Jackson 5", "R&B/Soul",
				"Jackson 5: The Ultimate Collection");
		manager.addSong("What's Going On", 233, "Marvin Gaye", "R&B/Soul",
				"What's Going On");
		manager
				.addSong("I Believe I Can Fly", 321, "R.Kelly", "R&B/Soul",
						"R.");
		manager.addSong("How Will I Know", 273, "Whitney Houston", "R&B/Soul",
				"Whitney Houston");
		manager.addSong("I Don't Want to Miss a Thing", 273, "Aerosmith",
				"Rock", "Rockin");
		manager.addSong("Piano Man", 336, "Billy Joel", "Rock",
				"The Essential Billy Joel");

		// Adding Movies
		manager.addMovie("X2: X-Men United", 7920,
				"Hugh Jackman & Patrick Stewart", "Action & Adventure", "HD",
				"PG-13");
		manager.addMovie("Gladiator", 9300, "Russell Crowe",
				"Action & Adventure", "DVD", "R");
		manager.addMovie("The Notebook", 7380, "Ryan Gosling & Rachel McAdams",
				"Romance", "HD", "PG-13");
		manager.addMovie("When Harry Met Sally", 5760,
				"Billy Crystal & Meg Ryan", "Romance", "DVD", "R");
		manager.addMovie("Invincible", 6240, "Mark Wahlberg", "Drama", "HD",
				"PG");
		manager.addMovie("We Are Marshall", 7860, "Matthew McConaughey",
				"Drama", "HD", "PG");
		manager.addMovie("The Wrestler", 6300, "Mickey Rourke", "Drama", "HD",
				"R");

		return manager;
	}
}