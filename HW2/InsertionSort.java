/***************************************************************/
/* Timothy Loftis                                              */
/* Login ID: loft3285                                          */
/* CS-203, Summer 2015                                         */
/* Programming Assignment 2                                    */
/* DfsNode class: This is a merge sort for an integer array    */
/***************************************************************/

import java.util.*;

public class InsertionSort{
   private int[] sortArray;
   private int length = 0;
   
   public InsertionSort(int[] intArray){
      this.sortArray = intArray;
      this.length = intArray.length;
   }
   
   public void doTheAlgorithm(){
      for(int itter = 1; itter < this.sortArray.length; itter++){
                  
         for(int innerItter = (itter - 1); innerItter >= 0; innerItter--){
            int temp = this.sortArray[innerItter + 1];
            
            if(this.sortArray[innerItter] > temp){
               this.sortArray[innerItter + 1] = this.sortArray[innerItter];
               this.sortArray[innerItter] = temp;
            }else
               break;
            }
      }
   }
   
   public int[] getArray(){
      return this.sortArray;
   }
}