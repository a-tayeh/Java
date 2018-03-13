import java.util.NoSuchElementException;

public class CircularQueue<T> {
    private T[] queue ;
    private int front;
    private int rear;
    private int specifiedCapacity;
    private final static int DEFAULT_CAPACITY = 10;


    public CircularQueue(int specifiedCapacity) {
        this.specifiedCapacity = specifiedCapacity;
        this.rear = 0;
        this.front = 0;
        @SuppressWarnings("unchecked")
        T[] tempQueue = (T[]) new Object[specifiedCapacity];
        queue = tempQueue;
        front = 0;
        rear = specifiedCapacity;

    }
    public CircularQueue(){
        this(DEFAULT_CAPACITY);
        this.rear = 0;
        this.front = 0;

    }
    public void enqueue(T entry){
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


}
