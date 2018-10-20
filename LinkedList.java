/**
 * File: LinkedList.java
 * Author: Yusheng Hu
 * Date: 10/11/2016
 */
 
 
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class LinkedList<T> implements Iterable<T>{
	//store the top node to a new top
	//number is the size of the list
	private Node top;
	private int number;
	private Node bottom;
	
	//constructor, assign the top node to null and number to 0
	public LinkedList(){
		top = null;
		number = 0;
		bottom = null;
	}
	//clear every node inside the list
	public void clear(){
		top = null;
		number = 0;
		bottom = null;
	}
	//return the size of the list 
	public int size(){
		return number;
	}
	//add a T item inside the list
	public void add(T item){
		Node n = new Node(item);
		if(top != null)	
			top.setPrev(n);
		else
			bottom = n;
		n.setNext(top);
		top = n;
		number++;
	}
	// Node class is a subclass of Linkedlist class.	
	private class Node{
		//node next stores the next node inside the list
		//T bucket stores the value of current node
		private Node next;
		private T bucket;
		private Node prev;
		
		// Constructor, assign the next node to null and the value of current node to input item.
		public Node(T item){
			next = null;
			bucket = item;
			prev = null;
		}
		
		public Node getPrev() { 
			return this.prev; 
			}
			
		public void setPrev( Node p ) { 
			this.prev = p; 
			}
				
		//return the value of current node
		public T getThing(){
			return bucket;
		}
		//set the next node to n
		public void setNext(Node n){
			next = n;
		}
		//return the next node;
		public Node getNext(){
			return next;
		}
		
	}
	//sub-class iterator will run through all the node
	private class LLIterator implements Iterator<T>{
		//cur will store the current current node
		private Node cur;
		//constructor, assign cur to input head.
		public LLIterator(Node head){
			this.cur = head;
		}
		// test if cur is null
		public boolean hasNext(){
			return cur != null;
		}
		
		public T next() {
			// write this method
			// should move towards the top of the stack and return the current data value.
			Node c = cur;
				
			cur = cur.getNext();
			
			return c.getThing();	
		}
		
		public void remove() {
    		throw new UnsupportedOperationException();
		}
	}
	
	 private class LLBackwardIterator implements Iterator<T>{
	 	//cur will store the current current node
		private Node cur;
		//constructor, assign cur to input head.
		public LLBackwardIterator(Node bottom){
			this.cur = bottom;
		}
		// test if cur is null
		public boolean hasNext(){
			return cur != null;
		}
		
		public T next() {
			// write this method
			// should move towards the top of the stack and return the current data value.
			Node c = cur;
				
			cur = cur.getPrev();
			
			return c.getThing();	
		}
		
		public void remove() {
    		throw new UnsupportedOperationException();
		}	
	 }
	
	// return a new lliterator
	public Iterator<T> iterator() {
        return new LLIterator( this.top );
    }
    
    // Return a new LLBackwardIterator pointing to the head of the list
    public Iterator<T> backward_iterator() {
        return new LLBackwardIterator( this.bottom );
    }
    
    // put the all the value inside a list in order
    public ArrayList<T> toArrayList(){
    	ArrayList<T> list = new ArrayList<T>();
		for (T item:this){
			list.add(item);
		}
		return list;
	}
    
     // put the all the value inside a list in random order
    public ArrayList<T> toShuffledList(){
    	ArrayList<T> ori = this.toArrayList();
    	ArrayList<T> temp = new ArrayList<T>();
    	int size = ori.size();
    	for (int i = 0; i < size; i ++){
    		Random ran = new Random();
    		int num = ran.nextInt(size - i);
    		temp.add(ori.get(num));
    		ori.remove(num);
    	}
    	return temp;
    }
    
	//test function
	public static void main(String[] args) {		
		LinkedList<Integer> llist = new LinkedList<Integer>();
		
		llist.add(5);
		llist.add(10);
		llist.add(20);
		
		System.out.printf("\nAfter setup %d\n", llist.size());
		for(Integer item: llist) {
			System.out.printf("thing %d\n", item);
		}
		
		llist.clear();
		
		System.out.printf("\nAfter clearing %d\n", llist.size());
		for (Integer item: llist) {
			System.out.printf("thing %d\n", item);
		}
		
		for (int i=0; i<20; i+=2) {
			llist.add(i);
		}
		
		System.out.printf("\nAfter setting %d\n", llist.size());
		for (Integer item: llist) {
			System.out.printf("thing %d\n", item);
		}
		
		ArrayList<Integer> alist = llist.toArrayList();
		System.out.printf("\nAfter copying %d\n", alist.size());
		for(Integer item: alist) {
			System.out.printf("thing %d\n", item);
		}						
	
		alist = llist.toShuffledList();	
		System.out.printf("\nAfter copying %d\n", alist.size());
		for(Integer item: alist) {
			System.out.printf("thing %d\n", item);
		}
		
		System.out.println( "iterating backward" );
        Iterator bi = llist.backward_iterator();
        while (bi.hasNext()) {
            System.out.println( bi.next() );
        }
	}
	
	


}