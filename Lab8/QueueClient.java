public class QueueClient {
    public static void main(String[]args){
        CircularQueue<Object> obj = new CircularQueue<>(2);
        obj.enqueue("ali");
        obj.enqueue("ali");
        obj.enqueue("ali");
        obj.enqueue("ali");


//        System.out.println(obj.dequeue());

//        System.out.println(obj.isEmpty());
        for(Object a : obj.toArray()){
            if(a!=null) {
                System.out.println(a + " ");
            }
        }

    }
}
