package mediaManager;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import MyList.MySortedLinkedList;

public class Manager implements MediaManager{

	Map<String,ArrayList<String>> playlists;
	TreeMap<String,ArrayList<String>> song;
	TreeMap<String,ArrayList<String>> movie; 
	TreeMap<Integer,ArrayList<String>> playCountTree; 

	StringBuffer playcount;
	public Manager(){
		playlists = new HashMap<String,ArrayList<String>>();
		song = new TreeMap<String,ArrayList<String>>();
		movie = new TreeMap<String,ArrayList<String>>();
		playcount = new StringBuffer("0");
	}
	public boolean addMediaToPlayList(String playListName, String mediaFileName) {
		/**NEED J-UNIT*/
		if(playlists.containsKey(playListName)){
			ArrayList<String> that = playlists.get(playListName);
			that.add(mediaFileName);
			if(that.contains(mediaFileName)){
				return false;
			}else{
				playlists.put(playListName, that);
				return true;
			}
		}else{
			return false;
		}
	}


	public boolean addMovie(String name, int durationInSeconds, String artists,
			String genre, String resolution, String rating) {
		/**NEED J-UNIT*/
		/**
		 * Adds the specified movie to the database.  Movie/Song names are unique in the database.
		 * @return true if the movie is added and false otherwise (e.g., the movie already exists).
		 */
		if (movie.containsKey(name)){
			return false;
		}else{
			TreeMap<String,ArrayList<String>> movieData = new TreeMap<String,ArrayList<String>>();
			ArrayList<String> movieInfo = new ArrayList<String>();
			Integer durationINSecs = durationInSeconds;
			String durationInSecs = durationINSecs.toString();
			movieInfo.add(0, name);
			movieInfo.add(1, durationInSecs);
			movieInfo.add(2, artists);
			movieInfo.add(3, genre);
			movieInfo.add(4, playcount.toString()); 
			movieInfo.add(5, resolution);
			movieInfo.add(6, rating); 


			movieData.put(name, movieInfo);
			movie = movieData;
			return true;
		}		
	}
	public boolean addSong(String name, int durationInSeconds, String artists,
			/**NEED J-UNIT*/
			String genre, String album) {
		if (song.containsKey(name)){
			return false;
		}else{
			if(song == null){
				TreeMap<String,ArrayList<String>> newSong = new TreeMap<String,ArrayList<String>>();
				song = newSong;
			}
			ArrayList<String> songInfo = new ArrayList<String>();
			Integer durationINSecs = durationInSeconds;
			String durationInSecs = durationINSecs.toString();

			String playCount = playcount.toString();

			songInfo.add(0, name);
			songInfo.add(1, durationInSecs);
			songInfo.add(2, artists);
			songInfo.add(3, genre);
			songInfo.add(4, playCount);
			songInfo.add(5, album); 


			song.put(name, songInfo);

			return true;
		}
	}

	public boolean createPlayList(String playListName) {
		/**NEED J-UNIT*/
		if(playlists.containsKey(playListName)){
			return false;
		}else{
			ArrayList<String> playListData = new ArrayList<String>();
			playlists.put(playListName, playListData);
			return true;
		}
	}


	public MySortedLinkedList<String> getAllMediaFileNames() {
		/**NEED J-UNIT + ADD Movies*/ 
		ArrayList<String> resultArray = new ArrayList<String>();
		MySortedLinkedList<String> temp = new MySortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);;
		Collection<ArrayList<String>> every = song.values();
		Collection<ArrayList<String>> every2 = movie.values(); 
		
		Iterator<ArrayList<String>> everything = every.iterator();
		Set<String> sets1 = new HashSet<String>(everything.next());
		Set <String> sets2 = null; 
		while (everything.hasNext ()){
			sets2 = new HashSet <String> (everything.next ());
			sets2.retainAll(sets1);
			if (sets2.isEmpty ()){
				sets2 = sets1;
			}
		}  
		
		Iterator<ArrayList<String>> everything2 = every2.iterator();
		Set<String> sets3 = new HashSet<String>(everything2.next());
		Set <String> sets4 = null; 
		while (everything2.hasNext ()){
			sets4 = new HashSet <String> (everything2.next ());
			sets4.retainAll(sets1);
			if (sets4.isEmpty ()){
				sets4 = sets3; 
			}
		}  

