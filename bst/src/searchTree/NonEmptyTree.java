package searchTree;

import java.util.Collection;
import edu.umd.cs.findbugs.annotations.NonNull;

/**
 * This class represents a non-empty search tree. An instance of this class
 * should contain:
 * <ul>
 * <li>A key
 * <li>A value (that the key maps to)
 * <li>A reference to a left Tree that contains key:value pairs such that the
 * keys in the left Tree are less than the key stored in this tree node.
 * <li>A reference to a right Tree that contains key:value pairs such that the
 * keys in the right Tree are greater than the key stored in this tree node.
 * </ul>
 *  
 */
public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {
	K key;
	V value;
	Tree<K,V> left;
	Tree<K,V> right;
	int count = 0;


	//	 Provide whatever constructors and instance variables you need
	public NonEmptyTree(K key, V value, Tree<K,V> left, Tree<K,V> right  ){
		this.key = key;
		this.value = value;
		this.left = left;
		this.right =  right;

	}

	public V search(K key) {
		int check = key.compareTo(this.key);
		if(check == 0){
			return this.value;
		}else if(check > 0){
			return right.search(key);
		}else if(check < 0){
			return left.search(key);
		}
		return null;
	}

	public NonEmptyTree<K, V> insert(K key, V value) {
		/**Insert/update the Tree with a new key:value pair. If the key already exists in the tree,
		 *  update the value associated with it.
		 *  If the key doesn't exist, insert the new key value pair. 
		 * This method returns a reference to an Tree that represents the updated value.
		 *  In many, but not all cases, the method may just return a reference to this.
		 *  This method is annotated @CheckReturnValue because you have to pay attention to the return value;
		 *  if you simply invoke insert on a Tree and ignore the return value, your code is almost certainly wrong.  */

		int check = key.compareTo(this.key);
		if(check == 0){//same
			this.value = value;
		}else if(check > 0){//greater
			right = right.insert(key, value);
		}else if(check < 0){//less than
			left = left.insert(key, value);
		}else{
			return new NonEmptyTree<K,V>(key, value, this, this);
		}
		return this;
	}

	public Tree<K, V> delete(K key) {
		/** Delete any binding the key has in this tree. If the key isn't bound, this is a no-op
		 *  This method returns a reference to an Tree that represents the updated value.
		 *  In many, but not all cases, the method may just return a reference to this. 
		 * This method is annotated @CheckReturnValue because you have to pay attention to the return value;
		 *  if you simply invoke delete on a Tree and ignore the return value, your code is almost certainly wrong. */
		int  check = key.compareTo(this.key);
		if (check == 0){
			try{ 
				this.key = left.max();
				value = search(left.max());
				left = left.delete(left.max());


			}catch(TreeIsEmptyException e){
				return right;	
			}


			//left gets set to top, and right. max left side or min of right side
		}else if (check < 0) {//DeleteTree: Check left of tree. if nothing there, return right of tree.
			left = left.delete(key);
		}else if (check > 0){
			right = right.delete(key);
		}

		return this;	
	}

	public @NonNull K max() {
		K max = null;

		try{
			max = right.max();
		}catch(TreeIsEmptyException e){
			max = this.key;

		}
		return max;
	}


	public @NonNull K min(){
		K min = null;

		try{
			min = left.min();
		}catch(TreeIsEmptyException e){
			min = this.key;

		}
		return min;
	}

	public int size() {

		count = 1; //counts parent
		count += right.size();
		count += left.size();
		return count;
	}


	public void addKeysToCollection(Collection<K> c) {
	left.addKeysToCollection(c);
		c.add(this.key);
		right.addKeysToCollection(c);
	}

	public Tree<K,V> subTree(K fromKey, K toKey) {
		NonEmptyTree<K,V> that = null;
		int check = this.key.compareTo(fromKey);
		int check2 = this.key.compareTo(toKey);
		if(check >= 0 && check2 <= 0){
			that = new NonEmptyTree<K,V>(this.key, this.value, left.subTree(fromKey, toKey), right.subTree(fromKey, toKey));
		}else if(this.key.compareTo(toKey) > 0){
			return left.subTree(fromKey, toKey);
		}else {
			return right.subTree(fromKey, toKey);
		}
		return that;
			
		//Case 1. Range is less than parent/ resides in left subtree
		//Case 2 Range is greater than parent/ resides in right subtree
		//Case 3 Range includes parent. L + P or R + P or L + P + R

	}
}