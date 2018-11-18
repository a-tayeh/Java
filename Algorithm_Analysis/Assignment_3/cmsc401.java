import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.LinkedList;
import javafx.util.Pair;
import java.util.Scanner;

@SuppressWarnings("unchecked")
public class cmsc401 {
    static class V{
        int vertices;

        public LinkedList<E>[] getEdgesList() {
            return edgesList;
        }

        LinkedList<E> edgesList [];
        HashMap<Integer,Integer> gasAndMotelCost = new HashMap<>();

        public int[] getDistanceTable() {
            return distanceTable;
        }

        int [] distanceTable;


        public V(int vertices){
            this.vertices = vertices;
            edgesList = new LinkedList[vertices];

            for(int i = 0;i<edgesList.length;i++){
                edgesList[i]= new LinkedList<>();
            }
        }

        void addEdges(int source, int destination, int weight){
            E edge = new E(source, destination, weight);
            edgesList[source].addFirst(edge);

            E edge2 = new E(destination, source, weight);
            edgesList[destination].addFirst(edge2);




        }
        void addGasAndMotelCost(int source, int gasCost){
            gasAndMotelCost.put(source,gasCost);
        }





        public void dijkstraWithGasAndMotel(int sourceVertex){

            boolean[] finalizedVertices = new boolean[vertices];

            distanceTable = new int[vertices];
            final int INF = Integer.MAX_VALUE;
            for (int i = 0; i <edgesList.length ; i++) {
                distanceTable[i] = INF;
            }

            PriorityQueue<Pair<Integer, Integer>> visitedVerticesQ = new
                    PriorityQueue<>(vertices,(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) -> p1.getKey()-p2.getKey());
            distanceTable[0] = 0;

            Pair<Integer, Integer> sourcePairDistance = new Pair<>(distanceTable[0],0);
            visitedVerticesQ.offer(sourcePairDistance);

            while(!visitedVerticesQ.isEmpty()){
                Pair<Integer, Integer> minPair = visitedVerticesQ.poll();

                int minPairValue = minPair.getValue();
                if(finalizedVertices[minPairValue]==false) {

                    //iterate through all the adjacent vertices and update the keys
                    LinkedList<E> tempEdgeList = edgesList[minPairValue];
                    for (int i = 0; i < tempEdgeList.size(); i++) {
                        E edge = tempEdgeList.get(i);
                        int destination = edge.edgeDestination;
                        //only if edge edgeDestination is not present in mst
                        if (finalizedVertices[destination] == false) {

                            int newDistance ;
                            if(gasAndMotelCost.containsKey(destination)){
                                 newDistance =  distanceTable[minPairValue] + edge.edgeWeight + gasAndMotelCost.get(destination);
                            }
                            else {
                                 newDistance = distanceTable[minPairValue] + edge.edgeWeight;
                            }
                            int currentDistance = distanceTable[destination];
                            if(currentDistance>newDistance){

                                Pair<Integer, Integer> updatedDistance = new Pair<>(newDistance, destination);
                                visitedVerticesQ.offer(updatedDistance);
                                distanceTable[destination] = newDistance;
                            }
                        }
                    }
                }
            }
        }



        HashMap<Integer, Integer> getGasAndMotelCost(){
            return gasAndMotelCost;
        }


    }


    static class E{
        int edgeWeight;
        int edgeDestination;
        int edgeSource;



        public E(int source, int edgeDestination, int edgeWeight) {
            this.edgeWeight = edgeWeight;
            this.edgeDestination = edgeDestination;
            this.edgeSource = source;
        }
    }

    public static void main(String [] args){
        Scanner in = new Scanner(System.in);
        int numOfVertices = in.nextInt();
        V graph = new V(numOfVertices);

        int numOfEdges = in.nextInt();
        in.nextLine();
        int counter = 0;
        while(counter < numOfEdges){
            String edges = in.nextLine();
            String []parts = edges.split("\\s");
            if(parts.length==2) {
                graph.addGasAndMotelCost(Integer.parseInt(parts[0])-1,Integer.parseInt(parts[1]));
            }
            else {
                graph.addEdges(Integer.parseInt(parts[0])-1, Integer.parseInt(parts[1])-1, Integer.parseInt(parts[2]));
                counter++;
            }

        }


        graph.dijkstraWithGasAndMotel(0);
        System.out.println(graph.getDistanceTable()[1]);

    }
}
