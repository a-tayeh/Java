public class QueueClient {
    public static void main(String[]args){
        CircularQueue<Object> obj = new CircularQueue<>(2);
        obj.enqueue("ali");
        obj.enqueue("saleh");
        obj.enqueue("tayeh");
        obj.enqueue("faraj");




//        System.out.println(obj.isEmpty());
        for(Object a : obj.toArray()){
            if(a!=null) {
                System.out.println(a + " ");
            }
        }

    }
}