		resultArray.addAll(sets2);
		resultArray.addAll(sets4);
		for(int i = 0; i < resultArray.size(); i++){
			temp.add(resultArray.get(i)); 
		}
		return temp;
	}

	public TreeMap<String, TreeSet<String>> getAllMediaFilesNamesByGenre() {
		/** Needs J-UNIT*/
		TreeMap<String, TreeSet<String>> temp = new TreeMap<String, TreeSet<String>>();
		TreeSet<String> namesTree = new TreeSet<String>();
		ArrayList<String> that = new ArrayList<String>();
		ArrayList<String> link = new ArrayList<String>();
		String names;
		String genres;
		Set<String> ss = song.keySet();
		Set<String> mm = movie.keySet();
		Set<String> all = ss;
		all.addAll(mm);
		that.addAll(all);

		for(int i = 0; i < that.size(); i++){
			names = that.get(i);
			if(song.containsKey(names)){
				link = song.get(names);
				genres = link.get(3);
				namesTree.add(names);
				temp.put(genres, namesTree);
			}else if(movie.containsKey(names)){
				link = movie.get(names);
				genres = link.get(3);
				namesTree.add(names);
				temp.put(genres, namesTree);
			}	
		}
		return temp;
	}

	public ArrayList<String> getAllMediaFilesNamesByPlayCount() {
		/**
		 * 	/**
		 * Returns an ArrayList sorted by the play count (entries with a higher play count
		 * will appear first in the list). If two files have the same count, then
		 * use the media file name to sort the media files.
		 * @return sorted linked list sorted by play count.
		 */

		ArrayList<String> that = new ArrayList<String>();
		ArrayList<String> nameGetter = new ArrayList<String>();
		Set<String> ss = song.keySet();
		Set<String> mm = movie.keySet();
		Set<String> all = ss;
		all.addAll(mm);
		that.addAll(all);
		String names = "";
		String namesChecker = "";
		int playCount = 0;
		int playCountChecker = 0;

		for(int i = 0; i < that.size(); i++){
			names = that.get(i);
			playCount = getPlayCount(names);
			for(int j = 0; j < that.size(); j++){
				namesChecker = that.get(j);
				playCountChecker = getPlayCount(namesChecker);
				if(playCount == playCountChecker && playCount != -1){
					if(playCountTree.containsKey(playCount)&& !nameGetter.contains(namesChecker)){
						nameGetter.add(namesChecker);
						playCountTree.put(playCount, nameGetter);
					}
				}
			}
		}
		/**Unsure how to add Tree in order */
		Set<Integer> tree = playCountTree.keySet();
		Iterator<Integer> it = tree.iterator();
		ArrayList<String>  b= new ArrayList<String>();
		ArrayList<String>  c= new ArrayList<String>();
		String a;
		while(it.hasNext()){
			b = playCountTree.get(it.next());	
		}
		//		
		//		playCountComparator comparator;
		//Define comparator
		return b;
	}

	public MySortedLinkedList<String> getAllMovieNames() {
		/** Needs J-UNIT*/
		MySortedLinkedList<String> gettinMovies = new MySortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		Set<String> movieNames = movie.keySet();
		Iterator<String> iter = movieNames.iterator();

		while(iter.hasNext()){
			gettinMovies.add((String) iter.next());
		}
		return gettinMovies;
	}

	public Set<String> getAllPlayLists() {
		/** Needs J-UNIT*/
		return playlists.keySet();
	}

	public MySortedLinkedList<String> getAllSongNames() {
		/** Needs J-UNIT*/
		MySortedLinkedList<String> gettinSongs = new MySortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		Set<String> songNames = song.keySet();
		Iterator<String> iter = songNames.iterator();

		while(iter.hasNext()){
			gettinSongs.add((String) iter.next());
		}
		return gettinSongs;
	}


	public ArrayList<String> getMediaFileInfo(String mediaFileName) {
		/** Needs J-UNIT*/
		ArrayList<String> that = new ArrayList<String>(); 
		if(song.containsKey(mediaFileName)){
			that = song.get(mediaFileName);
		}else if(movie.containsKey(mediaFileName)){
			that = movie.get(mediaFileName);	
		}
		return that;
	}

	public MySortedLinkedList<String> getMediaFileNamesWith(
			int durationInSeconds, String artists, String genre, String album,
			String resolution, String rating) {
		String ar = new String();
		ar = artists;
		MySortedLinkedList<String> sorted = new MySortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		ArrayList<String> songInfo = new ArrayList<String>();
		//ArrayList<String> tempInfo = new ArrayList<String>();
		ArrayList<String> check = new ArrayList<String>();
		ArrayList<String> check2 = new ArrayList<String>();
		Integer durationINSecs = durationInSeconds;
		String durationInSecs = durationINSecs.toString();
		//		songInfo.add(0, name);
		//		songInfo.add(1, durationInSecs);------
		//		songInfo.add(2, artists);-------
		//		songInfo.add(3, genre);-------
		//		songInfo.add(4, playCount);
		//		songInfo.add(5, album); ------------

		//		movieInfo.add(0, name);
		//		movieInfo.add(1, durationInSecs);----------
		//		movieInfo.add(2, artists);------------
		//		movieInfo.add(3, genre);----------------
		//		movieInfo.add(4, playcount.toString()); 
		//		movieInfo.add(5, resolution);----------
		//		movieInfo.add(6, rating); -------------

		Set<String> movies = movie.keySet();
		Iterator<String> it2 = movies.iterator();
		while(it2.hasNext()){
			check2 = movie.get(it2.next());
			if((durationInSeconds!= -1)){
				Integer play2 = Integer.parseInt(check2.get(1));
				if(check2.get(1)==durationInSecs || play2 <= durationInSeconds)
					sorted.add(it2.next());
			}else if((null != ar)){
				if(check2.get(2).equals(ar))
					sorted.add(it2.next());
			}else if(null != genre){
				if(check2.get(3).equals(genre))
					sorted.add(it2.next()); 
			}else if(null != resolution){
				if(check2.get(5).equals(resolution))
					sorted.add(it2.next());
			}else if(null!=rating){
				if(check2.get(6).equals(rating))
					sorted.add(it2.next());
			}
		}

		Set<String> songs = song.keySet();
		Iterator<String> it = songs.iterator();
		while(it.hasNext()){


			if(durationInSeconds!= -1){
				Integer play = Integer.parseInt(check.get(1));
				if(check.get(1)==durationInSecs || play <= durationInSeconds)
					sorted.add(it.next());
			}else if( null!=ar){
				if(check.get(2).equals(artists))
					sorted.add(it.next());
			}else if( null != genre){
				if(check.get(3).equals(genre))
					sorted.add(it.next());
			}else if( null!=album){
				if(check.get(4).equals(album))
					sorted.add(it.next());
			}
			return sorted;
		}


		/**
		 * Returns a sorted linked list with the names of media files satisfying the specified
		 * parameters restrictions.  If a field has a value of null, then that criteria is
		 * ignored in the search.  If durationInSeconds is -1, then the duration is ignored.  
		 * If a durationInSeconds value is provided, then media with a duration less than or equal
		 * to that value will be included in the search results.
		 * @param durationInSeconds
		 * @param artists
		 * @param genre
		 * @param album
		 * @param resolution
		 * @param rating
		 * @return sorted linked list sorted by name
	    NOT DONE YET*/
		return sorted;
	}

	public int getPlayCount(String mediaFileName) {
		/** Needs J-UNIT*/
		Integer playCount = 0;
		ArrayList<String> that = new ArrayList<String>(); 
		if(song.containsKey(mediaFileName)){
			that = song.get(mediaFileName);
			String playcount = that.get(4);
			playCount = Integer.parseInt(playcount);
		}else if(movie.containsKey(mediaFileName)){
			that = movie.get(mediaFileName);
			String playcount = that.get(4);
			playCount = Integer.parseInt(playcount);
		}else{
			playCount = -1;
		}
		return playCount;
	}

	public ArrayList<String> getPlayList(String playListName) {
		ArrayList<String> that = playlists.get(playListName);


		return that;
	}

	public void increasePlayCount(String mediaFileName) {
		/** Needs J-UNIT*/
		Integer playCount = 0;
		ArrayList<String> that = new ArrayList<String>(); 
		if(song.containsKey(mediaFileName)){
			that = song.get(mediaFileName);
			String playcount = that.get(4);
			playCount = Integer.parseInt(playcount);
			playCount++;
			playcount = playCount.toString();
			ArrayList<String> songInfo = song.get(mediaFileName);
			songInfo.set(4, playCount.toString());
		}else if(movie.containsKey(mediaFileName)){
			that = movie.get(mediaFileName);
			String playcount = that.get(4);
			playCount = Integer.parseInt(playcount);
			playCount++;
			playcount = playCount.toString();
			ArrayList<String> movieInfo = movie.get(mediaFileName);
			movieInfo.set(4, playCount.toString());	
		}
	}

	public boolean removeMediaFile(String mediaFileName) {
		/** Needs J-UNIT*/
		if(song.containsKey(mediaFileName)){
			song.remove(mediaFileName);
			return true;
		}else if(movie.containsKey(mediaFileName)){
			movie.remove(mediaFileName);
			return true;
		}else{
			return false;
		}
	}

	public boolean removeMediaFromPlayList(String playListName,
			/** Needs J-UNIT*/	
			String mediaFileName) {
		ArrayList<String> that = playlists.get(playListName);
		if(playlists.containsKey(playListName) && that.contains(mediaFileName)){
			return that.remove(mediaFileName);
		}else{
			return false;
		}
	}

	public boolean removePlayList(String playListName) {
		/** Needs J-UNIT*/
		if(playlists.containsKey(playListName)){
			playlists.remove(playListName);
			return true;
		}else{
			return false;
		}
	}

}
