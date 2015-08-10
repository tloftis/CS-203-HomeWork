import java.util.*;

public class DfsGraph{
   private DfsNode[] nodeArray = new DfsNode[1];
   private int cycleCount = 0;
   private int timesCycled = 0;
   private String Chain = "";
   
   
   public DfsGraph(DfsNode startNode){
      nodeArray[0] = startNode;
   }
   
   public DfsNode[] getNodesInGraph(){
      return nodeArray;
   }
   
   public boolean addNewNode(DfsNode newNode){
      if(!nodeExists(newNode.getId())){
         this.addNode(newNode);
         
         return true;
      }else{
         return false;      
      }
   }
   
   public boolean nodeExists(String nodeId){
      for (int itter = 0; itter < nodeArray.length; ++itter){
         if(nodeArray[itter].getId() == nodeId){
            return true;
         }
      }
      
      return false;
   }
   
   public void connectNodes(DfsNode node1, DfsNode node2){
      if(!nodeExists(node1.getId())){
         this.addNode(node1);
      }
      if(!nodeExists(node2.getId())){
         this.addNode(node2);
      }
      
      node1.connectNode(node2);
      node2.connectNode(node1);
   }
   
   public DfsNode getNodeById(String nodeId){
      for (int itter = 0; itter < nodeArray.length; ++itter){
         if(nodeArray[itter].getId() == nodeId){
            return nodeArray[itter];
         }
      }
      
      return null;
   }
   
   public int countCycles(DfsNode currentNode){
      cycleCount = 0;
      Chain = "Start";
      
      this.countCyclesItter(currentNode, currentNode, false);
      this.unvisitNodes();
      
      System.out.println(Chain);
      
      return cycleCount/2;
   }
   
   private void countCyclesItter(DfsNode currentNode, DfsNode previousNode, boolean listCycle){
      DfsNode[] connectedNodes = currentNode.getOrderedNodes();
      timesCycled++;
      currentNode.visit();
      Chain += " - " + currentNode.getId();
      
      for (int itter = 0; itter < connectedNodes.length; itter++){
         if(!connectedNodes[itter].wasVisited()){
            this.countCyclesItter(connectedNodes[itter], currentNode, listCycle);
         }else{
            if(connectedNodes[itter].getId() != previousNode.getId()){
               if(!listCycle){
                  cycleCount++;
                  System.out.println(currentNode.getId() + " - " + connectedNodes[itter].getId() + ":" + timesCycled);
               }else{
                  Chain = "";
                  listCyclesItter(connectedNodes[itter], currentNode);
                  System.out.println(Chain);
               }
               
            }
         }
      }
   }
   
   public void listCycles(DfsNode currentNode){
      cycleCount = 0;
      Chain = "Start";
      
      this.countCyclesItter(currentNode, currentNode, true);
      this.unvisitNodes();
   }
   
   private boolean listCyclesItter(DfsNode currentNode, DfsNode finalNode, DfsNode startNode){
      DfsNode[] connectedNodes = currentNode.getOrderedNodes();
      Chain += " - " + currentNode.getId();
      
      for (int itter = 0; itter < connectedNodes.length; itter++){         
         if(this.isConnected(currentNode, finalNode)){
            return true;
         }
         
         if(connectedNodes[itter] == startNode.getId()){
            Chain = startNode.getId();
            break;
         }
         
         if(connectedNodes[itter].wasVisited()){
            return this.countCyclesItter(connectedNodes[itter], finalNode, listCycle);
         }
      }
      
      return false;
   }
   
   public boolean isConnected(DfsNode base, DfsNode possibleConnection){
      DfsNode[] connectedList = base.getOrderedNodes();

      for (int itter = 0; itter < connectedList.length; itter++) {
         if(connectedList[itter].getId() == possibleConnection.getId()){
            return true;
         }
      }
      
      return false;
   }
   
   private void addNode(DfsNode newNode){
      int newLength = this.nodeArray.length + 1;
      DfsNode[] tempDfsNode= new DfsNode[newLength];

      for (int itter = 0; itter < (newLength - 1); itter++) {
         tempDfsNode[itter] = this.nodeArray[itter];
      }
      
      if(newNode.getId().compareTo(nodeArray[0].getId()) < 0){
         tempDfsNode[(newLength - 1)] = nodeArray[0];
         nodeArray[0] = newNode; 
      }else{
         tempDfsNode[newLength - 1] = newNode;
      }
      
      this.nodeArray = tempDfsNode;
   }
   
   private void unvisitNodes(){
      for (int itter = 0; itter < nodeArray.length; itter++) {
         nodeArray[itter].resetVisit();
      }
   }
}

