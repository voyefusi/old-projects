package MyList;

import java.util.*;




/**
 * This class represents a simple implementation of a sorted singly-linked list.
 * Elements added to the list are inserted in sorted order based on an specified
 * Comparator object.
 * <br><br>
 * 
 * This class relies on two classes: MyIterator and MyListNode. We have implemented
 * the MyListNode class for you, but you are responsible for the implementation
 * of the MyIterator class.  Notice these are inner classes defined within the 
 * MySortedLinkedList class.  As a result they may access each other's fields even
 * if they are private.  You are required to use the MyListNode class to implement
 * the nodes of your linked list.
 * <br><br>
 * 
 * Feel free to add any methods to the MySortedLinkedList class you consider are 
 * necessary to implement your project.  <b>However, keep in mind that there are two 
 * methods you are required to implement: an add method returning void (adds an element 
 * to the list keeping the list sorted) and a remove method returning void (removes 
 * any element(s) from the list that are equal to the parameter).</b>  
 * 
 * The JUnit public tests provide an example of using the MySortedLinkedList class.
 * <br><br>
 * 
 * You may not use the Java API LinkedList or ArrayList classes during the implementation
 * of the MySortedLinkedList class.
 *  
 * @author Dept of Computer Science, UMCP
 */

public class MySortedLinkedList<T> implements Iterable<T> {
	private Comparator<T> comparator;
	private MyListNode<T> head;

	/**
	 * Creates an empty list that is associated with the specified comparator.
	 * @param comparator
	 */
	public MySortedLinkedList(Comparator<T> comparator) {
		this.comparator = comparator;
		head = null;
	}

	/**
	 * This class represents a linked list node. 
	 * You should not modify this class. 
	 * 
	 * Because MyListNode is an inner class of MySortedLinkedList,
	 * methods in MySortedLinkedList may access fields of MyListNode
	 * directly even though they are private.
	 * 
	 * You may use the default constructor for MyListNode, which
	 * initializes both val and next to null.
	 * 
	 * @param <V>
	 */
	private class MyListNode<V> {
		private V val;
		private MyListNode<V> next;
	}
	/**
	 * Inserts the specified element at the correct position in the sorted list.
	 */
	public void add(T element) {

		MyListNode<T> newElement = new MyListNode<T>();
		newElement.val = element;

		if(isEmpty()){
			head = newElement;
		}else{
			MyListNode<T> curr = head;
			MyListNode<T> prev = null;

			while (curr != null) {
				int check = comparator.compare(curr.val, element);

				if (check < 0) {
					prev = curr;
					curr = curr.next;
				} else {
					break;					
				}

			}

			if(curr == null){
				prev.next = newElement;
			}else if (prev == null){
				newElement.next = curr;
				head = newElement;		
			}else{
				newElement.next = curr;
				prev.next = newElement;
			}
		}


	}


	/**
	 * Returns the element at the specified position in this list.
	 */
	public T get(int index) {
		int count = 0;
		MyListNode<T> temp = head;
		if(!isEmpty()){
			while (count != index) {
				count++;
				temp = temp.next;
			}
		}
		return (T)temp.val;
	}

	/**
	 * Removes any elements matching given element
	 */
	public void remove(T v) { 

		MyListNode<T> prev = null, curr = head;
		while (curr != null) {
			if (curr.val.equals(v)) {
				if (curr == head)
					head = head.next;
				else
					prev.next = curr.next;
				break;
			} else {
				prev = curr;
				curr = curr.next;
			}
		}
	}



	public int size() {
		int count = 0;
		MyListNode<T> temp = head;
		if (!isEmpty()){
			while (temp != null) {
				count++;
				temp = temp.next;
			}
		}
		return count;
	}	


	/*
	 * 
	 */
	public boolean isEmpty() {
		if(head == null){
			return true;
		}else{
			return false;
		}
	}
	public String toString() {
		String result = "\" ";
		MyListNode<T> temp = head;
		while (temp != null) {
			result += temp.val;
			temp = temp.next;
		}
		return result;
	}
	/**
	 * Returns an iterator over the elements in this list (in proper sequence).  
	 * @return iterator over the list
	 */
	public Iterator<T> iterator() {
		MyIterator<T> it = new MyIterator<T>(head);
		return it;	
	}

	/**
	 * This class implements an iterator over the list.
	 * You must implement this class. 
	 * @param <E>
	 */
	private class MyIterator<E> implements Iterator<E> {
		private MyListNode<E> start;

		/**
		 * Defines an iterator based on the start node provided.
		 * @param start
		 */
		private MyIterator(MyListNode<E> start) {
			this.start = start;		
		}
		

		/**
		 * Returns true if there is another element available
		 * @return true if there is another element and false otherwise
		 */
		public boolean hasNext() {
			if (start == null){
				return false;
			}else{
				return true;
			}	
		}

		/**
		 * Returns the next element
		 * @return next element
		 */
		public E next() {
			E ele = start.val;
			start = start.next;
			return ele;
		}

		/**
		 * Removes an element from the list.  You do not need to implement this method.
		 */



		public void remove() {
			throw new UnsupportedOperationException("You do NOT need to implement this method");
		}
	}
}