import java.util.*;

public class DfsGraph{
   private DfsNode[] nodeArray = new DfsNode[1];
   
   public DfsGraph(DfsNode startNode){
      nodeArray[0] = startNode;
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

