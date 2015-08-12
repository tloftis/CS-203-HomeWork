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
   /* Method: DfsGraph                                            */
   /* Purpose: Create a graph with just one node                  */
   /* Parameters:                                                 */
   /* DfsNode startNode: single node that would be in the graph   */
   /* Returns: DfsGraph: Returns a graph with one node            */
   /***************************************************************/
   public DfsGraph(DfsNode startNode){
      nodeArray[0] = startNode;
   }

   /***************************************************************/
   /* Method: DfsGraph                                            */
   /* Purpose: Create a graph from an array of nodes              */
   /* Parameters:                                                 */
   /* DfsNode[] newNodeArray: Array of nodes to add to graph      */
   /* Returns: DfsGraph: Returns a graph with all nodes in array  */
   /***************************************************************/
   public DfsGraph(DfsNode[] newNodeArray){
      this.nodeArray = newNodeArray;
   }
   
   /***************************************************************/
   /* Method: setContainedNodes                                   */
   /* Purpose: Change out all the nodes in the graph              */
   /* Parameters:                                                 */
   /* DfsNode[] newList: Node array to change out the nodes with  */
   /* Returns: void:  just changes out the nodes in the graph     */
   /***************************************************************/
   public void setContainedNodes(DfsNode[] newList){
      nodeArray = newList;
   }
   
   /***************************************************************/
   /* Method: getNodesInGraph                                     */
   /* Purpose: Get all nodes that are currently in the graph      */
   /* Parameters:                                                 */
   /* Returns: DfsNode[]: Array of all nodes in the graph         */
   /***************************************************************/
   public DfsNode[] getNodesInGraph(){
      return nodeArray;
   }
   
   /***************************************************************/
   /* Method: addNewNode                                          */
   /* Purpose: Adds a node to the graph                           */
   /* Parameters:                                                 */
   /* DfsNode newNode: node to add to the graph                   */
   /* Returns: boolean: wheither or not it was successfully added */
   /***************************************************************/
   public boolean addNewNode(DfsNode newNode){
      if(!nodeExists(newNode.getId())){
         this.addNode(newNode);
         
         return true;
      }
      
      return false;  
   }
   
   /***************************************************************/
   /* Method: nodeExists                                          */
   /* Purpose: Checks to see if a node is already in the graph    */
   /* Parameters:                                                 */
   /* String nodeId: Id of the node to check for                  */
   /* Returns: boolean: wheither or not the node is in the graph  */
   /***************************************************************/
   public boolean nodeExists(String nodeId){
      for (int itter = 0; itter < nodeArray.length; ++itter){
         if(nodeArray[itter].getId().equals(nodeId)){
            return true;
         }
      }
      
      return false;
   }
   
   /***************************************************************/
   /* Method: connectNodes                                        */
   /* Purpose: Connects two nodes together                        */
   /* Parameters:                                                 */
   /* DfsNode node1: Will be connected to node2                   */
   /* DfsNode node2: Will be connected to node1                   */
   /* Returns: void: nodes will be linked                         */
   /***************************************************************/
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
   /* Method: getNodeById                                         */
   /* Purpose: To get a node by the string Id                     */
   /* Parameters:                                                 */
   /* String nodeId: Id of the node to find                       */
   /* Returns: DfsNode: The node that matches the Id              */
   /***************************************************************/
   public DfsNode getNodeById(String nodeId){
      for (int itter = 0; itter < nodeArray.length; ++itter){
         if(nodeArray[itter].getId().equals(nodeId)){
            return nodeArray[itter];
         }
      }
      
      return null;
   }
   
   /***************************************************************/
   /* Method: countCyclesSelect                                   */
   /* Purpose: counts cycles a node from a specified node         */
   /* Parameters:                                                 */
   /* DfsNode currentNode: node to start searcing from            */
   /* Returns: int: the number of cycles                          */
   /***************************************************************/
   public int countCyclesSelect(DfsNode currentNode){
      cycleCount = 0;
      
      this.countCyclesItter(currentNode, currentNode, false);
      this.unvisitNodes();
      
      //It countss loops moving forward and back, so the number is always double
      return cycleCount/2;
   }
   
   /***************************************************************/
   /* Method: countCycles                                         */
   /* Purpose: counts cycles a node from a the lowest id node     */
   /* Parameters:                                                 */
   /* Returns: int: the number of cycles in the graph             */
   /***************************************************************/
   public int countCycles(){
      DfsNode currentNode = this.getSmallestNode();
      cycleCount = 0;
      
      this.countCyclesItter(currentNode, currentNode, false);
      this.unvisitNodes();
            
      //It countss loops moving forward and back, so the number is always double
      return cycleCount/2;
   }

   /***************************************************************/
   /* Method: countCyclesItter                                    */
   /* Purpose: to recursively search through a node to find chain */
   /*          to both count cycles or to find cycles             */
   /* Parameters:                                                 */
   /* DfsNode currentNode: Node to check connected nodes from     */
   /* DfsNode previousNode: Node it was previously at             */
   /* boolean listCycle: wheither to looks for cycles or not      */
   /* Returns: void: just itterates up a global int for cycles    */
   /***************************************************************/
   private void countCyclesItter(DfsNode currentNode, DfsNode previousNode, boolean listCycle){
      if(!this.nodeExists(currentNode.getId())){ return; }
      
      DfsNode[] connectedNodes = currentNode.getOrderedNodes();
      currentNode.visit();
      
      for (int itter = 0; itter < connectedNodes.length; itter++){
         if(!connectedNodes[itter].wasVisited()){
            this.countCyclesItter(connectedNodes[itter], currentNode, listCycle);
         }else{
            if(!connectedNodes[itter].getId().equals(previousNode.getId())){
               cycleCount++;
               
               if(listCycle){
                  this.listCyclesItter(connectedNodes[itter], connectedNodes[itter], connectedNodes[itter], currentNode);
               }
            }
         }
      }
   }
   
   /***************************************************************/
   /* Method: countCyclesTwo                                      */
   /* Purpose: counts cycles a node from a the lowest id node     */
   /*          while it is counting with countCycles              */
   /* Parameters:                                                 */
   /* Returns: int: the number of cycles in the graph             */
   /***************************************************************/
   public int countCyclesTwo(){
      DfsNode currentNode = this.getSmallestNode();
      cycleCountTwo = 0;
      
      this.countCyclesItterTwo(currentNode, currentNode, false);
      this.unvisitNodesTwo();
            
      //It countss loops moving forward and back, so the number is always double
      return cycleCountTwo/2;
   }

   /***************************************************************/
   /* Method: countCyclesItterTwo                                 */
   /* Purpose: to recursively search through a node to find chain */
   /*          to both count cycles or to find cycles             */
   /* Parameters:                                                 */
   /* DfsNode currentNode: Node to check connected nodes from     */
   /* DfsNode previousNode: Node it was previously at             */
   /* boolean listCycle: wheither to looks for cycles or not      */
   /* Returns: void: just itterates up a global int for cycles    */
   /***************************************************************/
   private void countCyclesItterTwo(DfsNode currentNode, DfsNode previousNode, boolean listCycle){
      if(!this.nodeExists(currentNode.getId())){ return; }
      
      DfsNode[] connectedNodes = currentNode.getOrderedNodes();
      currentNode.visitTwo();
      
      for (int itter = 0; itter < connectedNodes.length; itter++){
         if(!connectedNodes[itter].wasVisitedTwo()){
            this.countCyclesItterTwo(connectedNodes[itter], currentNode, listCycle);
         }else{
            if(!connectedNodes[itter].getId().equals(previousNode.getId())){
               cycleCountTwo++;
               
               if(listCycle){
                  this.listCyclesItter(connectedNodes[itter], connectedNodes[itter], connectedNodes[itter], currentNode);
               }
            }
         }
      }
   }
   
   /***************************************************************/
   /* Method: listCycles                                          */
   /* Purpose: To list the chain of nodes that makes up a cycle   */
   /* Parameters:                                                 */
   /* Returns: ArrayList<DfsNode[]>: contain all node arrays that */
   /*          Are in the graph                                   */
   /***************************************************************/
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
   /* Method: listCyclesItter                                     */
   /* Purpose: to go though and find all node chains in cycles    */
   /* Parameters:                                                 */
   /* DfsNode currentNode: Node to check connected nodes from     */
   /* DfsNode previousNode: Node it was previously at             */
   /* DfsNode startNode: Node at the start of the cycle           */
   /* DfsNode finalNode: Node at the end of the cycle             */
   /* Returns: void: adds to the nodeChainArray                   */
   /***************************************************************/
   private ArrayList<DfsNode> nodeChainArray = new ArrayList<>();//used only by this function
   private void listCyclesItter(DfsNode currentNode, DfsNode previousNode, DfsNode startNode, DfsNode finalNode){
      DfsNode[] connectedNodes = currentNode.getOrderedNodes();
      nodeChainArray.add(currentNode);
      
      for (int itter = 0; itter < connectedNodes.length; itter++){
         if(!currentNode.getId().equals(startNode.getId())){
            if(connectedNodes[itter].getId().equals(finalNode.getId())){
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
   /* Method: isConnected                                         */
   /* Purpose: To find out if nodes are connected                 */
   /* Parameters:                                                 */
   /* DfsNode base: Node to checke connections of                 */
   /* DfsNode possibleConnection: none to check if it is connected*/
   /* Returns: boolean: wheither if it was connected or not       */
   /***************************************************************/
   public boolean isConnected(DfsNode base, DfsNode possibleConnection){
      DfsNode[] connectedList = base.getOrderedNodes();

      for (int itter = 0; itter < connectedList.length; itter++) {
         if(connectedList[itter].getId().equals(possibleConnection.getId())){
            return true;
         }
      }
      
      return false;
   }
   
   /***************************************************************/
   /* Method: isNodeInChain                                       */
   /* Purpose: Checks if a node is in a chain already or not      */
   /* Parameters:                                                 */
   /* DfsNode checkNode: node to check for                        */
   /* ArrayList<DfsNode> nodeChain: list to check in for node     */
   /* Returns: boolean: wheither or no the node is in the list    */
   /***************************************************************/
   private boolean isNodeInChain(DfsNode checkNode, ArrayList<DfsNode> nodeChain){
      for (int itter = 0; itter < nodeChain.size(); itter++){
         DfsNode tempNode = nodeChain.get(itter);
         
         if(tempNode.getId().equals(checkNode.getId())){
            return true;
         }         
      }
      
      return false;
   }

   /***************************************************************/
   /* Method: areDfsArraysEqual                                   */
   /* Purpose: Compares two arrays of nodes to see if they have   */
   /*          The same content.                                  */
   /* Parameters:                                                 */
   /* DfsNode[] dfsArrayOne: Compares to dfsArrayTwo              */
   /* DfsNode[] dfsArrayTwo: Compares to dfsArrayOne              */
   /* Returns: boolean: wheither or not the arrays are equal      */
   /***************************************************************/
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
         if(!dfsArrayOneClone[itter].getId().equals(dfsArrayTwoClone[itter].getId())){
            return false;
         }         
      }
      
      return true;
   }

   /***************************************************************/
   /* Method: addNode                                             */
   /* Purpose: To add a node to an array of nodes                 */
   /* Parameters:                                                 */
   /* DfsNode newNode: Node to add to the node array              */
   /* Returns: void: Just adds a node to an array                 */
   /***************************************************************/
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
   /* Method: unvisitNodes                                        */
   /* Purpose: It goes through all nodes and calls resetVisit     */
   /* Parameters:                                                 */
   /* Returns: void: It just sets the nodes visited to false      */
   /***************************************************************/
   private void unvisitNodes(){
      for (int itter = 0; itter < nodeArray.length; itter++) {
         nodeArray[itter].resetVisit();
      }
   }

   /***************************************************************/
   /* Method: unvisitNodesTwo                                     */
   /* Purpose: It goes through all nodes and calls resetVisitTwo  */
   /* Parameters:                                                 */
   /* Returns: void: It just sets the nodes visited to false      */
   /***************************************************************/
   private void unvisitNodesTwo(){
      for (int itter = 0; itter < nodeArray.length; itter++) {
         nodeArray[itter].resetVisitTwo();
      }
   }

   /***************************************************************/
   /* Method: addToChainNoDup                                     */
   /* Purpose: This adds a DfsNode array to a node array list only*/
   /*          if the array isn't already in the list             */
   /* Parameters:                                                 */
   /* DfsNode[] tempArray: Array to attempt to add                */
   /* ArrayList<DfsNode[]> nodeChainArray: list to add to         */
   /* Returns: void: list is passed as refrence and modified      */
   /***************************************************************/
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
   /* Method: getSmallestNode                                     */
   /* Purpose: This gets the smallest array in the listy          */
   /* Parameters:                                                 */
   /* Returns: DfsNode: returns the smallest node                 */
   /***************************************************************/
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
   /* Method: sortNodesAlpha                                      */
   /* Purpose: Sorts an array of nodes alphabetically             */
   /* Parameters:                                                 */
   /* DfsNode[] arrayOfNodes: node array to sort                  */
   /* Returns: DfsNode[]: the array sorted, possibly not needed   */
   /***************************************************************/
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
   /* Method: removeLargestArray                                  */
   /* Purpose: removes the largest array in a list                */
   /* Parameters:                                                 */
   /* ArrayList<DfsNode[]> dfsArrayList: list to remove array from*/
   /* Returns: void: just removes a node                          */
   /***************************************************************/
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

