package tests;

import MyList.MySortedLinkedList;
import junit.framework.TestCase;
import MyList.MySortedLinkedList;
import mediaManager.*;

import java.util.*;

/**
 * Please put your own test cases into this file, so they can be tested
 * on the server.
 */

public class StudentTests extends TestCase {
	public void testAdd(){
		MySortedLinkedList<String> vikList = new MySortedLinkedList<String>(
				String.CASE_INSENSITIVE_ORDER);
		String[] data = { "Chris", "al", "ed", "bob"};

		for (String name : data){
			vikList.add(name);
		}
		Iterator<String> iterator = vikList.iterator();

		assertTrue(iterator.hasNext());
		assertTrue(iterator.hasNext());
		assertTrue(iterator.hasNext());

		StringBuffer resultBuffer = new StringBuffer();
		while (iterator.hasNext()){
			resultBuffer.append(iterator.next() + " ");
		}

		String correct = "al bob Chris ed ";
		assertTrue(correct.equals(resultBuffer.toString()));

	}

	public void testRemove(){
		MySortedLinkedList<String> vikList = new MySortedLinkedList<String>(
				String.CASE_INSENSITIVE_ORDER);
		String[] data = { "Victor", "SlikVik" , "Vik" , "Oyefusi" };

		for (String name : data){
			vikList.add(name);}

		vikList.remove("Oyefusi");
		vikList.remove("Victor");

		StringBuffer resultBuffer = new StringBuffer();
		for (String name : vikList){
			resultBuffer.append(name + " ");}

		String correct = "SlikVik Vik ";
		assertTrue(correct.equals(resultBuffer.toString()));
	}
	public void testSize(){
		MySortedLinkedList<String> vikList = new MySortedLinkedList<String>(
				String.CASE_INSENSITIVE_ORDER);
		String[] data = { "Victor", "SlikVik" , "Vik" , "Oyefusi" };

		for (String name : data)
			vikList.add(name);

		int first = vikList.size();
		assertTrue(first == 4);
		vikList.remove("Oyefusi");
		int second = vikList.size();
		assertTrue(second == 3);

	}
	public void testIsEmpty(){
		MySortedLinkedList<String> vikList = new MySortedLinkedList<String>(
				String.CASE_INSENSITIVE_ORDER);
		assertTrue(vikList.isEmpty());
		
	}
	public void testHasNextAndNext(){
		MySortedLinkedList<String> vikList = new MySortedLinkedList<String>(
				String.CASE_INSENSITIVE_ORDER);
		String[] data = { "Jon", "Jacob", "Jingle"};

		for (String name : data){
			vikList.add(name);
		}
		Iterator<String> iterator = vikList.iterator();
		
		
		assertTrue(iterator.hasNext());
		iterator.next();
		assertTrue(iterator.hasNext());
		iterator.next();
		assertTrue(iterator.hasNext());
		iterator.next();
		assertTrue(!iterator.hasNext());
	}
public void testAddSong(){
	Manager manager = new Manager();
	String name = "Thriller", artists = "MJ", genre = "Pop", album = "Thriller";
	int secs = 300;
	manager.addSong(name, secs, artists, genre, album);
	String correct = "[Thriller, 300, MJ, Pop, 0, Thriller]";
	System.out.println(manager.getMediaFileInfo(name));
	assertTrue(manager.getMediaFileInfo(name).toString().equals(correct));
}
	
	/**
	 * Adds the specified movie to the database.  Movie/Song names are unique in the database.
	 * @param name
	 * @param durationInSeconds
	 * @param artists
	 * @param genre
	 * @param resolution
	 * @param rating
	 * @return true if the movie is added and false otherwise (e.g., the movie already exists).
	 */
	public void testAddMovie(){
		Manager manager = new Manager();
		String name = "Blow";
		String artists = "Bob";
		String genre = "Action & Adventure";
		String resolution = "HD", rating = "R";
		 int durationInSeconds = 8640;
		manager.addMovie(name, durationInSeconds, artists, genre, resolution,
				rating);
		String correct = "[Blow, 8640, Bob, Action & Adventure, 0, HD, R]";
		System.out.println(manager.getMediaFileInfo(name));
		assertTrue(manager.getMediaFileInfo(name).toString().equals(correct));
	}
	
	/**
	 * Removes the movie/song from the database.
	 * @param mediaFileName
	 * @return true if the media is removed and false otherwise (e.g., it does not exist).
	 */
	public void testRemoveMediaFile(){
		
	}
	
	/**
	 * Returns the information associated with the specified media file (song or movie). For a song
	 * the ArrayList will have name, durationInSeconds, artists, genre, playCount, album; for a movie the
	 * ArrayList will have name, durationInSeconds, artists, genre, playCount, resolution, and rating.
	 * @param mediaFileName
	 * @return ArrayList with information or null (if mediaFileName not found).
	 */
	public void testGetMediaFileInfo(){	
	ArrayList<String> getMediaFileInfo;
	
	
	}
	/**
	 * Increases by one the play count associated with the mediaFile.  No operation will take place
	 * if mediaFileName is not found.
	 * @param mediaFileName
	 */
	public void testIncreasePlayCount(){
		Manager manager = new Manager();
		String name = "Blow";
		String artists = "Bob";
		String genre = "Action & Adventure";
		String resolution = "HD", rating = "R";
		 int durationInSeconds = 8640;
		manager.addMovie(name, durationInSeconds, artists, genre, resolution,
				rating);
		manager.increasePlayCount(name);
		ArrayList<String> check = new ArrayList<String>();
		check = manager.getMediaFileInfo(name);
		check.get(4);
		Integer b = 1;
		assertTrue(check.get(4).equals(b.toString()));
	}
	
	/**
	 * Returns the play count.
	 * @param mediaFileName
	 * @return returns the play count or -1 (if media not found).
	 */
	public void testGetPlayCount(){
		Manager manager = new Manager();
		String name = "Blow";
		String artists = "Bob";
		String genre = "Action & Adventure";
		String resolution = "HD", rating = "R";
		 int durationInSeconds = 8640;
		manager.addMovie(name, durationInSeconds, artists, genre, resolution,
				rating);
		manager.increasePlayCount(name);
		ArrayList<String> check = new ArrayList<String>();
		check = manager.getMediaFileInfo(name);
		check.get(4);
		Integer b = manager.getPlayCount(name);
		assertTrue(b == 1);
	}
	 
	/* PlayList methods */
	/** Creates a playlist.
	 * @return true if the playlist has been created and false otherwise (e.g., the 
	 * playlist already exists). 
	 */
	public void testCreatePlayList(){
		Manager manager =  new Manager();
		assertTrue(manager.createPlayList("Car Songs"));
		
	}
	
	/**
	 * Removes the playlist.
	 * @param playListName
	 * @return true if the playlist has been removed and false otherwise (e.g., the 
	 * playlist does not exist).
	 */
	public void testRemovePlayList(){
		Manager manager =  new Manager();
		String car = "car";
		manager.createPlayList(car);
		assertTrue(manager.removePlayList(car));
		
	}
	
	/**
	 * Adds the mediaFileName to the playlist.  The new entry is added at the end
	 * of the current list of entries associated with the playlist.
	 * @param playListName
	 * @param mediaFileName
	 * @return true if the add operation was completed successfully and false otherwise (e.g.,
	 * playlist does not exist). 
	 */
	
}
