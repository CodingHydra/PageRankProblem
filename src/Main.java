import java.util.ArrayList;
import java.util.LinkedList;

class Calculate {
    //testing

    Graph graph;
    double[] rank;
    int numVerters;
    double dampingFactor;
    Calculate(){
        dampingFactor = 0.85;
        graph = new Graph();
        //
        graph.addNode(new Node('A'));
        graph.addNode(new Node('B'));
        graph.addNode(new Node('C'));
        graph.addNode(new Node('D'));
        graph.addNode(new Node('E'));
        //
        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(1,4);
        graph.addEdge(2,3);
        graph.addEdge(2,4);
        graph.addEdge(4,0);
        graph.addEdge(4,2);
        //
        numVerters = 5;
        rank = new double[numVerters];

    }

    public void bruh() {
        int outDegree;
        int nodeMax = graph.alist.toArray().length - 1;


        //initalScore
        for (int i = 0; i < rank.length; i++) {
            rank[i] = 1.0 / numVerters;
        }
        //
        double baseAmount = (1 - 0.85) / numVerters;
        //checking for number of outdegree edges

        /*
        1.iterate every LinkedList in alist to check if a linkedList contain node N. (not itself)
            1.1 use a for loop to iterate through every alist link
            1.2


        2.if that list contains n then get the source node of that list and length of linkedList -1;
        3.get the rank of the source node and divide it by number of nodes in that list -1.
        4.store that in a arraylist and increment
        5.add all the elements in that arraylist
        6.multilpy the total of the elements by damping factor
        7.add baseamount
        8.

         */
//testing this should only appear on test branch
        //step1.
        int iteration = 1;
        int currentID = 0;
        Node sourceNode = null;
        for (int j = 0; j <= 20; j++) {
            while (currentID < graph.alist.toArray().length) {
                ArrayList<Double> related = new ArrayList<>();
                for (int i = 0; i < graph.alist.toArray().length; i++) {
                    double prS;
                    int numberOfOutGoingLinks;
                    if (i != currentID) {
                        sourceNode = graph.alist.get(currentID).get(0);
                        LinkedList<Node> currentList = graph.alist.get(i);
                        for (Node node : currentList) {
                            if (node == sourceNode) {
                                prS = rank[currentID];
                                numberOfOutGoingLinks = currentList.size() - 1;
                                related.add(prS / numberOfOutGoingLinks);
                            }
                        }
                    }
                }
//step 5
                double totalRightSide = 0;
                for (double num : related) {
                    totalRightSide += num;
                }
//step 6
                totalRightSide = totalRightSide * dampingFactor;
//step 7
                double prD = baseAmount + totalRightSide;
                rank[currentID] = prD;
                currentID++;
                System.out.println();
                System.out.printf("PR of page %d  = %f  Node %c", currentID, prD, sourceNode.data);
            }
            System.out.println();
            System.out.println("Iteration "+iteration);
            ++iteration;
            currentID = 0;
        }
    }


}



public class Main {

    public static void main(String[]args){
       Calculate cal = new Calculate();
       cal.graph.print();
       cal.bruh();
    }
}
