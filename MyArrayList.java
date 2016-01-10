/*
* File Name: MyArrayList.java
* Author: Adam Carlson
* Date: 11/21/15 
*/


// models the array list data structure
public class MyArrayList  {

	private Object[] list; // stores the array of values
	private int size; // stores the size of the list
	
	// initializes the array list
	public MyArrayList(){
		this.list = new Object[8];
		this.size = 0;
	}
	
	// clears the array list
	public void clear(){
		this.list = new Object[8];
		this.size = 0;
	}
	
	// inserts data at a given location
	public void insert(Object o, int index){
		if( this.size == this.list.length){ // check if need to expand array
			Object[] temp = new Object[this.list.length *2];
			for(int i = 0; i<this.list.length; i++){
				temp[i] = this.list[i];
			}
			this.list = temp;
		}
		for(int i = this.size; i> index; i--){
			this.list[i] = this.list[i-1]; // shift values to the right 
		}
		this.list[index] = o;
		this.size++; 
	}
	
	// adds an element to the back of the list
	public void add(Object o){
		if( this.size == this.list.length){ // check if need to expand array
			Object[] temp = new Object[this.list.length *2];
			for(int i = 0; i<this.list.length; i++){
				temp[i] = this.list[i]; 
			}
			this.list = temp;
		}
		this.list[this.size] = o;
		this.size++;
	}
	
	// removes an element at a given location
	public void remove(int index){
		for(int i = index; i < this.size; i++){
			this.list[i] = this.list[i+1]; // shift values to the left
		}
		this.size--;
	}
	
	// returns an element from a given location
	public Object get(int index){
		return this.list[index];
	}
	
	// returns the size of the list
	public int size(){
		return this.size;
	}
	
	// tests the class and its methods
    public static void main(String[] args){
		MyArrayList list = new MyArrayList();
		list.add(3);
		list.add("hello");
		list.add(true);
		for(int i = 0; i< list.size(); i++){
			System.out.println( list.get(i) );
		}
		list.remove(0);
		list.remove(1);
		System.out.println("After Removal: ");
		for(int i = 0; i< list.size(); i++){
			System.out.println( list.get(i) );
		}
		for(int i = 0; i< 50; i++){
			list.add(i);
		}
		System.out.println(list.get(45));
		list.insert(650, 45);
		System.out.println(list.get(45));
		list.insert("adam",0);
		System.out.println(list.get(0));
		list.clear();
		System.out.println(list.get(0));
	}
}