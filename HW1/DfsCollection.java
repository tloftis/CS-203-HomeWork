/***************************************************************/
/* Timothy Loftis                                              */
/* Login ID: loft3285                                          */
/* CS-203, Summer 2015                                         */
/* Programming Assignment 1                                    */
/* DfsCollection class: This will hold a bunch of DfsNodes and */
/*             will find any graphs found inside of the nodes  */
/*             As well as be the main interface API            */
/***************************************************************/

import java.util.*;

public class DfsCollection{
   private DfsNode[] nodeArray = new DfsNode[0];
   private int cycleCount = 0;

   /***************************************************************/
   /* Method: DfsCollection                                       */
   /* Purpose: Make a new graph with just one node                */
   /* Parameters:                                                 */
   /* DfsNode initNode: the node that you want to start the graph */
   /* Returns: DfsCollection: a new instance of a DfsCollection   */
   /***************************************************************/
   public DfsCollection(DfsNode initNode){
      this.addNode(initNode);
   }
   
   /***************************************************************/
   /* Method: DfsCollection                                       */
   /* Purpose: Make a new graph with no nodes                     */
   /* Parameters:                                                 */
   /* Returns: DfsCollection: a new instance of a DfsCollection   */
   /***************************************************************/
   public DfsCollection(){
      return;
   }

   /***************************************************************/
   /* Method: getNodesInCollection                                */
   /* Purpose: get the array of nodes in the collection           */
   /* Parameters:                                                 */
   /* Returns: DfsNode[]: array of contained nodes                */
   /***************************************************************/
   public DfsNode[] getNodesInCollection(){
      return nodeArray;
   }

   /***************************************************************/
   /* Method: addDfsNodeArray                                     */
   /* Purpose: Adds an array of DfsNodes to the collection        */
   /* Parameters:                                                 */
   /* DfsNode[] newNodeArray: array to add nodes from             */
   /* Returns: void: just adds the array internally               */
   /***************************************************************/
   public void addDfsNodeArray(DfsNode[] newNodeArray){
      for (int itter = 0; itter < nodeArray.length; itter++) {
         this.addNode(newNodeArray[itter]);
      }
   }

   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   public boolean nodeExists(String nodeId){
      for (int itter = 0; itter < nodeArray.length; ++itter){
         if(nodeArray[itter].getId().equals(nodeId)){
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
   public DfsNode addNewNode(String nodeId){
      DfsNode newNode = new DfsNode(nodeId);
      
      if(!this.nodeExists(nodeId)){
         this.addNode(newNode);
      }
      
      return newNode;
   }

   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   public DfsGraph[] findGraps(){
      ArrayList<DfsNode[]> nodeChainArray = new ArrayList<>();
      ArrayList<DfsGraph> graphArrayList = new ArrayList<>();
      
      for (int itter = 0; itter < nodeArray.length; itter++) {
         addToChainNoDup(this.traceNodeConnection(nodeArray[itter]), nodeChainArray);
      }
      
      //put all graphs found in the connected nodes into a array list or DfsGraphs
      for (int itter = 0; itter < nodeChainArray.size(); itter++) {
         DfsNode[] tempList = nodeChainArray.get(itter);
         graphArrayList.add(new DfsGraph(tempList));
      }
      
      return(graphArrayList.toArray(new DfsGraph[graphArrayList.size()]));
   }

   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   public DfsNode[] traceNodeConnection(DfsNode currentNode){      
      ArrayList<DfsNode> tempNodeChain = new ArrayList<>();
      
      this.traceNodeConnectionItter(currentNode, currentNode, tempNodeChain);
      this.unvisitNodes();
      
      return tempNodeChain.toArray(new DfsNode[tempNodeChain.size()]);
   }

   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   private void traceNodeConnectionItter(DfsNode currentNode, DfsNode previousNode, ArrayList<DfsNode> tempNodeChain){
      if(!this.nodeExists(currentNode.getId())){ return; }
      
      tempNodeChain.add(currentNode);
      
      DfsNode[] connectedNodes = currentNode.getOrderedNodes();
      currentNode.visit();
      
      for (int itter = 0; itter < connectedNodes.length; itter++){
         if(!connectedNodes[itter].wasVisited()){
            this.traceNodeConnectionItter(connectedNodes[itter], currentNode, tempNodeChain);
         }
      }
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
         if(nodeArray[itter].getId().equals(nodeId)){
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
   private void addNode(DfsNode newNode){
      int newLength = this.nodeArray.length + 1;
      DfsNode[] tempDfsNode = new DfsNode[newLength];

      for (int itter = 0; itter < (newLength - 1); itter++) {
         tempDfsNode[itter] = this.nodeArray[itter];
      }
      
      tempDfsNode[newLength - 1] = newNode;
      
      this.nodeArray = tempDfsNode;
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
      
      //have to clone the arrays to preserve the order they appear in orgionally
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
   private void addToChainNoDup(DfsNode[] tempArray, ArrayList<DfsNode[]> nodeChainArray){      
      for (int itter = 0; itter < nodeChainArray.size(); itter++){         
         if(this.areDfsArraysEqual(tempArray, nodeChainArray.get(itter))){
            return;
         }         
      }
      
      nodeChainArray.add(tempArray);
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
}

