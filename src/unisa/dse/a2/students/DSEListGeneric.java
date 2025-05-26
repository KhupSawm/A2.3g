package unisa.dse.a2.students;

import unisa.dse.a2.interfaces.ListGeneric;

/**
 * @author simont
 *
 */
public class DSEListGeneric<item> implements ListGeneric<item> {
	
	public NodeGeneric<item> head;
	private NodeGeneric<item> tail;

	public DSEListGeneric() {
		// Empyty node
		this.head = null;
		this.setTail(null);
		
	}
	public DSEListGeneric(NodeGeneric<item> head_) {
		// Null check
		if (head == null) {
			throw new NullPointerException("Cannot initialise with null Node");
		}
		// Same as DSEList
		head_.prev = null;
		head_.next = null;
		
		this.head = head_;
		this.tail = head_;
	}
	
	//Takes a list then adds each element into a new list
	public DSEListGeneric(DSEListGeneric<item> other) { // Copy constructor. 
		// Null Check
		if (other == null) {
			throw new NullPointerException("Cannot initialise with null Node");
		}
		// Reuse Code
		this.head = null;
		this.tail = null;
		
		// Iterate from head to tails and copy every node until current .next is null
		// This time replace Node with NodeGeneric and call current.get()
		for (NodeGeneric<item> current = other.head; current != null; current = current.next) {
			this.add(current.get()); // Create new Node
		}
	}

	//remove and return the item at the parameter's index
	public item remove(int index) {
		// Traverse to given index with while loop
			NodeGeneric<item> current = head;
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
			item FinalNode = current.get();
			
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
	public int indexOf(item obj) {
		// Index counter
		int index = 0;
		
		// Treverse through list
		// From current which is the head to each next link
		for (NodeGeneric<item> current = head; current != null; current = current.next) {
			if (obj.equals(current.get())) { // using .equals to test if obj is equal to current
				return index; // Return the index of where is it is
			}
			index ++;
		}
		return -1; // When obj is found end loops return -1
	}
	
	//returns item at parameter's index
	public item get(int index) {
		// Returns null if index not exist or negative
		if (index < 0) {
			return null;
		}
		int ind = 0;
		for (NodeGeneric<item> current = head; current != null; current = current.next) {
			if (ind == index) { // If Traverse until ind matches index
				return current.get(); // return node's string
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
		for (NodeGeneric<item> current = head; current != null; current = current.next) {
			count ++;
		}
		return count;
	}
	
	//Take each element of the list a writes them to a string 
	@Override
	public String toString() {
		String space = "";
		for (NodeGeneric<item> current = head; current != null; current = current.next) {
			space += current.get(); // Appends string to
			if(current.next != null) {
				space += " "; // Appends empty string (space) if next link is not null
			}
		}
		return space;
	}

	//add the parameter item at of the end of the list
	public boolean add(item obj) {
		// Creates new node
		// new Node next and prev is set to null as it's a new Node
		NodeGeneric<item> n = new NodeGeneric<item>(null, null, obj);
		
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

	//add item at parameter's index
	public boolean add(int index, Object obj) {
	}

	//searches list for parameter's String return true if found
	public boolean contains(Object obj) {
	}

	//removes the parameter's item form the list
	public boolean remove(Object obj) {
	}
	
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object other) {
		return true;
	}
	/**
	 * @return the tail
	 */
	public NodeGeneric<item> getTail() {
		return tail;
	}
	/**
	 * @param tail the tail to set
	 */
	public void setTail(NodeGeneric<item> tail) {
		this.tail = tail;
	}
	
}
