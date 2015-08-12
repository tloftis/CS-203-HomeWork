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
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   public DfsNode(String newId){
      this.id = newId;
   }

   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   public boolean connectNode(DfsNode newNode){
      if(!isNodeConnected(newNode.getId())){
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
   public boolean isNodeConnected(String nodeId){
      for (int itter = 0; itter < connectedNodes.length; ++itter){
         if(connectedNodes[itter].getId() == nodeId){
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
   public String getId(){
      return this.id;
   }   
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   public DfsNode[] getOrderedNodes(){
      this.sortNodesAlpha();
      return this.connectedNodes;
   }
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   public boolean wasVisited(){
      return this.visited;
   }
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   public void visit(){
      this.visited = true;
   }
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   public void resetVisit(){
      this.visited = false;
   }
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   public boolean wasVisitedTwo(){
      return this.visitedTwo;
   }
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   public void visitTwo(){
      this.visitedTwo = true;
   }
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
   public void resetVisitTwo(){
      this.visitedTwo = false;
   }
   
   /***************************************************************/
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
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
   /* Method: */
   /* Purpose: */
   /* Parameters:                                                 */
   /* : */
   /* Returns: : */
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

