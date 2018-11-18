/*
*               Ali Tayeh                           Assignment_3                        CMSC_401
*
* */

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.LinkedList;
import javafx.util.Pair;
import java.util.Scanner;

@SuppressWarnings("unchecked")
public class cmsc401 {
    static class V{
        private int vertices;
        private LinkedList<E> edgesList [];
        private HashMap<Integer,Integer> gasAndMotelCost = new HashMap<>();
        private int [] distanceTable;
        private boolean [] finalizedVertices;



        public V(int vertices){
            this.vertices = vertices;
            edgesList = new LinkedList[vertices];

            for(int i = 0;i<edgesList.length;i++){
                edgesList[i]= new LinkedList<>();
            }
        }



        public void addEdges(int source, int destination, int weight){
            E goingOutEdge = new E(source, destination, weight);
            edgesList[source].addFirst(goingOutEdge);

            E goingBackInEdge = new E(destination, source, weight);
            edgesList[destination].addFirst(goingBackInEdge);

        }

        public void initializeDistanceTable(){
            finalizedVertices = new boolean[vertices];
            distanceTable = new int[vertices];
            final int INF = Integer.MAX_VALUE;
            for (int i = 0; i <edgesList.length ; i++) {
                distanceTable[i] = INF;
            }
        }
        public HashMap<Integer, Integer> getGasAndMotelCost(){
            return gasAndMotelCost;
        }
        public void addGasAndMotelCost(int source, int gasCost){
            gasAndMotelCost.put(source,gasCost);
        }

        public LinkedList<E>[] getEdgesList() {
            return edgesList;
        }
        public int[] getDistanceTable() {
            return distanceTable;
        }

        public void dijkstraWithGasAndMotel(int source){
            /*
                Creates an array that will store finalizedNodes after their shortest distance has been calculated,
                Creates a distance table where temporary and final distances will be stored
                Initializes all initial distances, except the source, as infinity
            */
            initializeDistanceTable();
            /*
                PriorityQueue that will store pairs of vertices and their respected values,
                the PQ uses a lambda comparator that returns the vertex with shortest traveling value(including gasAndMotel value),
                initializes the distance from source to itself as zero in distance tavle
            */
            PriorityQueue<Pair<Integer, Integer>> visitedVerticesQ = new
                    PriorityQueue<>(vertices,(Pair<Integer, Integer> firstPair, Pair<Integer, Integer> secondPair) -> firstPair.getKey()-secondPair.getKey());
            distanceTable[0] = 0;

            Pair<Integer, Integer> sourcePairDistance = new Pair<>(distanceTable[0],0);
            visitedVerticesQ.offer(sourcePairDistance);
            /*
            * As long as our visitedVerticesQ is not empty, then check and calculate distance between all vertices and once a vertex has
            * the shortest path then finalize it by adding it to finalizedVertices array, dequeue it from priority queue then repeat the same
            * process onto the next item in the PQ
            *
            * When calculating the edge weights, call gasAndMotelCost to see if it contains a key(motel/gas cost) for the passed in destination,
            * if there is one then add the gas/motel cost to the edge weight, otherwise, only add the edge weight to calculate new distance.
            *
            * if current distance in distance table is greater than new distance after the calculation and once a vertex is finalized,
            * update the distance table with new distance
            * */
            while(!visitedVerticesQ.isEmpty()){
                Pair<Integer, Integer> minPair = visitedVerticesQ.poll();

                int minPairValue = minPair.getValue();
                if(finalizedVertices[minPairValue]==false) {

                    LinkedList<E> tempEdgeList = edgesList[minPairValue];
                    for (int i = 0; i < tempEdgeList.size(); i++) {
                        E edge = tempEdgeList.get(i);
                        int destination = edge.edgeDestination;
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






    }


    public static class E{
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
        V undirectedDijkstraGraph = new V(numOfVertices);

        int numOfEdges = in.nextInt();
        in.nextLine();
        int counter = 0;
        while(counter < numOfEdges){
            String edges = in.nextLine();
            String [] parts = edges.split("\\s");
            if(parts.length==2) {
                undirectedDijkstraGraph.addGasAndMotelCost(Integer.parseInt(parts[0])-1,Integer.parseInt(parts[1]));
            }
            else {
                undirectedDijkstraGraph.addEdges(Integer.parseInt(parts[0])-1, Integer.parseInt(parts[1])-1, Integer.parseInt(parts[2]));
                counter++;
            }

        }
        undirectedDijkstraGraph.dijkstraWithGasAndMotel(0);
        System.out.println(undirectedDijkstraGraph.getDistanceTable()[1]);

    }
}
