public class QueueClient {
    public static void main(String[]args){
        CircularQueue<Object> obj = new CircularQueue<>(4);
        obj.enqueue("Red");
        obj.enqueue("Yellow");
        obj.enqueue("Green");
        obj.enqueue("Blue");
        obj.enqueue("Purple");
        obj.enqueue("Orange");
        obj.enqueue("White");
        obj.dequeue();
        obj.dequeue();
        obj.enqueue("Gray");
        System.out.println(obj.isFull());









        for(Object a : obj.toArray()){
            if(a!=null) {
                System.out.println(a + " ");
            }
        }
        System.out.println(obj.size());

    }
}
