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

		/** 
 		*		A remove at index method that takes in the position in which data will be deleted 
 		* as first argument.
 		*
 		****/

        public void removeIndex(int index) {
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
 		/** 
 		*		An insert method that takes in the position in which data will be inserted 
 		* as first argument and the type of data that will be inserted as a second argument
 		*
 		****/
 		public void insert(int index, T entry) {
			Node newNode = new Node(entry);
			Node current = firstNode;
    		Node previous = null;
    		// if it's the first node in our chain then we simply call the add method and pass in the entry
			if(index == 1){
				add(entry);
				numOfEntries++;
				return; // this exits our method
			}
			// otherwise, it will go through our chain looking for the node previous to the position in which
			// the new node will be inserted
			for(int i = 1; current!=null && i<index;i++){
					previous = current;
					current = current.next;
			}

			 newNode.next = current;
    		 previous.next = newNode;			
			 numOfEntries++;
 		} 

 		public int getNumEntries(){
 			return numOfEntries;
 		}






		/** Retrieves all entries that are in this linkedchain.
			@return A newly allocated array of all the entries in this linked chain.
		*/
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[numOfEntries]; // Unchecked cast
		int index = 1;
		Node currentNode = firstNode;
		while ((index < numOfEntries) && (currentNode != null)) {
			result[index] = currentNode.data;
			index++;
			currentNode = currentNode.next;
		}
		return result;
	}
	



	/** 
	* An inner class node that has two instance variables , data and next, along with
	*  two contructors, a default one for the first initial node
	*/
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


	
	}



/********************************************************************************
*								Main Method
*
*********************************************************************************/

		public static void main(String[] args){


		try{
		// instanciate and object of type Ali_Tayeh_Problem02 and pass in a type parameter Object	
		Ali_Tayeh_Problem02<Object> obj = new Ali_Tayeh_Problem02<Object>();
		//Asks for user input to set how many DNA Nucleobase our string should have
		Scanner in =  new Scanner(System.in);
		int counter = 0;
		boolean done = false;
		String [] choices;
		int index = 0;

		
		while(obj.getNumEntries()<=1000000){
			obj.add("T");
			obj.add("G");
			obj.add("C");
			obj.add("A");
		}
		// takes the number of nucleobase strings we'll edit
		int limit = in.nextInt();


	/**	A while loop that splits the user input based on white space and assign each value
	*  to a string array position assuming the order of data in the input string is in the order of 
	*  		<INSTRUCTION> <POSITION> <VALUE>
	*/
		while(!done){
			choices = in.nextLine().split("\\s+");
			if(choices[0].equalsIgnoreCase("i")){
				index = Integer.parseInt(choices[1]);
				obj.insert(index,choices[2]);
				limit++;
			}
			else if(choices[0].equalsIgnoreCase("d")){
				index = Integer.parseInt(choices[1]);
				obj.removeIndex(index);
				limit--;
			}
			else if(choices[0].equalsIgnoreCase("e")){
				done = true;
			}

		}

		// a for loop that prints out the final results of our modified DNA Strand
		for(int i = 1;i<=limit;i++){
			System.out.print(obj.toArray()[i]);
		}	
		System.out.println();






		
	// incase something goes wrong!
	}catch(Exception e){System.out.println(e.getMessage());}
		
	}
}