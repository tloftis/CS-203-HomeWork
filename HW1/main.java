import java.util.*;

public class main
{
	public static void main(String[] args) {
      
      DfsCollection nodeCollection = new DfsCollection();
      
      DfsNode DfsNodeA = nodeCollection.addNewNode("a");
      DfsNode DfsNodeB = nodeCollection.addNewNode("b");
      DfsNode DfsNodeC = nodeCollection.addNewNode("c");
      DfsNode DfsNodeD = nodeCollection.addNewNode("d");
      DfsNode DfsNodeE = nodeCollection.addNewNode("e");
      DfsNode DfsNodeF = nodeCollection.addNewNode("f");
      DfsNode DfsNodeG = nodeCollection.addNewNode("g");
      DfsNode DfsNodeH = nodeCollection.addNewNode("h"); 
      DfsNode DfsNodeI = nodeCollection.addNewNode("i");
      
      nodeCollection.connectNodes(DfsNodeA, DfsNodeB);
      nodeCollection.connectNodes(DfsNodeB, DfsNodeD);
      //nodeCollection.connectNodes(DfsNodeB, DfsNodeE);
      //nodeCollection.connectNodes(DfsNodeD, DfsNodeE);
      nodeCollection.connectNodes(DfsNodeD, DfsNodeA);
      nodeCollection.connectNodes(DfsNodeE, DfsNodeG);
      //nodeCollection.connectNodes(DfsNodeG, DfsNodeF);
      nodeCollection.connectNodes(DfsNodeF, DfsNodeD);
      nodeCollection.connectNodes(DfsNodeF, DfsNodeA);
      nodeCollection.connectNodes(DfsNodeF, DfsNodeH);
      nodeCollection.connectNodes(DfsNodeH, DfsNodeA);
      //nodeCollection.connectNodes(DfsNodeG, DfsNodeI);
      
      //nodeCollection.connectNodes(DfsNodeC, DfsNodeD);
      nodeCollection.connectNodes(DfsNodeC, DfsNodeE);
      //nodeCollection.connectNodes(DfsNodeC, DfsNodeF);
      nodeCollection.connectNodes(DfsNodeC, DfsNodeG);
      
      //DfsNode[] nodesInGraph = graph.getNodesInGraph();
      DfsGraph[] graphsInCollection = nodeCollection.findGraps();
            
      String graphCountLine = "";
      String cycleCountLine = "";
      int totalCycles = 0;
            
      System.out.println("-------------------------------------");
      System.out.println("Collection 1:");
      
      graphCountLine = graphsInCollection.length + " Connected Tree(s) Found: ";
      
      for (int itterOut = 0; itterOut < graphsInCollection.length; itterOut++) {
         DfsNode[] currentNodeList = graphsInCollection[itterOut].getNodesInGraph();
         totalCycles += graphsInCollection[itterOut].countCycles();
         
         graphCountLine += "{" + currentNodeList[0].getId();
         
         for (int itter = 1; itter < currentNodeList.length; itter++) {
            graphCountLine += ", " + currentNodeList[itter].getId();
         }
         
         ArrayList<DfsNode[]> nodeChains = graphsInCollection[itterOut].listCycles();

         for (int itter = 0; itter < nodeChains.size(); itter++){
            DfsNode[] tempNodes = nodeChains.get(itter);
            cycleCountLine += "(" + tempNodes[0].getId();
            
            for (int itterInner = 1; itterInner < tempNodes.length; itterInner++){
               DfsNode tempNode = tempNodes[itterInner];
               cycleCountLine += "-" + tempNode.getId();
            }
            cycleCountLine += "-" + tempNodes[0].getId();
            
            cycleCountLine += ") ";
         }
         
         graphCountLine += "} ";
      }
      
      System.out.println(graphCountLine);
      
      if(totalCycles == 0){
         System.out.println("The collection is acyclic.");
      }else{
         System.out.println(totalCycles + " cycles found: " + cycleCountLine);
      }
      System.out.println("-------------------------------------");
	}
}

