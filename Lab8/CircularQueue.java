import java.util.NoSuchElementException;

public class CircularQueue<T> {
    private T[] queue ;
    private int front;
    private int rear;
    private int specifiedCapacity;
    private final static int DEFAULT_CAPACITY = 10;

    public CircularQueue(){
        this(DEFAULT_CAPACITY);
        this.rear = 0;
        this.front = 0;

    }

    public CircularQueue(int specifiedCapacity) {
        if(specifiedCapacity>DEFAULT_CAPACITY){
            throw new IllegalArgumentException("specified capacity is larger than defautl-capacity of: "+DEFAULT_CAPACITY);
        }
        this.specifiedCapacity = specifiedCapacity;
        this.rear = 0;
        this.front = 0;
        @SuppressWarnings("unchecked")
        T[] tempQueue = (T[]) new Object[specifiedCapacity+1];
        queue = tempQueue;
        front = 0;
        rear = specifiedCapacity;

    }
    public void enqueue(T entry){
        if(size()==DEFAULT_CAPACITY){
            System.out.println("You've reached max capacity");
            return;
        }
        checkCapacity();
        rear = (rear + 1) % queue.length;
        queue[rear] = entry;

    }
    public T dequeue() {
        if(isEmpty()){
            throw new NoSuchElementException("The queue is empty!");
        }
        T tempFront = null;
        if (!isEmpty()) {
            tempFront = queue[front+1];
            queue[front+1] = null;
            front = (front + 1) % queue.length;
        }
        return tempFront;
    }


    public void checkCapacity(){

        if (rear == queue.length) {
            rear = 0;
        }
            if (front == ((rear + 2) % queue.length))
            {
                T[] oldQueue = queue;
                int oldSize = oldQueue.length;

                @SuppressWarnings("unchecked")
                T[] tempQueue = (T[]) new Object[2 * oldSize];
                queue = tempQueue;
                for (int index = 0; index < oldSize - 1; index++) {
                    queue[index] = oldQueue[front];
                    front = (front + 1) % oldSize;
                }
                front = 0;
                rear = oldSize - 2;
            }
        }


    public boolean isEmpty(){
         return front == ((rear + 1) % queue.length);
    }

    public T[] toArray(){
        return queue;
    }
    public int size(){
        int total = 0;
        for(int i = 0;i<queue.length;i++){
            if(queue[i]!=null){
                total++;
            }
        }
        return total;
    }


}
