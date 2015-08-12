/***************************************************************/
/* Timothy Loftis                                              */
/* Login ID: loft3285                                          */
/* CS-203, Summer 2015                                         */
/* Programming Assignment 1                                    */
/* DfsGraph class: This class is meant to hold DfsNodes that   */
/*             all are connected in some manner and is able to */
/*             count and list cycles contained, if any.        */
/***************************************************************/

import java.util.*;

public class DfsGraph{
   private DfsNode[] nodeArray = new DfsNode[1];//holds all nodes in graph
   private ArrayList<DfsNode[]> nodeChainsArray = new ArrayList<>();//Used in recursive functions to hold cycle lists
   private int cycleCount = 0;//used to count cycles
   private int cycleCountTwo = 0;//used to count cycles while counting cycles
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   public DfsGraph(DfsNode startNode){
      nodeArray[0] = startNode;
   }

   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   public DfsGraph(DfsNode[] newNodeArray){
      this.nodeArray = newNodeArray;
   }
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   public void setContainedNodes(DfsNode[] newList){
      nodeArray = newList;
   }
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   public DfsNode[] getNodesInGraph(){
      return nodeArray;
   }
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   public boolean addNewNode(DfsNode newNode){
      if(!nodeExists(newNode.getId())){
         this.addNode(newNode);
         
         return true;
      }
      
      return false;  
   }
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   public boolean nodeExists(String nodeId){
      for (int itter = 0; itter < nodeArray.length; ++itter){
         if(nodeArray[itter].getId() == nodeId){
            return true;
         }
      }
      
      return false;
   }
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
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
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   public DfsNode getNodeById(String nodeId){
      for (int itter = 0; itter < nodeArray.length; ++itter){
         if(nodeArray[itter].getId() == nodeId){
            return nodeArray[itter];
         }
      }
      
      return null;
   }
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   public int countCyclesSelect(DfsNode currentNode){
      cycleCount = 0;
      
      this.countCyclesItter(currentNode, currentNode, false);
      this.unvisitNodes();
      
      //It countss loops moving forward and back, so the number is always double
      return cycleCount/2;
   }
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   public int countCycles(){
      DfsNode currentNode = this.getSmallestNode();
      cycleCount = 0;
      
      this.countCyclesItter(currentNode, currentNode, false);
      this.unvisitNodes();
            
      //It countss loops moving forward and back, so the number is always double
      return cycleCount/2;
   }
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   private void countCyclesItter(DfsNode currentNode, DfsNode previousNode, boolean listCycle){
      if(!this.nodeExists(currentNode.getId())){ return; }
      
      DfsNode[] connectedNodes = currentNode.getOrderedNodes();
      currentNode.visit();
      
      for (int itter = 0; itter < connectedNodes.length; itter++){
         if(!connectedNodes[itter].wasVisited()){
            this.countCyclesItter(connectedNodes[itter], currentNode, listCycle);
         }else{
            if(connectedNodes[itter].getId() != previousNode.getId()){
               cycleCount++;
               
               if(listCycle){
                  this.listCyclesItter(connectedNodes[itter], connectedNodes[itter], connectedNodes[itter], currentNode);
               }
            }
         }
      }
   }
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   public int countCyclesTwo(){
      DfsNode currentNode = this.getSmallestNode();
      cycleCountTwo = 0;
      
      this.countCyclesItterTwo(currentNode, currentNode, false);
      this.unvisitNodesTwo();
            
      //It countss loops moving forward and back, so the number is always double
      return cycleCountTwo/2;
   }
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   private void countCyclesItterTwo(DfsNode currentNode, DfsNode previousNode, boolean listCycle){
      if(!this.nodeExists(currentNode.getId())){ return; }
      
      DfsNode[] connectedNodes = currentNode.getOrderedNodes();
      currentNode.visitTwo();
      
      for (int itter = 0; itter < connectedNodes.length; itter++){
         if(!connectedNodes[itter].wasVisitedTwo()){
            this.countCyclesItterTwo(connectedNodes[itter], currentNode, listCycle);
         }else{
            if(connectedNodes[itter].getId() != previousNode.getId()){
               if(!listCycle){
                  cycleCountTwo++;
               }else{
                  this.listCyclesItter(connectedNodes[itter], connectedNodes[itter], connectedNodes[itter], currentNode);
               }
            }
         }
      }
   }
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   public ArrayList<DfsNode[]> listCycles(){
      cycleCount = 0;
      DfsNode currentNode = this.getSmallestNode();
      
      this.countCyclesItter(currentNode, currentNode, true);
      int listLength = cycleCount/2;
      
      this.unvisitNodes();
      
      while(nodeChainsArray.size() > listLength){
         this.removeLargestArray(nodeChainsArray);
      }
      
      return nodeChainsArray;
   }
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   private ArrayList<DfsNode> nodeChainArray = new ArrayList<>();//used only by this function
   private void listCyclesItter(DfsNode currentNode, DfsNode previousNode, DfsNode startNode, DfsNode finalNode){
      DfsNode[] connectedNodes = currentNode.getOrderedNodes();
      nodeChainArray.add(currentNode);
      
      for (int itter = 0; itter < connectedNodes.length; itter++){
         if(currentNode.getId() != startNode.getId()){
            if(connectedNodes[itter].getId() == finalNode.getId()){
               if(!this.isNodeInChain(finalNode, nodeChainArray)){
                  nodeChainArray.add(finalNode);
                  
                  DfsNode[] tempArray = nodeChainArray.toArray(new DfsNode[nodeChainArray.size()]);
                  addToChainNoDup(tempArray);
                  
                  nodeChainArray.remove(nodeChainArray.size() - 1);
               }
            }
         }
         
         if(!this.isNodeInChain(connectedNodes[itter], nodeChainArray)){
            this.listCyclesItter(connectedNodes[itter], currentNode, startNode, finalNode);
         }
      }
      
      nodeChainArray.remove(nodeChainArray.size() - 1);
   }
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   public boolean isConnected(DfsNode base, DfsNode possibleConnection){
      DfsNode[] connectedList = base.getOrderedNodes();

      for (int itter = 0; itter < connectedList.length; itter++) {
         if(connectedList[itter].getId() == possibleConnection.getId()){
            return true;
         }
      }
      
      return false;
   }
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   private boolean isNodeInChain(DfsNode checkNode, ArrayList<DfsNode> nodeChain){
      for (int itter = 0; itter < nodeChain.size(); itter++){
         DfsNode tempNode = nodeChain.get(itter);
         
         if(tempNode.getId() == checkNode.getId()){
            return true;
         }         
      }
      
      return false;
   }
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   private boolean areDfsArraysEqual(DfsNode[] dfsArrayOne, DfsNode[] dfsArrayTwo){
      if(dfsArrayOne.length != dfsArrayTwo.length){
         return false;
      }
      
      DfsNode[] dfsArrayOneClone = new DfsNode[dfsArrayOne.length];
      DfsNode[] dfsArrayTwoClone = new DfsNode[dfsArrayTwo.length];
      
      for (int itter = 0; itter < dfsArrayOne.length; itter++){
         dfsArrayOneClone[itter] = dfsArrayOne[itter];
         dfsArrayTwoClone[itter] = dfsArrayTwo[itter];
      }
      
      this.sortNodesAlpha(dfsArrayOneClone);
      this.sortNodesAlpha(dfsArrayTwoClone);
      
      for (int itter = 0; itter < dfsArrayOneClone.length; itter++){         
         if(dfsArrayOneClone[itter].getId() != dfsArrayTwoClone[itter].getId()){
            return false;
         }         
      }
      
      return true;
   }
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   private void addNode(DfsNode newNode){
      int newLength = this.nodeArray.length + 1;
      DfsNode[] tempDfsNode = new DfsNode[newLength];

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
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   private void unvisitNodes(){
      for (int itter = 0; itter < nodeArray.length; itter++) {
         nodeArray[itter].resetVisit();
      }
   }
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   private void unvisitNodesTwo(){
      for (int itter = 0; itter < nodeArray.length; itter++) {
         nodeArray[itter].resetVisitTwo();
      }
   }
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   private void addToChainNoDup(DfsNode[] tempArray){
      DfsGraph tempGraph = new DfsGraph(tempArray[0]);
      tempGraph.setContainedNodes(tempArray);
      
      if(tempGraph.countCyclesTwo() != 1){
         return;
      }
      
      for (int itter = 0; itter < nodeChainsArray.size(); itter++){         
         if(this.areDfsArraysEqual(tempArray, nodeChainsArray.get(itter))){
            return;
         }         
      }
      
      nodeChainsArray.add(tempArray);
   }
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   private DfsNode getSmallestNode(){
      DfsNode minNode = nodeArray[0];
      
      for (int itter = 0; itter < nodeArray.length; itter++) {
         if(!(minNode.getId().compareTo(nodeArray[itter].getId()) < 0)){
            minNode = nodeArray[itter];
         }
      }
      
      return minNode;
   }
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   private DfsNode[] sortNodesAlpha(DfsNode[] arrayOfNodes){
      DfsNode tempDfsNode; 
      int len = arrayOfNodes.length;
      
      for (int outItter = 0; outItter < len; outItter++) {
         for (int itter = (outItter + 1); itter < len; itter++) {
            if(arrayOfNodes[itter].getId().compareTo(arrayOfNodes[outItter].getId())<0){
               tempDfsNode = arrayOfNodes[outItter];
               arrayOfNodes[outItter] = arrayOfNodes[itter]; 
               arrayOfNodes[itter] = tempDfsNode;
            }
         }

      }
      
      return arrayOfNodes;
   }
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   private void removeLargestArray(ArrayList<DfsNode[]> dfsArrayList){
      int largestEle = 0;
      for (int itter = 0; itter < dfsArrayList.size(); itter++) {
         if(dfsArrayList.get(itter).length > dfsArrayList.get(largestEle).length){
            largestEle = itter;
         }
      }
      
      dfsArrayList.remove(largestEle);
   }
}

