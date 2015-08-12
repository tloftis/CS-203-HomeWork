/***************************************************************/
/* Timothy Loftis                                              */
/* Login ID: loft3285                                          */
/* CS-203, Summer 2015                                         */
/* Programming Assignment 1                                    */
/* DfsNode class: This is a single node contained in a graph   */
/*             It has a unique ID and can be connected to other*/
/*             nodes.                                          */
/***************************************************************/

import java.util.*;

public class DfsNode
{
   private String id;
   private DfsNode[] connectedNodes= new DfsNode[0];
   private boolean visited = false;
   private boolean visitedTwo = false;
   
   /***************************************************************/
   /* Method: DfsNode                                             */
   /* Purpose: Creates a node from an any string Id               */
   /* Parameters:                                                 */
   /* String newId: Id that you want to assign to the node        */
   /* Returns: DfsNode: node with id specified                    */
   /***************************************************************/
   public DfsNode(String newId){
      this.id = newId;
   }

   /***************************************************************/
   /* Method: connectNode                                         */
   /* Purpose: to connect node to this node                       */
   /* Parameters:                                                 */
   /* DfsNode newNode: node to connect to this node               */
   /* Returns: boolean: wheither or not the connection worked     */
   /***************************************************************/
   public boolean connectNode(DfsNode newNode){
      if(!isNodeConnected(newNode.getId())){
         this.addNode(newNode);
         return true;
      }
      
      return false;
   }
   
   /***************************************************************/
   /* Method: isNodeConnected                                     */
   /* Purpose: wheither or not the node is connected to this node */
   /* Parameters:                                                 */
   /* String nodeId: id of the node to connect to                 */
   /* Returns: boolean: wheither or not the node is connected     */
   /***************************************************************/
   public boolean isNodeConnected(String nodeId){
      for (int itter = 0; itter < connectedNodes.length; ++itter){
         if(connectedNodes[itter].getId().equals(nodeId)){
            return true;
         }
      }
      
      return false;
   }
   
   /***************************************************************/
   /* Method: getId                                               */
   /* Purpose: get the id of this node                            */
   /* Parameters:                                                 */
   /* Returns: String: the id of this node                        */
   /***************************************************************/
   public String getId(){
      return this.id;
   }   
   
   /***************************************************************/
   /* Method: getOrderedNodes                                     */
   /* Purpose: get all of the connected nodes                     */
   /* Parameters:                                                 */
   /* Returns: DfsNode[]: array of connected nodes                */
   /***************************************************************/
   public DfsNode[] getOrderedNodes(){
      this.sortNodesAlpha();
      return this.connectedNodes;
   }
   
   /***************************************************************/
   /* Method: wasVisited                                          */
   /* Purpose: if the node was visited, used for traversial       */
   /* Parameters:                                                 */
   /* Returns: boolean: whither the node was visited              */
   /***************************************************************/
   public boolean wasVisited(){
      return this.visited;
   }
   
   /***************************************************************/
   /* Method: visit                                               */
   /* Purpose: set the node to have been visited                  */
   /* Parameters:                                                 */
   /* Returns: void: marks node as visited                        */
   /***************************************************************/
   public void visit(){
      this.visited = true;
   }
   
   /***************************************************************/
   /* Method: resetVisit                                          */
   /* Purpose: sets the node to not be visited                    */
   /* Parameters:                                                 */
   /* Returns: void: sets visited to be false                     */
   /***************************************************************/
   public void resetVisit(){
      this.visited = false;
   }
   
   /***************************************************************/
   /* Method: wasVisitedTwo                                       */
   /* Purpose: if the node was visited, used for traversial       */
   /* Parameters:                                                 */
   /* Returns: boolean: whither the node was visited              */
   /***************************************************************/
   public boolean wasVisitedTwo(){
      return this.visitedTwo;
   }
   
   /***************************************************************/
   /* Method: visitTwo                                            */
   /* Purpose: set the node to have been visited                  */
   /* Parameters:                                                 */
   /* Returns: void: marks node as visited                        */
   /***************************************************************/
   public void visitTwo(){
      this.visitedTwo = true;
   }
   
   /***************************************************************/
   /* Method: resetVisitTwo                                       */
   /* Purpose: sets the node to not be visited                    */
   /* Parameters:                                                 */
   /* Returns: void: sets visited to be false                     */
   /***************************************************************/
   public void resetVisitTwo(){
      this.visitedTwo = false;
   }

   /***************************************************************/
   /* Method: addNode                                             */
   /* Purpose: To add a node to an array of connected nodes       */
   /* Parameters:                                                 */
   /* DfsNode newNode: Node to add to the node array              */
   /* Returns: void: Just adds a node to an array                 */
   /***************************************************************/
   private void addNode(DfsNode newNode){
      int newLength = this.connectedNodes.length + 1;
      DfsNode[] tempDfsNode= new DfsNode[newLength];

      for (int itter = 0; itter < (newLength - 1); itter++) {
         tempDfsNode[itter] = this.connectedNodes[itter];
      }
      
      tempDfsNode[newLength - 1] = newNode;
      
      this.connectedNodes = tempDfsNode;
   }

   /***************************************************************/
   /* Method: sortNodesAlpha                                      */
   /* Purpose: Sorts an array of connected nodes alphabetically   */
   /* Parameters:                                                 */
   /* DfsNode[] arrayOfNodes: node array to sort                  */
   /* Returns: DfsNode[]: the array sorted, possibly not needed   */
   /***************************************************************/
   private void sortNodesAlpha(){
      DfsNode tempDfsNode; 
      int len = connectedNodes.length;
      
      for (int outItter = 0; outItter < len; outItter++) {
         for (int itter = (outItter + 1); itter < len; itter++) {
            if(connectedNodes[itter].getId().compareTo(connectedNodes[outItter].getId())<0){
               tempDfsNode = connectedNodes[outItter];
               connectedNodes[outItter] = connectedNodes[itter]; 
               connectedNodes[itter] = tempDfsNode;
            }
         }

      }
   }
}

