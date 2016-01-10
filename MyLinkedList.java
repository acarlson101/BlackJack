/*
* File Name: MyLinkedList.java
* Author: Adam Carlson
* Date: 09/29/15
*/

import java.util.*;

//models the linkedlist data structure
public class MyLinkedList<T> implements Iterable<T> {
  
	private Node head; //stores a pointer to the head node
	private Node tail; //stores a pointer to the tail node
	private int numNodes; //stores the number of nodes in the list
	
	// O(1): initializes the linkedlist
	public MyLinkedList(){
		this.head = null;
		this.tail = null;
		this.numNodes = 0;
	}
	
	// O(1): clears the entire list
	public void clear(){
		this.head = null;
		this.tail = null;
		this.numNodes = 0;
	}
	
	// O(1): returns the size of the list
	public int size(){
		return this.numNodes;
	}
	
	// O(1): adds an element to the head of the list
	public void add(T item){
		Node n = new Node(item); 
		if(this.numNodes == 0){
			this.head = n;
			this.tail = n;
			this.numNodes++;
			
		}
		else{
			n.next = this.head;
			n.prev = null;
			this.head = n;
			this.head.next.prev = this.head;
			this.numNodes++;
		}
		
	}
	
	// O(1): add an element to the end of the list
	public void append(T item){
		Node n = new Node(item);
		if(this.numNodes == 0){
			this.head = n;
			this.tail = n;
			this.numNodes++;
		}
		else{
			n.prev = this.tail;
			this.tail.next = n;
			this.tail = n;
			this.numNodes++;
		}
	}
	
	// O(n): returns the data at a specified location in the list
	public T get(int index){
		Node current = this.head;
		for(int i = 0; i<index; i++){
			current = current.next;
		}
		return current.item;
	}
	
	// O(1): returns an iterator object
	public Iterator<T> iterator(){
		return new LLIterator(this.head); 
	}
	
	// O(n): returns an arraylist of the list contents in order
	public ArrayList<T> toArrayList(){
		ArrayList<T> list = new ArrayList<T>();
		for(T item: this){
			list.add(item);
		}
		return list;
	}
	
	// O(n^3): returns an arraylist of the list contents in shuffled order
	public ArrayList<T> toShuffledList(){
		ArrayList<T> list = new ArrayList<T>();
		for(T item: this){
			list.add(item);
		}
		ArrayList<T> tempList = new ArrayList<T>();
		Random rand = new Random();
		while(list.size() != 0){
			tempList.add( list.remove( rand.nextInt(list.size())) );
		}
		return tempList;
	}
	
	// O(n): removes the specified object if it can be found and returns true, otherwise it 
		//returns false
	public boolean remove(Object obj){
		Node current = this.head;
		for(int i = 0; i < this.numNodes; i++){
			if( current.getThing().equals(obj) ){
				if(i == 0){
					this.head = this.head.next;
					this.numNodes--;
					if(this.numNodes != 0){
						this.head.prev = null;
						return true;
					}
					else{
						this.tail = null;
						return true;
					}	
				}
				else if(i == this.numNodes - 1){
					this.tail = this.tail.prev;
					this.numNodes--;
					if(this.numNodes != 0){
						this.tail.next = null;
					}
					return true;	
				}
				else{
					current.prev.next = current.next;
					current.next.prev = current.prev;
					this.numNodes--;
					return true;
				}
			}
			
			else{
				current = current.next;
			}	
		}
		return false;
	}
	
	// O(n): deletes an object at a specified index
	public void delete(int index) {
    	if(index == 0) {
        	this.head = this.head.next;
			this.numNodes--;
			if(this.numNodes != 0){
				this.head.prev = null;
			}
			else{
				this.tail = null;
			}
			
			
        }
        else if(index == this.numNodes - 1){
        	this.tail = this.tail.prev;
			this.numNodes--;
			if(this.numNodes != 0){
				this.tail.next = null;
			}	
        }
        
        else{
        	Node current = this.head;
        	for(int i = 0; i < index-1; i++){
        		current = current.next;
        	}
			current.next = current.next.next;
			current.next.prev = current;
			this.numNodes--;
		}
        
    }
	
	
	//models an individual node in the LinkedList	
	private class Node {
	
		private Node next; //points to the next Node in the LinkedList
		private Node prev; //points to the previous Node in the LinkedList
		private T item; //holds the Node's content
		
		// O(1): initializes the node
		public Node(T item) {
			this.next = null;
			this.prev = null;
			this.item = item;
		}
		
		// O(1): returns the Node's content
		public T getThing() {
			return this.item;
		}
		
		// O(1): sets what the current node points to next
		public void setNext(Node n){
			this.next = n;
		}
		
		// O(1): returns the next node
		public Node getNext(){
			return this.next;
		}
		
		// O(1): sets what the current node points to previously
		public void setPrev(Node n){
			this.prev = n;
		}
		
		// O(1): returns the previous node
		public Node getPrev(){
			return this.prev;
		}
		
	}
	
	
	//iterator class which allows our list to make use of for-each loops
	private class LLIterator implements Iterator<T> {
		
		private Node next; //stores the next Node needed for traversal
		
		// O(1): initializes the iterator
		public LLIterator(Node head){
			this.next = head;
		}
		
		// O(1): returns true if the list has a next node after its current node
		public boolean hasNext(){
			return this.next != null;
		}
		
		// O(1): traverses the list one node and returns the the content of the new node
		public T next(){
			T item = this.next.getThing();  
			this.next = this.next.getNext();
			return item;
			
		}
		
		public void remove(){
		
		}
		
	
		
	}
	
	//tests the linked list class and its methods
	public static void main(String[] args) {
		
		MyLinkedList<Integer> llist = new MyLinkedList<Integer>();
	
		llist.add(5);
		llist.add(10);
		llist.add(20);
	
		System.out.printf("\nAfter setup %d\n", llist.size());
		
		//by using a for each loop, I am testing the LLIterator class and its methods
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
		
		llist.delete(6);
		llist.remove(0);
		llist.remove(18);
		llist.remove(12);
		llist.remove(45);
		llist.append(53);
		System.out.println(llist.get(2));
		System.out.println(llist.get(0));
		System.out.printf("\nAfter removing %d\n", llist.size());
		for (Integer item: llist) {
			System.out.printf("thing %d\n", item);
		}
	}

}