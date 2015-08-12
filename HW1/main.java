import java.util.*;

public class main
{
	public static void main(String[] args) {
      DfsNode DfsNodeA = new DfsNode("a");
      DfsNode DfsNodeB = new DfsNode("b");
      DfsNode DfsNodeC = new DfsNode("c");
      DfsNode DfsNodeD = new DfsNode("d");
      DfsNode DfsNodeE = new DfsNode("e");
      DfsNode DfsNodeF = new DfsNode("f");
      DfsNode DfsNodeG = new DfsNode("g");
      DfsNode DfsNodeH = new DfsNode("h"); 
      DfsNode DfsNodeI = new DfsNode("i");
      
      DfsGraph graph = new DfsGraph(DfsNodeA);
      
      graph.connectNodes(DfsNodeA, DfsNodeB);
      graph.connectNodes(DfsNodeB, DfsNodeD);
      graph.connectNodes(DfsNodeB, DfsNodeE);
      graph.connectNodes(DfsNodeD, DfsNodeE);
      graph.connectNodes(DfsNodeD, DfsNodeA);
      graph.connectNodes(DfsNodeE, DfsNodeG);
      graph.connectNodes(DfsNodeG, DfsNodeF);
      graph.connectNodes(DfsNodeF, DfsNodeD);
      graph.connectNodes(DfsNodeF, DfsNodeA);
      graph.connectNodes(DfsNodeF, DfsNodeH);
      graph.connectNodes(DfsNodeH, DfsNodeA);
      graph.connectNodes(DfsNodeG, DfsNodeI);
      
      graph.connectNodes(DfsNodeC, DfsNodeD);
      graph.connectNodes(DfsNodeC, DfsNodeE);
      graph.connectNodes(DfsNodeC, DfsNodeF);
      graph.connectNodes(DfsNodeC, DfsNodeG);
      
      DfsNode[] nodesInGraph = graph.getNodesInGraph();
      
      System.out.println(graph.countCycles());
      
      System.out.println("-------------------------------------");
      graph.listCycles(DfsNodeA);
      System.out.println("-------------------------------------");
      
      for (int itterOut = 0; itterOut < nodesInGraph.length; itterOut++) {
         DfsNode[] currentNodeList = nodesInGraph[itterOut].getOrderedNodes();
         String linked = nodesInGraph[itterOut].getId();
         
         for (int itter = 0; itter < currentNodeList.length; itter++) {
            linked += " - " + currentNodeList[itter].getId();
         }
         
         System.out.println(linked);
      }
      
      //for (int itter = 0; itter < listB.length; itter++) {
      //    System.out.println(listB[itter].getId());
      //}
	}
}

