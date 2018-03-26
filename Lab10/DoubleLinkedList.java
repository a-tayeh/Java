public class DoubleLinkedList<T> implements ListInterface<T> {
    private DoubleLinkedNode first;
    private DoubleLinkedNode last;
    private int numElements;

    public DoubleLinkedList() {
        initializeDataFields();
    }

    @Override
    public void add (T newEntry) {
        if(isEmpty()){
            DoubleLinkedNode firstNode = new DoubleLinkedNode(newEntry);
            first = firstNode;
            last = firstNode;
            firstNode.previous = null;
            numElements++;

        }

        else {
            DoubleLinkedNode newNode = new DoubleLinkedNode(newEntry);
            last.next = newNode;
            newNode.previous = last;
            last = newNode;
            numElements++;
        }
    }

    @Override
    public void add (int newPosition, T newEntry) {
        if(isEmpty() || newPosition == numElements+1){
            add(newEntry);
            return;
        }
        if(newPosition<1 || newPosition>numElements+1){
            throw new IndexOutOfBoundsException("can't do that");
        }
        else{
            DoubleLinkedNode currentNode = first;
            DoubleLinkedNode newNode = new DoubleLinkedNode(newEntry);
            for(int i = 1;currentNode!=null && i<newPosition-1;i++){
                currentNode.previous = currentNode;
                currentNode=currentNode.next;
            }
            newNode.next = currentNode.next;
            currentNode.next = newNode;
            newNode.previous = currentNode;
            DoubleLinkedNode temp = newNode.next;
            temp.previous = newNode;
            numElements++;
        }

    }

    @Override
    public T remove (int givenPosition) {
        return null;
    }

    @Override
    public void clear () {
        first = null;
        last = null;
        numElements = 0;
    }

    @Override
    public T replace (int givenPosition, T newEntry) {
        return null;
    }

    @Override
    public T getEntry (int givenPosition) {
        if ((givenPosition >= 1) && (givenPosition <= numElements))	{
            assert !isEmpty();
            return getNodeAt(givenPosition).getData();
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
    }

    @Override
    public T[] toArray() {
        return null;
    }

    @Override
    public boolean contains (T anEntry) {

        boolean found = false;
        DoubleLinkedNode currentNode = first;
        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.getData()))
                found = true;
            else
                currentNode = currentNode.getNextNode();
        }
        return found;

    }

    @Override
    public int getLength() {
        return numElements;
    }

    @Override
    public boolean isEmpty() {
        return numElements == 0;
    }

    // Initializes the class's data fields to indicate an empty list.
    private void initializeDataFields()  {
        first = null;
        last = null;
        numElements = 0;
    }

    protected DoubleLinkedNode getFirst() {
        return first;
    }

    protected DoubleLinkedNode getLast() {
        return last;
    }

    // Returns a reference to the node at a given position.
    // Precondition: The chain is not empty;
    //               1 <= givenPosition <= numberOfEntries.
    protected DoubleLinkedNode getNodeAt(int givenPosition)	{
        assert !isEmpty() && (1 <= givenPosition) && (givenPosition <= numElements);
        DoubleLinkedNode currentNode = first;

        // Traverse the chain to locate the desired node
        // (skipped if givenPosition is 1)
        for (int counter = 1; counter < givenPosition; counter++)
            currentNode = currentNode.getNextNode();

        assert currentNode != null;

        return currentNode;
    }


    protected class DoubleLinkedNode{
        private T data;
        private DoubleLinkedNode next;  	 // Link to next node
        private DoubleLinkedNode previous; // Link to previous node

        private DoubleLinkedNode(T dataPortion){
            data = dataPortion;
            next = null;
            previous = null;
        }
        private DoubleLinkedNode(DoubleLinkedNode previousNode, T dataPortion, DoubleLinkedNode nextNode){
            data = dataPortion;
            next = nextNode;
            previous = previousNode;
        }

        protected T getData(){
            return data;
        }

        private void setData(T newData){
            data = newData;
        }

        DoubleLinkedNode getNextNode(){
            if(next==null){
                return null;
            }
            return next;
        }

        private void setNextNode(DoubleLinkedNode nextNode){
            next = nextNode;
        }

        DoubleLinkedNode getPreviousNode(){
            return previous;
        }

        private void setPreviousNode(DoubleLinkedNode previousNode){
            previous = previousNode;
        }
    }
    public static void main(String [] args){
        DoubleLinkedList<String> list = new DoubleLinkedList<String>();
//        list.add("Ali");
//        list.add("ME");
//        list.add("sammy");
//        System.out.println(list.getLength());
        list.add("Will");
        list.add("Dustin");
        list.add("Lucas");
        list.add("Mike");
        list.add("Eleven");
        list.add(6,"barb");
        System.out.println(list.getNodeAt(6).getData());
//        System.out.println(list.getNodeAt(4).getPreviousNode().getData());


    }
}