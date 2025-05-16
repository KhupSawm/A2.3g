package unisa.dse.a2.students;

import unisa.dse.a2.interfaces.List;

/**
 * @author simont
 *
 */
public class DSEList implements List {
	
	public Node head;
	private Node tail;

	public DSEList() {
		this.head = null;
		this.tail = null;
		
	}
	public DSEList(Node head_) {
		// Starts from new node
		head_.prev = null;
		head_.next = null;
		
		this.head = head_;
		this.tail = head_;
	}
	
	//Takes a list then adds each element into a new list
	public DSEList(DSEList other) { // Copy constructor. 
		this.head = null;
		this.tail = null;
		
		// Iterate from head to tails and copy every node until current .next is null
		for (Node current = other.head; current != null; current = current.next) {
			this.add(current.getString()); // Create new Node
		}
	}

	//remove the String at the parameter's index
	public String remove(int index) {

	}

	//returns the index of the String parameter 
	public int indexOf(String obj) {
	}
	
	//returns String at parameter's index
	public String get(int index) {
	}

	//checks if there is a list
	public boolean isEmpty() {
		// If head is null, then there are no nodes in the list so list is empty
		return head == null;
	}

	//return the size of the list
	public int size() {
	}
	
	//Take each element of the list a writes them to a string 
	@Override
	public String toString() {
	}

	//add the parameter String at of the end of the list
	public boolean add(String obj) {
		// Creates new node
		// new Node next and prev is set to null as it's a new Node
		Node n = new Node(null, null, obj);
		
		// A new node so head and tail reference to n
		if (head == null) {
			head = n;
			tail = n;
		}else { // If not empty
			n.prev = tail; // This connects the new node to the end of the last node
			tail.next = n; // Old last node connects to the new
			tail = n; // updates tail pointer
		}
		return true;
	}

	//add String at parameter's index
	public boolean add(int index, String obj) {
	}

	//searches list for parameter's String return true if found
	public boolean contains(String obj) {
	}

	//removes the parameter's String form the list
	public boolean remove(String obj) {
	}
	
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object other) {
		return true;
	}
	
}
