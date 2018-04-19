import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashTableOpenAddressing<K,V> implements DictionaryInterface<K,V> {
    private int numEntries;
    private static final int DEFAULT_CAPACITY = 6;
    private static final int MAX_CAPACITY = 10000;
    private TableEntry<K, V>[] table;

    private double loadFactor;
    private boolean initialized = false;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    public HashTableOpenAddressing() {
        this(DEFAULT_LOAD_FACTOR, DEFAULT_CAPACITY);
    }

    public HashTableOpenAddressing(double loadFactor, int initialCapacity) {
        if(loadFactor <= 0 || initialCapacity <= 0){
            throw new IllegalArgumentException("Value for load-factor/capacity must be greater than 0");
        }
        if(initialCapacity>MAX_CAPACITY){
            throw new IllegalStateException("Capacity can't be greater than MAX_CAPACITY");
        }
        else {
            if(!isPrime(initialCapacity)){
                initialCapacity = getNextPrime(initialCapacity);
            }
            table = (TableEntry<K, V>[]) new TableEntry[initialCapacity];
            initialized = true;
            numEntries = 0;
            this.loadFactor = loadFactor;

        }
    }

    private static class TableEntry <K, V> {
        private K key;
        private V value;
        private States state; // Flags whether this entry is in the hash table
        private enum States {CURRENT, REMOVED} // Possible values of state

        public TableEntry(K key, V value) {
            this.key = key;
            this.value = value;
            state = States.CURRENT;
        }


        public K getKey() {
            return key;
        }


        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
        public boolean isIn(){
            return state==States.CURRENT;
        }
        public boolean isRemoved(){
            return state == States.REMOVED;
        }
        public void setToRemoved(){
            state = States.REMOVED;
            key = null;
            value = null;
        }
    }
    //****************************KeyIterator**************************
    private class KeyIterator implements Iterator<K>{
        private int currentIndex; // Current position in hash table
        private int numberLeft; // Number of entries left in iteration
        private KeyIterator() {
            currentIndex = 0;
            numberLeft = numEntries;
        }
        public boolean hasNext() {
            return numberLeft > 0;
        }
        public K next() {
            K result = null;
            if (hasNext()) {
            // Skip table locations that do not contain a current entry
                while ((table[currentIndex] == null)
                        || table[currentIndex].isRemoved()){
                    currentIndex++;
                }
                result = table[currentIndex].getKey();
                numberLeft--;
                currentIndex++;
            }
            else
                throw new NoSuchElementException();
            return result;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    //****************************KeyIterator**************************
    private class ValueIterator implements Iterator<V>{
        private int currentIndex; // Current position in hash table
        private int numberLeft; // Number of entries left in iteration
        private ValueIterator() {
            currentIndex = 0;
            numberLeft = numEntries;
        }
        public boolean hasNext() {
            return numberLeft > 0;
        }
        public V next() {
            V result = null;
            if (hasNext()) {
        // Skip table locations that do not contain a current entry
                while ((table[currentIndex] == null)
                        || table[currentIndex].isRemoved()){
                    currentIndex++;
                }
                result = table[currentIndex].getValue();
                numberLeft--;
                currentIndex++;
            }
            else
                throw new NoSuchElementException();
            return result;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    private void checkInitialization() {
        if (!initialized)
            throw new SecurityException ("HashedDictionary object is not initialized properly.");
    }
    private boolean isPrime(int integer) {
        boolean result;
        boolean done = false;
        // 1 and even numbers are not prime
        if ((integer == 1) || (integer % 2 == 0)) {
            result = false;
        }
        // 2 and 3 are prime
        else if ((integer == 2) || (integer == 3)) {
            result = true;
        }
        else { // integer is odd and >= 5
            result = true; // assume prime
            for (int divisor = 3; !done && (divisor * divisor <= integer);
                 divisor = divisor + 2) {
                if (integer % divisor == 0) {
                    result = false; // divisible; not prime
                    done = true;
                }
            }
        }
        return result;
    }
    private int getNextPrime(int integer) {
        // if even, add 1 to make odd
        if (integer % 2 == 0) {
            integer++;
        }
        // test odd integers
        while (!isPrime(integer)) {
            integer = integer + 2;
        }
        return integer;
    }

    @SuppressWarnings("unchecked")
    private void enlargeHashTable() {
        TableEntry<K, V>[] oldTable = table;
        int capacity = getNextPrime(oldTable.length * 2);
        table = (TableEntry<K, V>[]) new TableEntry[capacity];
        numEntries = 0;
        // Rehash dictionary entries from old array to the new
        for (int index = 0; index < oldTable.length; index++) {
            if ((oldTable[index] != null) && oldTable[index].isIn())
                add(oldTable[index].getKey(), oldTable[index].getValue());
        }
    }





    @Override
    public int getSize(){
        return numEntries;
    }
    @Override
    public V add(K key, V value) {
        V oldValue; // value to return if (isHashTableTooFull())
        checkInitialization();
        int index = getHashIndex(key);
        index = linearProbe(index, key); // check for and resolve collision
        // Assertion: index is within legal range for hashTable
        assert (index >= 0) && (index < table.length);
        if ( (table[index] == null) || table[index].isRemoved()) { // key not found, so insert new entry
            table[index] = new TableEntry<K,V>(key, value);
            numEntries++;
            oldValue = null;
        }
        else
        {
            oldValue = table[index].getValue();
            table[index].setValue(value);


        }
        if(isFull()){
            enlargeHashTable();
        }

        // end if return oldValue;
        return table[index].getValue();
    }
    private int getHashIndex(K key) {
        int hashIndex = key.hashCode() % table.length;
        if (hashIndex < 0) {
            hashIndex = hashIndex + table.length;
        }
        return hashIndex;
    }
    private int linearProbe(int index, K keyIn) {
        boolean found = false;
        int removedStateIndex = -1; // first index in removed state
        if(table[index] == null || table[index].getKey() == null)
            return index;
        while (!found && (table[index] != null)) {
            if (table[index].isIn()) {
                if (keyIn.equals(table[index].getKey()))
                    found = true;
                else // Follow probe sequence
                    index = (index + 1) % table.length; // Linear probing
            }
            else { // Skip entries that were removed
                if (removedStateIndex == -1)
                    removedStateIndex = index;
                index = (index + 1) % table.length; // Linear probing
            }
        }
        if (found || (removedStateIndex == -1))
            return index; // Index of either key or null
        else
            return removedStateIndex; // Index of available location
    }



    @Override
    public V getValue(K key) {
        return null;
    }

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public Iterator<K> getKeyIterator() {
        return new KeyIterator();
    }

    @Override
    public Iterator<V> getValueIterator() {
        return new ValueIterator();
    }

    @Override
    public boolean isEmpty() {
        return numEntries==0;
    }

    @Override
    public boolean isFull() {
        return (double) numEntries / table.length >= loadFactor;
    }



    @Override
    public void clear() {

    }
    public String toString() {
        String result = "";
        for(int i = 0; i < table.length; i++) {
            result += i + " ";
            if(table[i] == null)
                result += "null\n";
            else
                result += table[i].getKey() + " "
                        + table[i].getValue() + "\n";
        }
        return result;
    }
    @Override
    public V remove(K key) {
        V removedValue = null;
        int index = getHashIndex(key);
        index = linearProbe(index, key);
        if (index != -1) { // key found; flag entry as removed and return its value
            removedValue = table[index].getValue();
            table[index].setToRemoved();
            numEntries--;
        }
        // else key not found; return null
        return removedValue;
    }



    public static void main(String[] args){
        HashTableOpenAddressing<String, Integer> purchases = new
                HashTableOpenAddressing<String, Integer>();
        String names[] = {"Yuan", "Bobby", "Kevin"};
        purchases.add(names[0], 654);
        purchases.add(names[1], 341);
        purchases.add(names[2], 70);
        System.out.println("Replaced: " + purchases.add(names[1], 170));
        purchases.remove(names[0]);
        purchases.remove(names[2]);

        Iterator<String> keyIter = purchases.getKeyIterator();
        Iterator<Integer> valueIter = purchases.getValueIterator();
        System.out.println("Contents of the hash table:");
        while(keyIter.hasNext()) {
            System.out.println("Key-" + keyIter.next()
                    + " : Value-" + valueIter.next());
        }
//        System.out.println(purchases.toString());
        purchases.add("Robert", 348);
        purchases.add("Sue", 453);
        purchases.add("Mark", 231);
        purchases.add("George", 15);
        System.out.println(purchases.toString());
        System.out.println(purchases.table.length);
        System.out.println(purchases.getSize());
        System.out.println(purchases.isFull());
    }

}
