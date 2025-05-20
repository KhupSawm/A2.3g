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
		// Traverse to given index with while loop
		Node current = head;
		int ind = 0;
		
		while (current != null && ind < index) {
			current = current.next;
			ind ++;
		}
		// If it's passed the end then throw exception
		if (current == null) {
			throw new IndexOutOfBoundsException("Index: " + index);
		}
		// Store the node’s string for the return
		String FinalNode = current.getString();
		
		// If statement that will unlink the current
		// Remove node's link
		if (current.prev == null) {
			head = current.next;
		}else {
			current.prev.next = current.next;
		}
		if (current.next == null) {
			tail = current.prev;
		}else {
			current.next.prev = current.prev;
		}
		return FinalNode; // Return the removed string
	}

	//returns the index of the String parameter 
	public int indexOf(String obj) {
		// Index counter
		int index = 0;
		
		// Treverse through list
		// From current which is the head to each next link
		for (Node current = head; current != null; current = current.next) {
			if (obj.equals(current.getString())) { // using .equals to test if obj is equal to current
				return index; // Return the index of where is it is
			}
			index ++;
		}
		return -1; // When obj is found end loops return -1
	}
	
	//returns String at parameter's index
	public String get(int index) {
		// Returns null if index not exist or negative
		if (index < 0) {
			return null;
		}
		int ind = 0;
		for (Node current = head; current != null; current = current.next) {
			if (ind == index) { // If Traverse until ind matches index
				return current.getString(); // return node's string
			}
			ind ++;
		}
		// If it doesnt exist in list and the loops end then return null
		return null;
	}

	//checks if there is a list
	public boolean isEmpty() {
		// If head is null, then there are no nodes in the list so list is empty
		return head == null;
	}

	//return the size of the list
	public int size() {
		int count = 0;
		
		// Reuse for loop from copy constructor to iterate through list
		// Head to next link until reach null
		// Increment count each time node is visited then return count
		for (Node current = head; current != null; current = current.next) {
			count ++;
		}
		return count;
	}
	
	//Take each element of the list a writes them to a string 
	@Override
	public String toString() {
		String space = "";
		for (Node current = head; current != null; current = current.next) {
			space += current.getString(); // Appends string to
			if(current.next != null) {
				space += " "; // Appends empty string (space) if next link is not null
			}
		}
		return space;
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
		// Allow index from 0 through size() inclusive
		int ind = size();
		if (index < 0 || index > ind) {
			throw new IndexOutOfBoundsException("Index: " + index);
		}
		// Tail case delegates to append  
	    if (index == ind) {
	    	return add(obj);
	    }
	    // Head case 
	    if (index == 0) {
	    	Node newNode = new Node(null, null, obj);
	    	newNode.next = head;
	    	head.prev = newNode;
	    	head = newNode;
	    	
	    	return true;
	    }
	    // Create new node and insert in new node before the current node at the index
	    Node newNode = new Node(null, null, obj);
	    Node current = head;
	    
	    for (int i = 0; i < index; i++) {
	    	current = current.next; // Move to the index
	    }
	    // insert and connect the newNode with between newNode prev and next
	    newNode.prev = current.prev;
	    newNode.next = current;
	    current.prev.next = newNode;
	    current.prev = newNode;
	    return true;
	}

	//searches list for parameter's String return true if found
	public boolean contains(String obj) {
		// Re-using the for loop 
		// Move from head to each next link
		for (Node current = head; current != null; current = current.next) {
			if (obj.equals(current.getString())) { // for each node compare with stored string obj
				return true; // Return true if match
			}
		}
		return false; // Return false, once loop ends and no match found
	}

	//removes the parameter's String form the list
	public boolean remove(String obj) {
		// Re-use loop
		// Locate the node that match the string obj
		for (Node current = head; current != null; current = current.next) {
			if (obj.equals(current.getString())) {
				// unlink the current
				if (current.prev == null) { // remove head
					head = current.next;
				}else {
					current.prev.next = current.next;
				}
			}
		}
	}
	
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) { // If same then true
			return true;
		}
		if (!(other instanceof DSEList)) { // If not same type return false
			return false;
		}
	}
	
}
