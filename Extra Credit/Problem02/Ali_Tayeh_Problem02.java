import java.util.Scanner;
import java.lang.Math;

public class Ali_Tayeh_Problem02<T>{


	private Node firstNode;
	private int numOfEntries;

	public Ali_Tayeh_Problem02(){
		this.firstNode = null;
		this.numOfEntries = 0;
	}

	//this add an entry to our chain, if our numofentries is 0 then our chain is empty 
	// so we create a new node then assign first node to that newly created node.
	public boolean add(T entry){
			if(numOfEntries == 0){
				Node newNode = new Node(entry);
				firstNode = newNode;
				numOfEntries++;
				return true;
			}
		// if the node is not empty then we create that entry and assign in to newNode
			// then make the newNode.next equal to first node
			// now our old node is pushed aside and newNode is our first node in the chain
			Node newNode = new Node(entry);
			newNode.next = firstNode;
			firstNode = newNode;
			numOfEntries++;
			return true;
		
		
	}

/********************************************************************************
*								WORK IN PROGRESS
*
*********************************************************************************/


        public void remove(int index) {
			boolean result = false;
			Node temp = firstNode;
			if(index == 1){
				firstNode = temp.next;
				numOfEntries--;
			}
			for(int i = 1; temp!=null&& i<index-1;i++){
					temp = temp.next;
			}
			Node next = temp.next.next;
			temp.next = next;
			numOfEntries--;
			
			
 		}

 		public void insert(int index, T entry) {
			Node newNode = new Node(entry);
			Node current = firstNode;
    		Node previous = null;
			if(index == 1){
				add(entry);
				return;
			}
			for(int i = 1; current!=null && i<index;i++){
					previous = current;
					current = current.next;
			}
			 newNode.next = current;
    		 previous.next = newNode;			
			 numOfEntries++;
			
			
 		} 



/********************************************************************************
*								WORK IN PROGRESS
*
*********************************************************************************/



		/** Retrieves all entries that are in this bag.
			@return A newly allocated array of all the entries in this bag.
		*/
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[numOfEntries]; // Unchecked cast
		int index = 0;
		Node currentNode = firstNode;
		while ((index < numOfEntries) && (currentNode != null)) {
			result[index] = currentNode.data;
			index++;
			currentNode = currentNode.next;
		}
		return result;
	}
	public void removeEntry(int index){
		Node currentNode = firstNode;
		while((index < numOfEntries)&& currentNode!=null){
			
		}
	}



	 
	private class Node{

		private T data;
		private Node next;

		public Node(T dataPortion){
			this(dataPortion,null);
		}
		public Node(T dataPortion, Node nextNode){
			this.data = dataPortion;
			this.next = nextNode;
		}
		public T getData() {
			return data; 
			} // end getData
		public void setData(T newData) {
			    data = newData;
			 } // end setData
		public Node getNextNode() {
			return next;
		} // end getNextNode
		public void setNextNode(Node nextNode) {
			next = nextNode;
		} 

	}

		public static void main(String[] args){

		try{
		Ali_Tayeh_Problem02 obj = new Ali_Tayeh_Problem02();
		//asks for user input to set how many DNA Nucleobase our string should have
		Scanner in =  new Scanner(System.in);
		// int limit = in.nextInt();
		int counter = 0;

/********************************************************************************
*								WORK IN PROGRESS
*
*********************************************************************************/

		// int counter = 0;
		obj.add("A");
		obj.add("T");
		obj.add("C");
		obj.add("G");
		System.out.println("Before removing at index 2");

		for(Object a: obj.toArray()){
			System.out.printf("%s",a);
		}
		System.out.println();
		obj.remove(2);

		System.out.println("AFTER removing at index 2");

		for(Object a: obj.toArray()){
			System.out.printf("%s",a);
		}
		System.out.println();

		System.out.println("Before inserting a C at index 2");

		for(Object a: obj.toArray()){
			System.out.printf("%s",a);
		}
		System.out.println();
		obj.insert(2,"C");

		System.out.println("AFTER inserting a C at index 2");

		for(Object a: obj.toArray()){
			System.out.printf("%s",a);
		}
		System.out.println();
		// String g = "G";
		// obj.remove(String g);

/********************************************************************************
*								WORK IN PROGRESS
*
*********************************************************************************/

		// while(counter<limit){
		// 	// this creates a random  num b/w 1-4
		// 	// it will pass in an entry based on which random number cameback
		// 	int random = (int)(Math.random()*4+1);
		// 	switch(random){
		// 		case 1:
		// 			obj.add("A");
		// 			break;
		// 		case 2:
		// 			obj.add("G");
		// 			break;
		// 		case 3: 
		// 			obj.add("T");
		// 			break;
		// 		case 4:
		// 			obj.add("C");
		// 			break;
		// 	}
		// 	counter++;
		// }	


		// this is just me looping throw the toArray method that's coming from Ali_Tayeh... class
		// and print out the string of DNA Nucleobase
		

	}catch(Exception e){System.out.println(e.getMessage());}
		
	}
}