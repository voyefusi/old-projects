package tests;

import java.util.Iterator;
import java.util.Set;

import searchTree.SearchTreeMap;
import junit.framework.TestCase;
import edu.umd.cs.findbugs.annotations.Nullable;

@edu.umd.cs.findbugs.annotations.DefaultAnnotationForParameters(Nullable.class)
public class StudentTests extends TestCase {
	public void testSearch(){

	}

	public void testInsert(){
		SearchTreeMap<Integer, String> search = new SearchTreeMap<Integer, String>();

		search.put(8, "vik");
		search.put(2, "viks");
		search.put(4, "viktor");
		search.put(10, "viktory");
		search.put(7, "viking");
		search.put(11, "slikvik");

		assertTrue(search.get(8).equals("vik"));
		assertTrue(search.get(2).equals("viks"));
		assertTrue(search.get(4).equals("viktor"));
		assertTrue(search.get(10).equals("viktory"));
		assertTrue(search.get(7).equals("viking"));
		assertTrue(search.get(11).equals("slikvik"));
	}

	public void testDelete(){
		SearchTreeMap<Integer, String> search = new SearchTreeMap<Integer, String>();
		search.put(8, "vik");
		search.put(2, "viks");
		search.put(4, "viktor");
		search.put(10, "viktory");
		search.put(7, "viking");
		search.put(11, "slikvik");
		assertTrue(search.get(2).equals("viks"));
		search.remove(2);
		/**NOT DONE YET*/
	}

	public void testMax(){
		SearchTreeMap<Integer, String> search = new SearchTreeMap<Integer, String>();
		search.put(8, "vik");
		search.put(2, "viks");
		search.put(4, "viktor");
		search.put(10, "viktory");
		search.put(7, "viking");
		search.put(11, "slikvik");
		int max = search.getMax();
		assertTrue(max == 11);
	}
	
	public void testMin(){
		SearchTreeMap<Integer, String> search = new SearchTreeMap<Integer, String>();
		search.put(8, "vik");
		search.put(2, "viks");
		search.put(4, "viktor");
		search.put(10, "viktory");
		search.put(7, "viking");
		search.put(11, "slikvik");
		int min = search.getMin();
		assertTrue(min == 2);
	}

	public void testSize(){
		SearchTreeMap<Integer, String> search = new SearchTreeMap<Integer, String>();
		search.put(8, "vik");
		search.put(2, "viks");
		search.put(4, "viktor");
		search.put(10, "viktory");
		search.put(7, "viking");
		search.put(11, "slikvik");
		int size = search.size();
		assertTrue(size == 6);
	}

	public void testAddKeysToCollection(){
		SearchTreeMap<Integer, String> search = new SearchTreeMap<Integer, String>();
		search.put(8, "vik");
		search.put(2, "viks");
		search.put(4, "viktor");
		search.put(10, "viktory");
		search.put(7, "viking");
		search.put(11, "slikvik");
		Set<Integer> that = search.keySet();
		Iterator<Integer> it = that.iterator();
		String stuff = "";
		while(it.hasNext()){
			stuff += it.next();
		}
		assertTrue(stuff.equals("24781011"));
	}

	public void testSubTree(){
		SearchTreeMap<Integer, String> search = new SearchTreeMap<Integer, String>();
		search.put(8, "vik");
		search.put(2, "viks");
		search.put(4, "viktor");
		search.put(10, "viktory");
		search.put(7, "viking");
		search.put(11, "slikvik");
		
		SearchTreeMap<Integer, String> search2 = search.subMap(4, 10);
		Set<Integer> that = search2.keySet();
		Iterator<Integer> it = that.iterator();
		String stuff = "";
		while(it.hasNext()){
			stuff += it.next();
		}
		assertTrue(stuff.equals("47810"));
	}
}