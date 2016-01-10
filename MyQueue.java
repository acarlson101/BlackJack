/*
* File Name: MyQueue.java
* Author: Adam Carlson
* Date: 10/27/15
*/

import java.util.*;

// models the queue data structure
public class MyQueue<T> implements Iterable<T>{
	
	private MyLinkedList<T> list; //stores the lists of data for the queue
	private int maxSize; //stores the maximum size of the queue
	
	//initializes the queue with largest possible maximum size
	public MyQueue(){
		list = new MyLinkedList<T>();
		this.maxSize = Integer.MAX_VALUE;
	}
	
	//initializes the queue with a set maximum size
	public MyQueue(int size){
		list = new MyLinkedList<T>();
		this.maxSize = size;
	}
	
	// returns the size of the queue
	public int size(){
		return this.list.size();
	}
	
	// adds an element to the back and returns true if successful, throws exception if not
	public boolean add(T data){
		if(this.list.size() < this.maxSize){
			list.append(data);
			return true;
		}
		throw new IllegalStateException("Capacity Reached");
	}
	
	// add an element to the back and returns true if successful, returns false if not
	public boolean offer(T data){
		if(this.list.size() < this.maxSize){
			list.append(data);
			return true;
		}
		return false;
	}
	
	// removes and returns an element from the front of the list, throws exception if empty
	public T remove(){
		if(this.list.size() != 0){
			T retData = this.list.get(0);
			this.list.delete(0);
			return retData;
		}
		throw new NoSuchElementException("The queue is empty");
	}
	
	//removes and returns an element from the front of the list, returns null if empty
	public T poll(){
		if(this.list.size() != 0){
			T retData = this.list.get(0);
			this.list.delete(0);
			return retData;
		}
		return null;
	}
	
	// returns the element at the front of the list, throws exception if empty
	public T element(){
		if(this.list.size() != 0){
			return this.list.get(0);
		}
		throw new NoSuchElementException("The queue is empty");
	}
	
	//returns the element at the front of the list, returns null if empty
	public T peek(){
		if(this.list.size() != 0){
			return this.list.get(0);
		}
		return null;
	}
	
	//returns an iterator object
	public Iterator<T> iterator(){
		return this.list.iterator();
	}
	
	//tests the class and its methods
	public static void main(String[] args){
	/*	tests exception cases
	
		MyQueue<String> q1 = new MyQueue<>(5);
		for(int i = 0; i < 6; i++){
			q1.add("hi");
		}
	
	
		MyQueue<String> q2 = new MyQueue<>(5);
		for(int i = 0; i < 6; i++){
			System.out.println(q2.offer("hi"));
		}
	
	
		MyQueue q3 = new MyQueue();
		q3.remove();
		
	
		MyQueue q4 = new MyQueue();
		System.out.println( q4.poll() );
	
		MyQueue q5 = new MyQueue();
		q5.element();			
	
		MyQueue q6 = new MyQueue();
		System.out.println( q6.peek() );
	*/
	
		MyQueue<Integer> q = new MyQueue<>();
		for(int i = 0; i<10; i++){
			q.add(i);
		}
		q.offer(53);
		System.out.println(q.peek() + "," + q.element());
		
		for(Integer item: q){
			System.out.println(item);
		}
		
		System.out.println("\nRemoved Items: ");
		System.out.println(q.remove());
		System.out.println( q.poll() );
		
		System.out.println("\nNumber of Items: ");
		System.out.println( q.size() );
		
		System.out.println("\nAfter removing: ");
		for(Integer item: q){
			System.out.println(item);
		}
		
	}	

}	