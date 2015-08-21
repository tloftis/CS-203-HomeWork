/***************************************************************/
/* Timothy Loftis                                              */
/* Login ID: loft3285                                          */
/* CS-203, Summer 2015                                         */
/* Programming Assignment 2                                    */
/* main class: Uses Dfs Classes to find pares the text of a    */
/*             Data file and display graph information found   */
/*             inside of it                                    */
/***************************************************************/

import java.util.*;//used for mostly ArrayLists
import java.io.*;//used to read files

public class main{
   /***************************************************************/
   /* Method: main                                                */
   /* Purpose: Uses the text parser and Dfs Classes to generate   */
   /*          Information about the graph formed                 */
   /* Parameters:                                                 */
   /* String[] args: Given by terminal, location of data txt file */
   /* Returns: Void: Output to the console only                   */
   /***************************************************************/
	public static void main(String[] args) {
      int[] sorted = new int[] {45,23,11,89,77,98,4,28,65,43};
      String sortedStr = "";
      
      for(int i = 0; i < (sorted.length - 1); i++){
         sortedStr += sorted[i] + ", ";   
      }  
      sortedStr += sorted[(sorted.length - 1)];
      System.out.println(sortedStr);
      
      MergeSort sortAlg = new MergeSort(sorted);
      sortAlg.doTheAlgorithm();
      
      sorted = sortAlg.getArray();
      sortedStr = "";
      
      for(int i = 0; i < (sorted.length - 1); i++){
         sortedStr += sorted[i] + ", ";   
      }  
      sortedStr += sorted[(sorted.length - 1)];
      System.out.println(sortedStr);
   }
}