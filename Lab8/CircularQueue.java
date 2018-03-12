public class CircularQueue<T> {
    private T[] queue;
    private int front;
    private int rear;
    private int specifiedCapacity;
    private final static int DEFAULT_CAPACITY = 10;


    public CircularQueue(int specifiedCapacity) {
        this.specifiedCapacity = specifiedCapacity;
        this.rear = 0;
        this.front = 0;
    }
    public CircularQueue(){
        this(DEFAULT_CAPACITY);

    }
    public void enqueue(T entry){
        if(isEmpty()){
            @SuppressWarnings("unchecked")
            T[] tempQueue = (T[]) new Object[specifiedCapacity + 1];
            queue = tempQueue;
            front = 0;
            rear = specifiedCapacity;
            queue[front] = entry;
            rear++;
        }
        checkCapacity();
        queue[front] = entry;
        rear++;
        if (rear == queue.length) {
            rear = 0; }

    }
    public T dequeue() {
        T tempFront = null;
        if (!isEmpty()) {
            tempFront = queue[front];
            queue[front] = null;
            front = (front + 1) % queue.length;
        }
        return tempFront; }


    public void checkCapacity(){
            if (front == ((rear + 2) % queue.length))
            {
                T[] oldQueue = queue;
                int oldSize = oldQueue.length;

                @SuppressWarnings("unchecked")
                T[] tempQueue = (T[]) new Object[2 * oldSize]; queue = tempQueue;
                for (int index = 0; index < oldSize - 1; index++) {
                    queue[index] = oldQueue[front];
                    front = (front + 1) % oldSize;
                }
                front = 0;
                rear = oldSize - 2;
            }
        }


    public boolean isEmpty(){
        return front == ((rear+1)% queue.length);
    }


}
